import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;

public class Students extends JFrame {
    public Students(String studentId, String intake) {
        // Set the title and icon
        super("Students");
        ImageIcon image = new ImageIcon("Ologo.jpg");
        this.setIconImage(image.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 850);
        this.setLocationRelativeTo(null);
        
        // Set the background color
        this.getContentPane().setBackground(Color.decode("#c8ff6d"));
        this.setLayout(null);
        
        // Read the image file
        Image bgImage = null;
        try {
            bgImage = ImageIO.read(new File("AGH.jpg"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // Create the tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(100, 100, 900, 700);
        
        // Create panels for each tab
        JPanel panel1 = new RSst(studentId, intake);
        JPanel panel2 = new RPDst(studentId, intake);
        JPanel panel3 = new RCst(studentId);
        JPanel panel4 = new FBst(studentId, intake);
        
        // Add the panels to the tabbed pane
        tabbedPane.addTab("Report Submission", panel1);
        tabbedPane.addTab("Request Presentation Date", panel2);
        tabbedPane.addTab("Check Result", panel3);
        tabbedPane.addTab("Feedback", panel4);
        
        // Add the tabbed pane to the frame
        this.add(tabbedPane);
        
        // Set the background image
        if (bgImage != null) {
            JLabel background = new JLabel(new ImageIcon(bgImage));
            background.setBounds(0, 0, bgImage.getWidth(null), bgImage.getHeight(null));
            this.add(background);
        }
        
        // Add Logout Button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(880, 10, 100, 30);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        this.add(logoutButton);
    }
    
    private void logout() {
        this.dispose(); // Close the current window
        Menu_Selection MSfrmae = new Menu_Selection();
        MSfrmae.setVisible(true);
    }
}
