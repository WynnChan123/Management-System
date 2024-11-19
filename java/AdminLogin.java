
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class AdminLogin extends JFrame implements ActionListener{
    
    private JFrame jframe;
    private JTextField adminIDField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JLabel adminIDLabel;
    private JButton backButton;
    private JLabel passwordLabel;
    
    public AdminLogin() {
        jframe = new JFrame();
        jframe.setBackground(Color.gray);

        adminIDLabel = new JLabel("Admin ID:");
        adminIDLabel.setBounds(50,100, 75, 25);

        adminIDField = new JTextField();
        adminIDField.setBounds(125, 100, 200, 25);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50,150,75, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(125, 150, 200, 25);

        loginButton = new JButton("Login");
        loginButton.setBounds(125, 200, 100, 25);
        loginButton.addActionListener(this);
        
        resetButton = new JButton("Reset");
        resetButton.setBounds(225, 200, 100, 25);
        resetButton.addActionListener(this);
        
        backButton = new JButton ("Back");
        backButton.setBounds(125, 225, 200, 25);  //add previous declaration and below calling and this and should be fine 
        backButton.addActionListener(this);
        jframe.add(backButton);
        
        jframe.add(adminIDLabel);
        jframe.add(adminIDField);
        jframe.add(passwordLabel);
        jframe.add(passwordField);
        jframe.add(loginButton);
        jframe.add(resetButton);
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        jframe.setTitle("Login");
        jframe.setSize(450, 450);
        jframe.setLocationRelativeTo(null);
        jframe.setLayout(null);
        jframe.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == resetButton) {
            adminIDField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == backButton){
            Menu_Selection main = new Menu_Selection();
            jframe.dispose();
            main.setVisible(true);
        }
        if (e.getSource() == loginButton) {
            String adminID = adminIDField.getText();
            String password = new String(passwordField.getPassword());

            if (authenticate(adminID, password)) {
                jframe.dispose();
                JOptionPane.showMessageDialog(jframe, "Welcome, user " + getUsername(adminID));
                //instantiate admin's welcome page
            } else {
                JOptionPane.showMessageDialog(jframe, "Invalid admin ID or password.");
            }
        }
    }

    private boolean authenticate(String adminID, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("admin_id.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\$");
                if (parts.length == 3 && parts[0].equals(adminID) && parts[2].equals(password)) {
                    AdminGUI ADframe = new AdminGUI();
                    ADframe.setVisible(true);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getUsername(String adminID) {
        try (BufferedReader br = new BufferedReader(new FileReader("admin_id.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\$");
                if (parts.length == 3 && parts[0].equals(adminID)) {
                    return parts[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}