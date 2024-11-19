import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class ProjectLogin extends Frame implements ActionListener {
    
    private JFrame jframe;
    private JTextField PMIDField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JButton backButton;
    private JLabel PMIDLabel;
    private JLabel passwordLabel;    
      
    public ProjectLogin() {
        jframe = new JFrame();
        jframe.setBackground(Color.gray);
      
        PMIDLabel = new JLabel("Project Manager ID:");
        PMIDLabel.setBounds(50,100, 75, 25);

        PMIDField = new JTextField();
        PMIDField.setBounds(125, 100, 200, 25);

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
           
        jframe.add(PMIDLabel);
        jframe.add(PMIDField);
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
        if (e.getSource() == resetButton) {
            PMIDField.setText("");
            passwordField.setText("");
        } else if (e.getSource() == backButton){
            Menu_Selection main = new Menu_Selection();
            jframe.dispose();
            main.setVisible(true);
        } else if (e.getSource() == loginButton) {
            String PMID = PMIDField.getText();
            String password = new String(passwordField.getPassword());
            if (authenticate(PMID, password)) {
                jframe.dispose();
                String message = "Welcome, " + getUsername(PMID);
                JOptionPane.showMessageDialog(jframe, message);
                //LecturerMainGUI GUI = new LecturerMainGUI (); //change according to ur name @YEW 
                //GUI.setVisible(true);

                this.dispose();
                
            } else {
                JOptionPane.showMessageDialog(jframe, "Invalid Project Manager ID or password.");
            }
        }
    }


        public static boolean authenticate(String PMID, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("lecturer_id.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\$");
                if (parts.length == 7 && parts[1].equals(PMID) && parts[3].equals(password)) {
                    ProjectManagerGUI PMframe = new ProjectManagerGUI();
                    PMframe.equals(true);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getUsername(String PMID) {
      try (BufferedReader br = new BufferedReader(new FileReader("lecturer_id.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
          String[] parts = line.split("\\$");
          if (parts.length >= 3 && parts[1].equals(PMID)) {
            return parts[2];
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }
}