import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectManagerGUI {

    public ProjectManagerGUI() {
        // Create the frame
        JFrame frame = new JFrame("Custom GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        
        // Set the background color of the frame
        frame.getContentPane().setBackground(Color.decode("#c8ff6d"));
        frame.setLayout(null);

        // Create the tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(100, 100, 850, 650);
        
        // Create panels for each tab
        AllotStudents panel1 = new AllotStudents();
        AssignSupervisor panel2 = new AssignSupervisor();
        AssignSecondMarkers panel3 = new AssignSecondMarkers();
        Report panel4 = new Report();

        // Add the panels to the tabbed pane
        tabbedPane.addTab("Allot Students", panel1);
        tabbedPane.addTab("Assign Supervisor", panel2);
        tabbedPane.addTab("Assign Second Markers", panel3);
        tabbedPane.addTab("Report", panel4);

        // Add the tabbed pane to the frame
        frame.add(tabbedPane);
        
        // Create Log Out Button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.setBounds(880, 10, 100, 30);
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Logged Out Successfully!");
                frame.dispose(); // Close the current window
                Menu_Selection SMframe = new Menu_Selection();
            }
        });
        frame.add(logoutButton);

        // Add AGH logo
        JLabel logoLabel = new JLabel();
        ImageIcon aghLogo = loadImageIcon("AGH.png");
        if (aghLogo != null) {
            logoLabel.setIcon(aghLogo);
        }
        logoLabel.setBounds(0, 0, 150, 60); // Adjust bounds as necessary
        frame.add(logoLabel);

        // Set Welcome message and Lecturer name
        JLabel welcomeLabel = new JLabel("Welcome Project Manager");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        welcomeLabel.setBounds(200, 50, 600, 50);
        frame.add(welcomeLabel);
        
        // Make the frame visible
        frame.setVisible(true);
    }

    private static ImageIcon loadImageIcon(String path) {
        java.net.URL imgURL = ProjectManagerGUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}