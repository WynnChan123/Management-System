
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AllotStudents extends JPanel implements ActionListener {
    private JRadioButton allotByIntakeRadioButton, allotByIndividualRadioButton;
    private ButtonGroup allotmentButtonGroup;
    private JComboBox<String> assessmentTypeComboBox;
    private JComboBox<String> intakeCodeComboBox;
    private JTextField studentIdTextField, studentNameTextField;
    private JButton allotButton;

    public AllotStudents() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Allotment options: By Intake and By Individual Student
        allotByIntakeRadioButton = new JRadioButton("By Intake");
        allotByIndividualRadioButton = new JRadioButton("By Individual Student");
        allotmentButtonGroup = new ButtonGroup();
        allotmentButtonGroup.add(allotByIntakeRadioButton);
        allotmentButtonGroup.add(allotByIndividualRadioButton);
        allotByIntakeRadioButton.setSelected(true); // Select intake by default

        // Assessment Type Combo Box
        assessmentTypeComboBox = new JComboBox<>(new String[]{"Internship", "Investigation Reports", "CP1", "CP2", "RMCP", "FYP"});
        assessmentTypeComboBox.setSelectedIndex(0);

        // Intake Code Combo Box
        intakeCodeComboBox = new JComboBox<>(new String[]{
            "AGH2F2402CS", "AGH2F2402CS (AI)", "AGH2F2402CS (CYB)",
            "AGH2F2402CS (DA)", "AGH2F2402CS (DF)"
        });

        // Student ID and Name Text Fields
        studentIdTextField = new JTextField(10);
        studentNameTextField = new JTextField(10); // Changed to 10 columns

        // Allot Button
        allotButton = new JButton("Allot Students");
        allotButton.addActionListener(this);

        // Add components to the panel using GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(new JLabel("Allot By:"), gbc);

        gbc.gridy++;
        mainPanel.add(allotByIntakeRadioButton, gbc);

        gbc.gridy++;
        mainPanel.add(allotByIndividualRadioButton, gbc);

        gbc.gridy++;
        mainPanel.add(new JLabel("Assessment Type:"), gbc);

        gbc.gridy++;
        mainPanel.add(assessmentTypeComboBox, gbc);

        gbc.gridy++;
        mainPanel.add(new JLabel("Intake Code:"), gbc);

        gbc.gridy++;
        mainPanel.add(intakeCodeComboBox, gbc);

        gbc.gridy++;
        mainPanel.add(new JLabel("Student ID:"), gbc);

        gbc.gridy++;
        mainPanel.add(studentIdTextField, gbc);

        gbc.gridy++;
        mainPanel.add(new JLabel("Student Name:"), gbc);

        gbc.gridy++;
        mainPanel.add(studentNameTextField, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        mainPanel.add(allotButton, gbc);

        // Add mainPanel to the center of the layout
        add(mainPanel, BorderLayout.CENTER);

        // Add listeners for radio buttons to toggle input fields
        allotByIntakeRadioButton.addActionListener(this);
        allotByIndividualRadioButton.addActionListener(this);

        // Initialize input fields
        toggleInputFields();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == allotButton) {
            String assessmentType = (String) assessmentTypeComboBox.getSelectedItem();

            if (allotByIntakeRadioButton.isSelected()) {
                String intakeCode = (String) intakeCodeComboBox.getSelectedItem();
                allotByIntake(intakeCode, assessmentType);
            } else if (allotByIndividualRadioButton.isSelected()) {
                String studentId = studentIdTextField.getText().trim();
                String studentName = studentNameTextField.getText().trim();
                if (studentId.isEmpty() || studentName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter student ID and name.");
                    return;
                }
                allotByStudent(studentId, studentName, assessmentType);
            }
        }

        // Toggle input fields based on selected allotment option
        toggleInputFields();
    }

    private void toggleInputFields() {
        boolean isByIntake = allotByIntakeRadioButton.isSelected();
        intakeCodeComboBox.setEnabled(isByIntake);
        studentIdTextField.setEnabled(!isByIntake);
        studentNameTextField.setEnabled(!isByIntake);
    }

    private void allotByIntake(String intakeCode, String assessmentType) {
        File studentIdFile = new File("student_id.txt");
        File studentAssessmentFile = new File("student_assessment.txt");
        List<String> updatedStudents = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(studentIdFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\$");
                if (parts.length >= 4 && parts[3].equals(intakeCode)) {
                    String newLine = parts[0] + "$" + parts[1] + "$" + parts[3] + "$" + assessmentType;
                    updatedStudents.add(newLine);
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error reading student ID file: " + ex.getMessage());
            return;
        }

        try (FileWriter fileWriter = new FileWriter(studentAssessmentFile, true)) {
            for (String student : updatedStudents) {
                fileWriter.write(student + "\n");
            }
            JOptionPane.showMessageDialog(this, "Students allotted by intake successfully.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error writing to student assessment file: " + ex.getMessage());
        }
    }

    private void allotByStudent(String studentId, String studentName, String assessmentType) {
        File studentIdFile = new File("student_id.txt");
        File studentAssessmentFile = new File("student_assessment.txt");
        boolean studentFound = false;

        try (Scanner fileScanner = new Scanner(studentIdFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\$");
                if (parts.length >= 4 && parts[0].equals(studentId) && parts[1].equals(studentName)) {
                    String newLine = parts[0] + "$" + parts[1] + "$" + parts[3] + "$" + assessmentType;
                    try (FileWriter fileWriter = new FileWriter(studentAssessmentFile, true)) {
                        fileWriter.write(newLine + "\n");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Error writing to student assessment file: " + ex.getMessage());
                    }
                    studentFound = true;
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error reading student ID file: " + ex.getMessage());
        }

        if (studentFound) {
            JOptionPane.showMessageDialog(this, "Student allotted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Error: Student ID or Name does not exist.");
        }
    }
}