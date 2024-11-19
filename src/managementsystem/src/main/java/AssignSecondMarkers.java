import javax.swing.*;
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

public class AssignSecondMarkers extends JPanel implements ActionListener {
    private JTextField lecturerIdTextField;
    private JButton assignButton, removeButton, searchButton;
    private JTextArea lecturerIdArea, secondMarkerArea, nameArea, supervisorIdArea;

    public AssignSecondMarkers() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel idLabel = new JLabel("Lecturer ID:");
        lecturerIdTextField = new JTextField(15);

        assignButton = new JButton("Assign Second Marker");
        removeButton = new JButton("Remove Second Marker");
        searchButton = new JButton("Search Lecturer");

        assignButton.addActionListener(this);
        removeButton.addActionListener(this);
        searchButton.addActionListener(this);

        lecturerIdArea = new JTextArea(1, 20);
        lecturerIdArea.setEditable(false);
        secondMarkerArea = new JTextArea(1, 20);
        secondMarkerArea.setEditable(false);
        nameArea = new JTextArea(1, 20);
        nameArea.setEditable(false);
        supervisorIdArea = new JTextArea(1, 20);
        supervisorIdArea.setEditable(false);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(lecturerIdTextField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(searchButton, gbc);

        // Inside the constructor
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Lecturer ID:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Name:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Second Marker ID:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Supervisor ID:"), gbc);

        // After setting the position of JTextArea components
         gbc.gridx = 1;
        gbc.gridy = 1;
        add(lecturerIdArea, gbc);

        gbc.gridy = 2;
        add(nameArea, gbc);

        gbc.gridy = 3;
        add(secondMarkerArea, gbc);

        gbc.gridy = 4;
        add(supervisorIdArea, gbc);

        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(assignButton, gbc);

        gbc.gridx = 2;
        add(removeButton, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String lecturerId = lecturerIdTextField.getText().trim();
        if (e.getSource() == searchButton) {
            if (lecturerId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Lecturer ID.");
                clearLecturerDetails();
            } else {
                searchLecturer(lecturerId);
            }
        } else if (e.getSource() == assignButton) {
            assignSecondMarkersRole(lecturerId);
        } else if (e.getSource() == removeButton) {
            removeSecondMarkersRole(lecturerId);
        }
    }

    private void searchLecturer(String lecturerId) {
        File lecturerIdFile = new File("lecturer_id.txt");
        if (!lecturerIdFile.exists()) {
            JOptionPane.showMessageDialog(this, "Error: Lecturer ID file not found.");
            return;
        }

        boolean lecturerExists = false;
        String secondMarkerId = "";
        String lecturerName = "";
        String supervisorId = "";

        try (Scanner fileScanner = new Scanner(lecturerIdFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\$");
                if (parts[0].equals(lecturerId)) {
                    lecturerExists = true;
                    lecturerName = parts[2].trim(); 
                    secondMarkerId = parts[4].trim();
                    if (parts.length > 6) {
                    supervisorId = parts[6].trim();
                    }
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error reading lecturer ID file: " + ex.getMessage());
        }

        if (lecturerExists) {
            lecturerIdArea.setText(lecturerId);
            nameArea.setText(lecturerName);
            secondMarkerArea.setText(secondMarkerId);
            supervisorIdArea.setText(supervisorId);
        } else {
            JOptionPane.showMessageDialog(this, "Error: Lecturer ID does not exist.");
            clearLecturerDetails();
        }
    }

    private void clearLecturerDetails() {
        lecturerIdArea.setText("");
        nameArea.setText("");
        secondMarkerArea.setText("");
        supervisorIdArea.setText("");
    }

    private void assignSecondMarkersRole(String lecturerId) {
        File lecturerIdFile = new File("lecturer_id.txt");
        if (!lecturerIdFile.exists()) {
            JOptionPane.showMessageDialog(this, "Error: Lecturer ID file not found.");
            return;
        }

        boolean lecturerExists = false;
        boolean hasSecondMarkersRole = false;
        List<String> lines = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(lecturerIdFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\$");
                if (parts[0].equals(lecturerId)) {
                    lecturerExists = true;
                    if (parts.length > 6 && parts[4].startsWith("SM")) {
                        hasSecondMarkersRole = true;
                    }
                }
                lines.add(line);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error reading lecturer ID file: " + ex.getMessage());
        }

        if (lecturerExists) {
            if (!hasSecondMarkersRole) {
                String newSecondMarkerId = "SM" + lecturerId.substring(2);

                for (int i = 0; i < lines.size(); i++) {
                    String[] parts = lines.get(i).split("\\$");
                    if (parts[0].equals(lecturerId)) {
                        lines.set(i, parts[0] + "$" + parts[1] + "$" + parts[2] + "$" + parts[3] + "$" + newSecondMarkerId + (parts.length > 6 ? "$" + parts[5] : "") + (parts.length > 6 ? "$" + parts[6] : ""));
                        break;
                    }
                }

                try (FileWriter fileWriter = new FileWriter(lecturerIdFile, false)) {
                    for (String line : lines) {
                        fileWriter.write(line + "\n");
                    }
                    JOptionPane.showMessageDialog(this, "Second Marker assigned successfully.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error writing to lecturer ID file: " + ex.getMessage());
                }

            } else {
                JOptionPane.showMessageDialog(this, "Error: Lecturer already has a second markers role.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error: Lecturer ID does not exist.");
        }
    }

    private void removeSecondMarkersRole(String lecturerId) {
        File lecturerIdFile = new File("lecturer_id.txt");
        if (!lecturerIdFile.exists()) {
            JOptionPane.showMessageDialog(this, "Error: Lecturer ID file not found.");
            return;
        }

        boolean lecturerExists = false;
        boolean hasSecondMarkersRole = false;
        List<String> lines = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(lecturerIdFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\$");
                if (parts[0].equals(lecturerId)) {
                    lecturerExists = true;
                    if (parts.length > 4 && parts[4].startsWith("SM")) {
                        hasSecondMarkersRole = true;
                        parts[4] = ""; // Remove second marker role
                        }
                        lines.add(String.join("$", parts));
                    } else {
                        lines.add(line);
                    }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error reading lecturer ID file: " + ex.getMessage());
        }

        if (lecturerExists) {
            if (hasSecondMarkersRole) {
                try (FileWriter fileWriter = new FileWriter(lecturerIdFile, false)) {
                    for (String line : lines) {
                        fileWriter.write(line + "\n");
                    }
                    JOptionPane.showMessageDialog(this, "Second Marker role removed successfully.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error writing to lecturer ID file: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error: Lecturer does not have a second markers role.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error: Lecturer ID does not exist.");
        }
    }
}