import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Menu_Selection extends JFrame implements ActionListener {

    private JPanel jpanel;
    private JButton ADbutton;
    private JButton STbutton;
    private JButton LCbutton;
    private JButton PMbutton;
    private JLabel jlabel;
    private JLabel timeLabel; // Label to display time
    private boolean closedByUser = false;

    public Menu_Selection() {
        setTitle("Academic Guidance Hub");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Modified

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closedByUser = true;
                dispose(); // Close the window
            }
        });
        
        // Main panel with null layout
        jpanel = new JPanel(); 
        jpanel.setLayout(null);
        jpanel.setBackground(new Color(204, 255, 118));
        add(jpanel);
        
        // Label
        JLabel jlabel = new JLabel("Welcome User");
        jlabel.setFont(new Font("Your preferred font name", Font.BOLD, 20)); // Set font and size
        jlabel.setBounds(155, 1, 150, 150); // Set bounds for label
        jpanel.add(jlabel);

        
        // Buttons
        ADbutton = new JButton("Admin");
        STbutton = new JButton("Student");
        LCbutton = new JButton("Lecturer");
        PMbutton = new JButton("Project Manager");
        
        // Set bounds for buttons
        int buttonWidth = 200;
        int buttonHeight = 50;
        int buttonX = (450 - buttonWidth) / 2;
        int buttonY = (450 - buttonHeight * 3 - 20 * 2) / 2; 
        ADbutton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        STbutton.setBounds(buttonX, buttonY + buttonHeight + 20, buttonWidth, buttonHeight); // Add 20 pixels gap
        LCbutton.setBounds(buttonX, buttonY + 2 * (buttonHeight + 20), buttonWidth, buttonHeight); // Add 20 pixels gap
        PMbutton.setBounds(buttonX, buttonY + 2 * (buttonHeight + 55), buttonWidth, buttonHeight); 
        
        // Add buttons to the panel
        jpanel.add(ADbutton);
        jpanel.add(STbutton);
        jpanel.add(LCbutton);
        jpanel.add(PMbutton);
        
        // Add action listeners to buttons
        ADbutton.addActionListener(this);
        STbutton.addActionListener(this);
        LCbutton.addActionListener(this);
        PMbutton.addActionListener(this);
        
        // Set button properties
        ADbutton.setFocusable(false);
        STbutton.setFocusable(false);
        LCbutton.setFocusable(false);
        PMbutton.setFocusable(false);
        
        // Create and style time label
        timeLabel = new JLabel();
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        updateTime(); // Update time initially
        jpanel.add(timeLabel); // Add time label to the panel
        timeLabel.setBounds(0, 330, 300, 250);
        
        pack(); // Adjust frame size to fit components
        setSize(450, 500); // Set frame size
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true); // Make the frame visible
    }
    
    
    // Method to update the time
    private void updateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = now.format(formatter);
        timeLabel.setText("Current Time: " + formattedTime);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ADbutton) {
            getContentPane().removeAll();
            revalidate();
            AdminLogin adminlogin = new AdminLogin();
            revalidate();
            repaint();
            this.dispose();
        }
        if (e.getSource() == STbutton) {
            getContentPane().removeAll();
            revalidate();
            StudentLogin studentlogin = new StudentLogin(); 
            revalidate();
            repaint();
            this.dispose();
        }
        if (e.getSource() == LCbutton) {
            getContentPane().removeAll();
            revalidate();
            LecturerLogin lecturerlogin = new LecturerLogin();
            revalidate();
            repaint();
            this.dispose();
        }
        if (e.getSource() == PMbutton) {
            getContentPane().removeAll();
            revalidate();
            ProjectLogin projectlogin = new ProjectLogin();
            revalidate();
            repaint();
            this.dispose();     
        }
    }
    
    public boolean isClosedByUser() {
        return closedByUser;
    }
}
