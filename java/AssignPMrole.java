import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class AssignPMrole extends JPanel {
    private final JPanel panelView;
    private final JTable lecturerTable;
    private final DefaultTableModel lecturerTableModel, pmTableModel;
    private JButton deleteButton, updateButton, allotPMButton;
    private final JButton searchButton;
    private final JTextField searchField;
    private String currentLecturerId;

    public AssignPMrole(DefaultTableModel pmTableModel) {

        // View Lecturers Panel (Without PM Role)
        this.pmTableModel = pmTableModel;
        this.panelView = new JPanel(new BorderLayout());
        String[] columnHeaders = {"Lecturer ID", "Project Manager ID", "Lecturer Name", "Password", "Intake"};
        lecturerTableModel = new DefaultTableModel(columnHeaders, 0);
        lecturerTable = new JTable(lecturerTableModel);
        JScrollPane scrollPane = new JScrollPane(lecturerTable);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(20);
        searchButton = new JButton("Search for Lecturer");
        searchButton.addActionListener(new SearchLecturerActionListener(this, searchField, lecturerTableModel));

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        panelView.add(searchPanel, BorderLayout.NORTH);
        panelView.add(scrollPane, BorderLayout.CENTER);

        loadLecturers();

    }
    
        public JPanel getPanel() {
        return panelView;
    }


    private void loadLecturers() {
        File lecturerIdFile = new File("lecturer_id.txt");
        if (!lecturerIdFile.exists()) {
            JOptionPane.showMessageDialog(this, "Error: Lecturer ID file not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Scanner fileScanner = new Scanner(lecturerIdFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\$");
                if (parts.length < 5) {
                    String[] paddedParts = new String[5];
                    System.arraycopy(parts, 0, paddedParts, 0, parts.length);
                    parts = paddedParts;
                }
                String lecturerId = parts[0].trim();
                String role = parts[1].trim();
                String lecturerName = parts[2].trim();
                String lecturerPass = parts[3].trim();
                String lecturerIntake = parts[5].trim();

                if (role.isEmpty()) {
                    role = "";
                }

                Object[] rowData = {lecturerId, role, lecturerName, lecturerPass, lecturerIntake};
                if (role.startsWith("PM")) {
                    System.out.println("Project Manager: " + line); // For debugging
                    pmTableModel.addRow(rowData);
                } else {
                    System.out.println("Lecturer: " + line); // For debugging
                    lecturerTableModel.addRow(rowData);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading lecturer ID file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void searchLecturer(String lecturerId) {
        for (int i = 0; i < lecturerTableModel.getRowCount(); i++) {
            if (lecturerTableModel.getValueAt(i, 0).equals(lecturerId)) {
                String lecturerInfo = "Lecturer's ID: " + lecturerTableModel.getValueAt(i, 0) + "\n"
                        + "Lecturer's Name: " + lecturerTableModel.getValueAt(i, 2) + "\n"
                        + "Lecturer's Password: " + lecturerTableModel.getValueAt(i, 3) + "\n"
                        + "Lecturer's Intake: " + lecturerTableModel.getValueAt(i, 4);

                currentLecturerId = lecturerId;

                JFrame resultFrame = new JFrame("Lecturer Details");
                resultFrame.setLayout(new BorderLayout());

                // Profile picture section
                JPanel profilePicPanel2 = new JPanel(new BorderLayout());
                JLabel profilePicLabel2 = new JLabel();
                profilePicPanel2.add(profilePicLabel2, BorderLayout.CENTER);

                File profilePicFile2 = new File("profile_pics/" + currentLecturerId + ".png");
                if (profilePicFile2.exists()) {
                    try {
                        BufferedImage profilePic2 = ImageIO.read(profilePicFile2);
                        if (profilePic2 != null) {
                            Image scaledImage = profilePic2.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                            profilePicLabel2.setIcon(new ImageIcon(scaledImage));
                        } else {
                            System.err.println("Failed to load profile picture: " + currentLecturerId);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }


                JPanel buttonsPanel = new JPanel();
                deleteButton = new JButton("Delete");
                updateButton = new JButton("Update");
                allotPMButton = new JButton("Allot PM");

                deleteButton.setFocusable(true);
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = lecturerTable.getSelectedRow();
                        if (selectedRow != -1) {
                            lecturerTableModel.removeRow(selectedRow);
                            deleteLecturer(currentLecturerId);
                            JOptionPane.showMessageDialog(AssignPMrole.this, "Lecturer record deleted.");
                            resultFrame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(AssignPMrole.this, "Please select a lecturer to delete.");
                        }
                    }
                });

                updateButton.setFocusable(true);
                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = lecturerTable.getSelectedRow();
                        if (selectedRow != -1) {
                            String newLecturerName = JOptionPane.showInputDialog(AssignPMrole.this, "Enter new Lecturer Name:");
                            String newPassword = JOptionPane.showInputDialog(AssignPMrole.this, "Enter new Lecturer Password:");
                            String[] intakeOptions = {"AGH2F2402CS", "AGH2F2402CS(AI)", "AGH2F2402CS(CYB)", "AGH2F2402CS(DA)", "AGH2F2402CS(DF)"};
                            JComboBox<String> intakeComboBox = new JComboBox<>(intakeOptions);
                            int result = JOptionPane.showConfirmDialog(AssignPMrole.this, intakeComboBox, "Select new Intake:", JOptionPane.OK_CANCEL_OPTION);

                            if (newLecturerName != null && newPassword != null && result == JOptionPane.OK_OPTION) {
                                String newIntake = (String) intakeComboBox.getSelectedItem();
                                lecturerTableModel.setValueAt(newLecturerName, selectedRow, 2);
                                lecturerTableModel.setValueAt(newPassword, selectedRow, 3);
                                lecturerTableModel.setValueAt(newIntake, selectedRow, 4);
                                updateLecturer(currentLecturerId, newLecturerName, newPassword, newIntake);
                                JOptionPane.showMessageDialog(AssignPMrole.this, "Lecturer record updated.");
                                resultFrame.dispose();
                            }

                        } else {
                            JOptionPane.showMessageDialog(AssignPMrole.this, "Please select a lecturer to update.");
                        }
                    }
                });

                allotPMButton.setFocusable(true);
                allotPMButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = lecturerTable.getSelectedRow();
                        if (selectedRow != -1) {
                            String lecturerId = (String) lecturerTableModel.getValueAt(selectedRow, 0);
                            String role = (String) lecturerTableModel.getValueAt(selectedRow, 1);
                            String lecturerName = (String) lecturerTableModel.getValueAt(selectedRow, 2);
                            String lecturerPass = (String) lecturerTableModel.getValueAt(selectedRow, 3);
                            String lecturerIntake = (String) lecturerTableModel.getValueAt(selectedRow, 4);

                            if (role.startsWith("PM")) {
                                JOptionPane.showMessageDialog(AssignPMrole.this, "Lecturer already has a project manager role.", "Already Assigned", JOptionPane.WARNING_MESSAGE);
                                return;
                            }

                        assignProjectManager(lecturerId, lecturerName, lecturerPass, lecturerIntake);
                        lecturerTableModel.removeRow(selectedRow);
                        } else {
                            JOptionPane.showMessageDialog(AssignPMrole.this, "Please select a lecturer to allot the PM role.", "No Selection", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });
                
                buttonsPanel.add(deleteButton);
                buttonsPanel.add(updateButton);
                buttonsPanel.add(allotPMButton);

                // Text area for student info
                JTextArea textArea = new JTextArea(lecturerInfo);
                textArea.setEditable(false);
                JScrollPane textScrollPane = new JScrollPane(textArea);

                // Add components to resultFrame
                resultFrame.add(textScrollPane, BorderLayout.CENTER);
                resultFrame.add(profilePicPanel2, BorderLayout.EAST);
                resultFrame.add(buttonsPanel, BorderLayout.SOUTH);

                resultFrame.setSize(500, 300);
                resultFrame.setLocationRelativeTo(AssignPMrole.this);               
                resultFrame.setVisible(true);
                return;
            }
        }
        JOptionPane.showMessageDialog(AssignPMrole.this, "Lecturer ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
    }

     private static void deleteLecturer(String lecturerId) {
        File studentIdFile = new File("lecturer_id.txt");
        List<String> lines = new ArrayList<>();
        if (studentIdFile.exists()) {
            try (Scanner fileScanner = new Scanner(studentIdFile)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    if (!line.startsWith(lecturerId)) {
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

     private static void updateLecturer(String lecturerId, String newName, String newPass, String newIntake) {
        File lecturerIdFile = new File("lecturer_id.txt");
        List<String> lines = new ArrayList<>();
        if (lecturerIdFile.exists()) {
            try (Scanner fileScanner = new Scanner(lecturerIdFile)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    if (line.startsWith(lecturerId)) {
                        line = lecturerId + "$ $" + newName + "$" + newPass + "$ $" + newIntake + "$";
                    }
                    lines.add(line);
                }
            } catch (FileNotFoundException e) {
                System.err.println("Error reading student ID file: " + e.getMessage());
            }
        }

        try (FileWriter fileWriter = new FileWriter(lecturerIdFile)) {
            for (String line : lines) {
                fileWriter.write(line + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to student ID file: " + e.getMessage());
        }
    }
    

     public void assignProjectManager(String lecturerId, String lecturerName, String lecturerPass, String lecturerIntake) {
        File lecturerIdFile = new File("lecturer_id.txt");
        List<String> lines = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(lecturerIdFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\$ \\$|\\$");
                if (parts.length == 5 && parts[0].equals(lecturerId)) {
                    if (parts[1].startsWith("PM")) {
                        JOptionPane.showMessageDialog(this, "Lecturer already has a project manager role.", "Already Assigned", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } else {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error reading lecturer ID file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String newProjectManagerId = "PM" + lecturerId.substring(2);

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith(lecturerId)) {
                lines.set(i, lecturerId + "$" + newProjectManagerId + "$" + lecturerName + "$" + lecturerPass + "$ $" + lecturerIntake + "$");
                break;
                }
        }
        try (FileWriter fileWriter = new FileWriter(lecturerIdFile, false)) {
            for (String line : lines) {
                fileWriter.write(line + "\n");
            }
            pmTableModel.addRow(new Object[]{lecturerId, "PM" + lecturerId.substring(2), lecturerName, lecturerPass, lecturerIntake});
            
            JOptionPane.showMessageDialog(this, "Project manager assigned successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to lecturer ID file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}