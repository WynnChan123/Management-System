import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagePMRole extends JPanel {
    private final JPanel panelView;
    private final JTable pmTable;
    private final DefaultTableModel pmTableModel, lecturerTableModel;
    private JButton deleteButton, updateButton;
    private final JButton searchPMButton, removePMButton;
    private final JTextField searchPMField;
    private String currentLecturerId;

    public ManagePMRole(DefaultTableModel lecturerTableModel) {

        // View Project Managers Panel
        this.lecturerTableModel = lecturerTableModel;
        this.panelView = new JPanel(new BorderLayout());
        String[] columnHeaders = {"Lecturer ID", "Project Manager ID", "Lecturer Name", "Password", "Intake"};
        pmTableModel = new DefaultTableModel(columnHeaders, 0);
        pmTable = new JTable(pmTableModel);
        JScrollPane scrollPane = new JScrollPane(pmTable);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPMField = new JTextField(20);
        searchPMButton = new JButton("Search for PM");
        searchPMButton.addActionListener(new SearchPMActionListener());
        removePMButton = new JButton("Remove PM Role");
        removePMButton.addActionListener(new RemovePMRoleActionListener());

        searchPanel.add(searchPMField);
        searchPanel.add(searchPMButton);
        searchPanel.add(removePMButton);

        panelView.add(searchPanel, BorderLayout.NORTH);
        panelView.add(scrollPane, BorderLayout.CENTER);


        loadProjectManagers();

    }
    
    public JPanel getPanel() {
        return panelView;
    }

    private void loadProjectManagers() {
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
                    pmTableModel.addRow(rowData);
                }else {
                    System.out.println("Lecturer: " + line); // Add this line for debugging
                    lecturerTableModel.addRow(rowData);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading lecturer ID file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
    
    
    private class SearchPMActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchId = searchPMField.getText().trim();
            if (searchId.isEmpty()) {
                JOptionPane.showMessageDialog(ManagePMRole.this, "Please enter a Lecturer ID to search.", "No Input", JOptionPane.WARNING_MESSAGE);
                return;
            }

            pmTableModel.setRowCount(0);  // Clear existing rows

            File lecturerIdFile = new File("lecturer_id.txt");
            if (!lecturerIdFile.exists()) {
                JOptionPane.showMessageDialog(ManagePMRole.this, "Error: Lecturer ID file not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Scanner fileScanner = new Scanner(lecturerIdFile)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] parts = line.split("\\$ \\$|\\$");
                    if (parts.length == 5 && parts[0].equals(searchId) && parts[1].startsWith("PM")) {
                        pmTableModel.addRow(parts);
                        
                        String lecturerInfo = "Lecturer's ID: " + parts[0] +"\n"
                                + "Project Manager ID: " + parts[1] + "\n"
                                + "Lecturer's Name: " + parts[2] + "\n"
                                + "Lecturer's Password: " + parts[3] + "\n"
                                + "Lecturer's Intake: " + parts[4];
                        
                        currentLecturerId = searchId;
                        
                        JFrame resultFrame = new JFrame("Project Manager Details");
                        deleteButton = new JButton("Delete");
                        updateButton = new JButton("Update");
                        
                        resultFrame.add(deleteButton);
                        deleteButton.setBounds(30, 100, 80, 30);
                        deleteButton.setFocusable(true);
                        deleteButton.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int selectedRow = pmTable.getSelectedRow();
                                if (selectedRow != -1){
                                    pmTableModel.removeRow(selectedRow);
                                    deleteLecturer(currentLecturerId);
                                    JOptionPane.showMessageDialog(ManagePMRole.this, "Project Manager record deleted.");
                                }else{
                                    JOptionPane.showMessageDialog(ManagePMRole.this, "Please select a project manager to delete");
                                }
                            }
                            
                        });
                        
                        resultFrame.add(updateButton);
                        updateButton.setBounds(110,100,80,30);
                        updateButton.setFocusable(true);
                        updateButton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int selectedRow = pmTable.getSelectedRow();
                            if (selectedRow != -1) {
                                String newLecturerName = JOptionPane.showInputDialog(ManagePMRole.this, "Enter new Lecturer Name:");
                                String newPassword = JOptionPane.showInputDialog(ManagePMRole.this, "Enter new Lecturer Password:");
                                // Create JComboBox for intake selection
                                String[] intakeOptions = {"AGH2F2402CS", "AGH2F2402CS(AI)", "AGH2F2402CS(CYB)", "AGH2F2402CS(DA)", "AGH2F2402CS(DF)"};
                                JComboBox<String> intakeComboBox = new JComboBox<>(intakeOptions);
                                int result = JOptionPane.showConfirmDialog(ManagePMRole.this, intakeComboBox, "Select new Intake:", JOptionPane.OK_CANCEL_OPTION);

                                if (newLecturerName != null && newPassword != null && result == JOptionPane.OK_OPTION) {
                                    String newIntake = (String) intakeComboBox.getSelectedItem();
                                    pmTableModel.setValueAt(newLecturerName, selectedRow, 2);
                                    pmTableModel.setValueAt(newPassword, selectedRow, 3);
                                    pmTableModel.setValueAt(newIntake, selectedRow, 4);
                                    updateLecturer(currentLecturerId, newLecturerName, newPassword, newIntake);
                                    JOptionPane.showMessageDialog(ManagePMRole.this, "Project Manager record updated.");
                                }

                            } else {
                                JOptionPane.showMessageDialog(ManagePMRole.this, "Please select a project manager to update.");
                            }
                        }
                    });
                        
                        resultFrame.setSize(300,200);
                        resultFrame.setLocationRelativeTo(ManagePMRole.this);
                        
                        JTextArea textArea = new JTextArea(lecturerInfo);
                        textArea.setEditable(false);
                        resultFrame.add(new JScrollPane(textArea));
                        
                        resultFrame.setVisible(true);
                        return;
                        
                    }
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(ManagePMRole.this, "Error reading lecturer ID file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    private class RemovePMRoleActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = pmTable.getSelectedRow();
            if (selectedRow != -1) {
                String lecturerId = (String) pmTableModel.getValueAt(selectedRow, 0);
                String role = (String) pmTableModel.getValueAt(selectedRow, 1);
                String lecturerName = (String) pmTableModel.getValueAt(selectedRow, 2);
                String lecturerPass = (String) pmTableModel.getValueAt(selectedRow, 3);
                String lecturerIntake = (String) pmTableModel.getValueAt(selectedRow, 4);
                removeProjectManager(lecturerId, lecturerName, lecturerPass, lecturerIntake);
                pmTableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(ManagePMRole.this, "Please select a project manager to remove the PM role.", "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        }
        
    }
    
        public void removeProjectManager(String lecturerId, String lecturerName, String lecturerPass, String lecturerIntake) {
           File lecturerIdFile = new File("lecturer_id.txt");
           List<String> lines = new ArrayList<>();

           try (Scanner fileScanner = new Scanner(lecturerIdFile)) {
               while (fileScanner.hasNextLine()) {
                   String line = fileScanner.nextLine();
                   String[] parts = line.split("\\$ \\$|\\$");
                   if (parts.length == 5 && parts[0].equals(lecturerId) && parts[1].startsWith("PM")) {
                       lines.add(lecturerId + "$ $" + lecturerName + "$" + lecturerPass + "$ $" + lecturerIntake + "$");
                   } else {
                       lines.add(line);
                   }
               }
           } catch (FileNotFoundException e) {
               JOptionPane.showMessageDialog(this, "Error reading lecturer ID file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
               return;
           }

            try (FileWriter fileWriter = new FileWriter(lecturerIdFile, false)) {
                for (String line : lines) {
                   fileWriter.write(line + "\n");
                }
               lecturerTableModel.addRow(new Object[]{lecturerId, "", lecturerName, lecturerPass, lecturerIntake});
               JOptionPane.showMessageDialog(this, "Project manager role removed successfully.");
           } catch (IOException e) {
               JOptionPane.showMessageDialog(this, "Error writing to lecturer ID file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
           }
       }
}