import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FBst extends JPanel {
    private JComboBox<String> subjectComboBox;
    private JComboBox<Integer> markingComboBox;
    private JTextArea feedbackArea;
    private JButton submitButton;
    private String studentID;
    private String intake;

    public FBst(String studentID, String intake) {
        this.studentID = studentID;
        this.intake = intake;

        setLayout(null);

        JLabel subjectLabel = new JLabel("Choose Subject:");
        subjectLabel.setBounds(50, 50, 200, 30);
        add(subjectLabel);

        List<Subject> subjects = SubjectUtil.getSubjectsForIntake(intake);
        subjectComboBox = new JComboBox<>(subjects.stream().map(Subject::getName).toArray(String[]::new));
        subjectComboBox.setBounds(250, 50, 200, 30);
        add(subjectComboBox);

        JLabel markingLabel = new JLabel("Marking (1-5):");
        markingLabel.setBounds(50, 100, 200, 30);
        add(markingLabel);

        markingComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        markingComboBox.setBounds(250, 100, 200, 30);
        add(markingComboBox);

        JLabel feedbackLabel = new JLabel("Feedback:");
        feedbackLabel.setBounds(50, 150, 200, 30);
        add(feedbackLabel);

        feedbackArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(feedbackArea);
        scrollPane.setBounds(250, 150, 200, 100);
        add(scrollPane);

        submitButton = new JButton("Submit");
        submitButton.setBounds(250, 270, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitFeedback();
            }
        });
        add(submitButton);
    }

    private void submitFeedback() {
        String subjectName = (String) subjectComboBox.getSelectedItem();
        int marking = (int) markingComboBox.getSelectedItem();
        String feedbackContent = feedbackArea.getText();

        if (subjectName.isEmpty() || feedbackContent.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        Subject subject = SubjectUtil.getSubjectByName(subjectName);
        String lecturerId = subject.getLecturerId();

        String feedbackEntry = intake + "$" + subjectName + "$" + lecturerId + "$" + marking + "$" + feedbackContent + "$" + studentID;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("feedback.txt", true))) {
            writer.write(feedbackEntry);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Feedback submitted successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to submit feedback.");
            e.printStackTrace();
        }
    }
}
