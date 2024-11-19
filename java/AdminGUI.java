import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;

public class AdminGUI extends JFrame {
    private BufferedImage image;
    private static String lastStudentId = "ST00000";
    private static String lastLecturerId = "LT00000";
    private final DefaultTableModel pmTableModel, lecturerTableModel;
    private JComboBox<String> studentIntakeComboBox;
    private JComboBox<String> lecturerIntakeComboBox;
    private JLabel profilePicLabel;
    private File profilePicFile;
    private JLabel profilePicLabel2;
    private File profilePicFile2;   
    private Menu_Selection menu;

    public AdminGUI() {
        this.setTitle("Academic Guidance Hub");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800); 
        this.setLocationRelativeTo(null);
        // Set the background color of the frame
        this.getContentPane().setBackground(Color.decode("#c8ff6d"));
        this.setLayout(null);

        // Load the image
        try {
            image = ImageIO.read(new File("AGH.jpg")); // Read from image path
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize pmTableModel and lecturerTableModel
        pmTableModel = new DefaultTableModel();
        lecturerTableModel = new DefaultTableModel();
        
        // Create the tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(100, 100, 900, 700);

        // Creating an instance of AssignPMrole
        AssignPMrole assignPMrole = new AssignPMrole(pmTableModel);
        ManagePMRole managePMrole = new ManagePMRole(lecturerTableModel);
        
        
        // Add the panels to the tabbed pane
        tabbedPane.addTab("Register Students", studentRegistration());
        tabbedPane.addTab("Register Lecturers", lecturerRegistration());
        tabbedPane.addTab("Edit/Delete Student Details", new StudentRecord().getPanel());
        tabbedPane.addTab("View Lecturer Details", assignPMrole.getPanel());
        tabbedPane.addTab("Remove PM role", managePMrole.getPanel());

        // Add the tabbed pane to the frame
        this.add(tabbedPane);

          // Create "Log Out" button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.setBounds(880, 10, 100, 30);      
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Logged Out Successfully!");
                dispose(); // Close the current window
                menu = new Menu_Selection();
            }
        });
        this.add(logoutButton);

        
        // Create "Welcome Admin" label
        JLabel welcomeLabel = new JLabel("Welcome Admin"  , SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        welcomeLabel.setBounds(200, 50, 600, 50);
        this.add(welcomeLabel);
        
        
        
        // Make the frame visible
        this.setVisible(true);
    }

    private JPanel studentRegistration() {
        JPanel studentRegisterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Profile picture label
        profilePicLabel = new JLabel();
        profilePicLabel.setPreferredSize(new Dimension(150, 150));
        profilePicLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        studentRegisterPanel.add(profilePicLabel, gbc);

        // Upload button for profile picture
        JButton uploadButton = new JButton("Upload Picture");
        gbc.gridx = 1;
        studentRegisterPanel.add(uploadButton, gbc);

        // Action listener for upload button
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    profilePicFile = fileChooser.getSelectedFile();
                    try {
                        BufferedImage profilePic = ImageIO.read(profilePicFile);
                        Image scaledImage = profilePic.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                        profilePicLabel.setIcon(new ImageIcon(scaledImage));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;

        JLabel nameLabel = new JLabel("Student's Name:");
        JTextField nameField = new JTextField(20);
        JLabel passLabel = new JLabel("Student's Password:");
        JPasswordField passField = new JPasswordField(20);
        JLabel intakeLabel = new JLabel("Student's Intake:");
        String[] Intakes = {"AGH2F2402CS", "AGH2F2402CS(AI)", "AGH2F2402CS(CYB)", "AGH2F2402CS(DA)", "AGH2F2402CS(DF)"};
        studentIntakeComboBox = new JComboBox<>(Intakes);
        JButton registerButton = new JButton("Register Student");

        studentRegisterPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        studentRegisterPanel.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        studentRegisterPanel.add(passLabel, gbc);
        gbc.gridx = 1;
        studentRegisterPanel.add(passField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        studentRegisterPanel.add(intakeLabel, gbc);
        gbc.gridx = 1;
        studentRegisterPanel.add(studentIntakeComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        studentRegisterPanel.add(registerButton, gbc);

        // Load the last student ID from the file
        loadLastStudentId();

        // Add action listener for the register button
        registerButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String studentName = nameField.getText();
              String studentPass = new String(passField.getPassword());
              String studentIntake = (String) studentIntakeComboBox.getSelectedItem();

              System.out.println("Selected Intake: " + studentIntake); // Debugging statement

              // Generate new student ID
              int lastIdNum = Integer.parseInt(lastStudentId.substring(lastStudentId.length() - 5));
              int currentIdNum = lastIdNum + 1;
              String newStudentId = String.format("ST%05d", currentIdNum);

              // Combine student name and ID
              String studentInfo = newStudentId + "$" + studentName + "$" + studentPass + "$" + studentIntake + "$" + "\n";

              // Write student info to file
              try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("student_id.txt", true))) {
                  fileWriter.write(studentInfo);
                  JOptionPane.showMessageDialog(null, "Student information saved: " + studentInfo);
              } catch (IOException ex) {
                  JOptionPane.showMessageDialog(null, "Error writing to student ID file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
              }

              // Save the profile picture
              if (profilePicFile != null) {
                  try {
                      File destFile = new File("profile_pics/" + newStudentId + ".png");
                      destFile.getParentFile().mkdirs();
                      ImageIO.write(ImageIO.read(profilePicFile), "png", destFile);
                  } catch (IOException ex) {
                      ex.printStackTrace();
                  }
              }

              // Update the last student ID
              lastStudentId = newStudentId;
            }
        });

        return studentRegisterPanel;
    }

    private static void loadLastStudentId() {
        File studentIdFile = new File("student_id.txt");
        if (studentIdFile.exists()) {
            try (Scanner fileScanner = new Scanner(studentIdFile)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    if (!line.trim().isEmpty()) {
                        lastStudentId = line.split("\\$")[0];
                    }
                }
                System.out.println("Last Student ID: " + lastStudentId);
            } catch (FileNotFoundException e) {
                System.err.println("Error reading student ID file: " + e.getMessage());
            }
        }
    }

    private JPanel lecturerRegistration() {
        JPanel lecturerRegisterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Profile picture label
        profilePicLabel2 = new JLabel();
        profilePicLabel2.setPreferredSize(new Dimension(150, 150));
        profilePicLabel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lecturerRegisterPanel.add(profilePicLabel2, gbc);

        // Upload button for profile picture
        JButton uploadButton2 = new JButton("Upload Picture");
        gbc.gridx = 1;
        lecturerRegisterPanel.add(uploadButton2, gbc);

        // Action listener for upload button
        uploadButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    profilePicFile2 = fileChooser.getSelectedFile();
                    try {
                        BufferedImage profilePic = ImageIO.read(profilePicFile2);
                        Image scaledImage = profilePic.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                        profilePicLabel2.setIcon(new ImageIcon(scaledImage));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;

        
        JLabel nameLabel = new JLabel("Lecturer's Name:");
        JTextField nameField = new JTextField(20);
        JLabel passLabel = new JLabel("Lecturer's Password:");
        JPasswordField passField = new JPasswordField(20);
        JLabel intakeLabel = new JLabel("Lecturer's Intake:");
        String[] Intakes = {"AGH2F2402CS", "AGH2F2402CS(AI)", "AGH2F2402CS(CYB)", "AGH2F2402CS(DA)", "AGH2F2402CS(DF)"};
        lecturerIntakeComboBox = new JComboBox<>(Intakes);
        JButton registerButton2 = new JButton("Register Lecturer");

        lecturerRegisterPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        lecturerRegisterPanel.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        lecturerRegisterPanel.add(passLabel, gbc);
        gbc.gridx = 1;
        lecturerRegisterPanel.add(passField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        lecturerRegisterPanel.add(intakeLabel, gbc);
        gbc.gridx = 1;
        lecturerRegisterPanel.add(lecturerIntakeComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        lecturerRegisterPanel.add(registerButton2, gbc);

        // Load the last lecturer ID from the file
        loadLastLecturerId();

        // Add action listener for the register button
        registerButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lecturerName = nameField.getText();
                String lecturerPass = new String(passField.getPassword());
                String lecturerIntake = (String) lecturerIntakeComboBox.getSelectedItem();

                // Generate new lecturer ID
                int lastIdNum = Integer.parseInt(lastLecturerId.substring(lastLecturerId.length() - 5));
                int currentIdNum = lastIdNum + 1;
                String newLecturerId = String.format("LT%05d", currentIdNum);
                
                // Combine lecturer name and ID
                String lecturerInfo = newLecturerId + "$ $" + lecturerName + "$" + lecturerPass + "$ $" + lecturerIntake + "$" + "\n";
                
                // Write lecturer info to file
                try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("lecturer_id.txt", true))) {
                    fileWriter.write(lecturerInfo);
                    JOptionPane.showMessageDialog(null, "Lecturer information saved: " + lecturerInfo);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error writing to lecturer ID file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                // Save the profile picture
                if (profilePicFile2 != null) {
                    try {
                        File destFile = new File("profile_pics/" + newLecturerId + ".png");
                        destFile.getParentFile().mkdirs();
                        ImageIO.write(ImageIO.read(profilePicFile2), "png", destFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                
                // Update the last lecturer ID
                lastLecturerId = newLecturerId;
            }
        });

        return lecturerRegisterPanel;
    }

    private static void loadLastLecturerId() {
        File lecturerIdFile = new File("lecturer_id.txt");
        if (lecturerIdFile.exists()) {
            try (Scanner fileScanner = new Scanner(lecturerIdFile)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    if (!line.trim().isEmpty()) {
                        lastLecturerId = line.split("\\$")[0];
                    }
                }
                System.out.println("Last Lecturer ID: " + lastLecturerId);
            } catch (FileNotFoundException e) {
                System.err.println("Error reading lecturer ID file: " + e.getMessage());
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (image != null) {
            g.drawImage(image, 0, 50, this);
        }
    }
}