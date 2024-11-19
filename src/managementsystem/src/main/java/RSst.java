import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.StandardOpenOption;

public class RSst extends JPanel {
    private JComboBox<String> subjectComboBox;
    private JTextField filePathField;
    private JButton chooseFileButton;
    private JButton submitButton;
    private File selectedFile;
    private String studentID;
    private String intake;

    public RSst(String studentID, String intake) {
        this.studentID = studentID;
        this.intake = intake;

        setLayout(null);

        JLabel subjectLabel = new JLabel("Choose Subject:");
        subjectLabel.setBounds(50, 50, 200, 30);
        add(subjectLabel);

        List<String> subjects = getSubjectsForIntake(intake, "intake_subject.txt");
        subjectComboBox = new JComboBox<>(subjects.toArray(new String[0]));
        subjectComboBox.setBounds(250, 50, 200, 30);
        add(subjectComboBox);

        JLabel fileLabel = new JLabel("Choose PDF File:");
        fileLabel.setBounds(50, 100, 200, 30);
        add(fileLabel);

        filePathField = new JTextField();
        filePathField.setBounds(250, 100, 200, 30);
        filePathField.setEditable(false);
        add(filePathField);

        chooseFileButton = new JButton("Choose File");
        chooseFileButton.setBounds(470, 100, 150, 30);
        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF files", "pdf"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    filePathField.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        add(chooseFileButton);

        submitButton = new JButton("Submit");
        submitButton.setBounds(250, 150, 100, 30);
        submitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedFile != null && selectedFile.getName().endsWith(".pdf")) {
                String subject = (String) subjectComboBox.getSelectedItem();
                String newFileName = subject + "_" + studentID + ".pdf";
                String targetDirPath = "Report submission\\" + intake + "\\" + subject; // Relative path
                File targetDir = new File(targetDirPath);
                if (!targetDir.exists()) {
                    if (!targetDir.mkdirs()) {
                        JOptionPane.showMessageDialog(null, "Failed to create directory.");
                        return;
                    }
                }
                File targetFile = new File(targetDir, newFileName);
                try {
                    Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    JOptionPane.showMessageDialog(null, "File submitted successfully as " + newFileName);
                    updateReportFile(intake, subject, studentID);
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(null, "File submission failed.");
                    ioException.printStackTrace();
                }
            } else {
            JOptionPane.showMessageDialog(null, "Please select a valid PDF file.");
        }
    }
});

        add(submitButton);
    }

    private List<String> getSubjectsForIntake(String intake, String fileName) {
        List<String> subjects = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                String[] parts = line.split("\\$");
                if (parts[0].equals(intake)) {
                    for (int i = 1; i < parts.length; i++) {
                        String[] subjectParts = parts[i].split("\\|");
                        subjects.add(subjectParts[0]);
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    private void updateReportFile(String intake, String subject, String studentID) {
        String reportFileName = "report.txt";
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(reportFileName));
        } catch (IOException e) {
            e.printStackTrace();
            lines = new ArrayList<>();
        }

        String newRecord = String.format("%s$%s$%s_%s$ $ $ $", intake, subject, subject, studentID);
        boolean updated = false;

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split("\\$");
            if (parts.length >= 4 && parts[0].equals(intake) && parts[1].equals(subject) && parts[2].equals(subject + "_" + studentID)) {
                lines.set(i, newRecord);
                updated = true;
                break;
            }
        }

        if (!updated) {
            lines.add(newRecord);
        }

        try {
            Files.write(Paths.get(reportFileName), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
