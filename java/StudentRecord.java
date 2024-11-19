import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class StudentRecord extends JPanel {
    private JPanel panel;
    private final JButton searchButton;
    public JButton deleteButton;
    public JButton updateButton;
    private final JTable table;
    private String currentStudentId;
    private DefaultTableModel tableModel;
    private final JTextField searchField;

    public StudentRecord() {
        this.panel = new JPanel(new BorderLayout());

        String[] columnheader = {"Student ID", "Student Name", "Password", "Student Intake"};

        tableModel = new DefaultTableModel(columnheader, 0);

        try (BufferedReader br = new BufferedReader(new FileReader("student_id.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\$");
                tableModel.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create table with the model
        table = new JTable(tableModel);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add components to the panel
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        searchField = new JTextField(20);
        searchButton = new JButton("Search for Student");
        searchButton.addActionListener(new SearchActionListener());

        gbc.gridx = 0;
        gbc.gridy = 0;
        searchPanel.add(searchField, gbc);

        gbc.gridx = 1;
        searchPanel.add(searchButton, gbc);

        // Add components to the register panel
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }

    
 private class SearchActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String searchId = searchField.getText().trim();
        if (searchId.isEmpty()) {
            JOptionPane.showMessageDialog(StudentRecord.this, "Please enter a Student ID to search.", "No Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        File studentIdFile = new File("student_id.txt");
        if (!studentIdFile.exists()) {
            JOptionPane.showMessageDialog(StudentRecord.this, "Error: Student ID file not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean studentFound = false;
        try (Scanner fileScanner = new Scanner(studentIdFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\$");
                if (parts.length == 4 && parts[0].equals(searchId)) {
                    studentFound = true;

                    String studentInfo = "Student ID: " + parts[0] + "\n"
                            + "Student Name: " + parts[1] + "\n"
                            + "Password: " + parts[2] + "\n"
                            + "Student Intake: " + parts[3];

                    currentStudentId = searchId;

                    JFrame resultFrame = new JFrame("Student Details");
                    resultFrame.setLayout(new BorderLayout());

                    // Profile picture section
                    JPanel profilePicPanel = new JPanel(new BorderLayout());
                    JLabel profilePicLabel = new JLabel();
                    profilePicPanel.add(profilePicLabel, BorderLayout.CENTER);

                    File profilePicFile = new File("profile_pics/" + currentStudentId + ".png");
                    if (profilePicFile.exists()) {
                        try {
                            BufferedImage profilePic = ImageIO.read(profilePicFile);
                            Image scaledImage = profilePic.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                            profilePicLabel.setIcon(new ImageIcon(scaledImage));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    
                    // Buttons panel
                    JPanel buttonsPanel = new JPanel();
                    deleteButton = new JButton("Delete");
                    updateButton = new JButton("Update");

                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int selectedRow = table.getSelectedRow();
                            if (selectedRow != -1) {
                                tableModel.removeRow(selectedRow);
                                deleteStudent(currentStudentId);
                                JOptionPane.showMessageDialog(StudentRecord.this, "Student record deleted.");
                                resultFrame.dispose(); // Close the frame after deletion
                            } else {
                                JOptionPane.showMessageDialog(StudentRecord.this, "Please select a student to delete.");
                            }
                        }
                    });

                    updateButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int selectedRow = table.getSelectedRow();
                            if (selectedRow != -1) {
                                String newStudentName = JOptionPane.showInputDialog(StudentRecord.this, "Enter new Student Name:");
                                String newPassword = JOptionPane.showInputDialog(StudentRecord.this, "Enter new Student Password:");
                                // Create JComboBox for intake selection
                                String[] intakeOptions = {"AGH2F2402CS", "AGH2F2402CS(AI)", "AGH2F2402CS(CYB)", "AGH2F2402CS(DA)", "AGH2F2402CS(DF)"};
                                JComboBox<String> intakeComboBox = new JComboBox<>(intakeOptions);
                                int result = JOptionPane.showConfirmDialog(StudentRecord.this, intakeComboBox, "Select new Intake:", JOptionPane.OK_CANCEL_OPTION);

                                if (newStudentName != null && newPassword != null && result == JOptionPane.OK_OPTION) {
                                    String newIntake = (String) intakeComboBox.getSelectedItem();
                                    tableModel.setValueAt(newStudentName, selectedRow, 1);
                                    tableModel.setValueAt(newPassword, selectedRow, 2);
                                    tableModel.setValueAt(newIntake, selectedRow, 3);
                                    updateStudent(currentStudentId, newStudentName, newPassword, newIntake);
                                    JOptionPane.showMessageDialog(StudentRecord.this, "Student record updated.");
                                    resultFrame.dispose();
                                }
                            } else {
                                JOptionPane.showMessageDialog(StudentRecord.this, "Please select a student to update.");
                            }
                        }
                    });

                    buttonsPanel.add(deleteButton);
                    buttonsPanel.add(updateButton);

                    // Text area for student info
                    JTextArea textArea = new JTextArea(studentInfo);
                    textArea.setEditable(false);
                    JScrollPane textScrollPane = new JScrollPane(textArea);

                    // Add components to resultFrame
                    resultFrame.add(textScrollPane, BorderLayout.CENTER);
                    resultFrame.add(profilePicPanel, BorderLayout.EAST);
                    resultFrame.add(buttonsPanel, BorderLayout.SOUTH);

                    resultFrame.setSize(500, 300); // Adjust frame size as needed
                    resultFrame.setLocationRelativeTo(StudentRecord.this);
                    resultFrame.setVisible(true);
                    return;
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(StudentRecord.this, "Error reading student ID file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (!studentFound) {
            JOptionPane.showMessageDialog(StudentRecord.this, "Student ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    
    private static void deleteStudent(String studentId) {
        File studentIdFile = new File("student_id.txt");
        List<String> lines = new ArrayList<>();
        if (studentIdFile.exists()) {
            try (Scanner fileScanner = new Scanner(studentIdFile)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    if (!line.startsWith(studentId)) {
                        lines.add(line);
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("Error reading student ID file: " + e.getMessage());
            }
        }

        try (FileWriter fileWriter = new FileWriter(studentIdFile)) {
            for (String line : lines) {
                fileWriter.write(line + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to student ID file: " + e.getMessage());
        }
    }
    
    private static void updateStudent(String studentId, String newName, String newPass, String newIntake) {
        File studentIdFile = new File("student_id.txt");
        List<String> lines = new ArrayList<>();
        if (studentIdFile.exists()) {
            try (Scanner fileScanner = new Scanner(studentIdFile)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    if (line.startsWith(studentId)) {
                        line = studentId + "$" + newName + "$" + newPass + "$" + newIntake;
                    }
                    lines.add(line);
                }
            } catch (FileNotFoundException e) {
                System.err.println("Error reading student ID file: " + e.getMessage());
            }
        }

        try (FileWriter fileWriter = new FileWriter(studentIdFile)) {
            for (String line : lines) {
                fileWriter.write(line + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to student ID file: " + e.getMessage());
        }
    }
 
}