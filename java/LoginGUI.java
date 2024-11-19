
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class LoginGUI extends JFrame implements ActionListener {
    
    private JFrame jframe;
    private JTextField studentIDField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JLabel studentIDLabel;
    private JLabel passwordLabel;

    public LoginGUI() {
        jframe = new JFrame();

      
        studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setBounds(50,100, 75, 25);

        studentIDField = new JTextField();
        studentIDField.setBounds(125, 100, 200, 25);

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

        
        jframe.add(studentIDLabel);
        jframe.add(studentIDField);
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
            studentIDField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == loginButton) {
            String studentID = studentIDField.getText();
            String password = new String(passwordField.getPassword());

            if (authenticate(studentID, password)) {
                jframe.dispose();
                JOptionPane.showMessageDialog(jframe, "Welcome, user " + getUsername(studentID));
                //instantiate student's welcome page
            } else {
                JOptionPane.showMessageDialog(jframe, "Invalid student ID or password.");
            }
        }
    }

    private boolean authenticate(String studentID, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("student_id.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\$");
                if (parts.length == 3 && parts[0].equals(studentID) && parts[2].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getUsername(String studentID) {
        try (BufferedReader br = new BufferedReader(new FileReader("student_id.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\$");
                if (parts.length == 3 && parts[0].equals(studentID)) {
                    return parts[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}