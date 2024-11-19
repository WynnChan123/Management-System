
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


public class LecturerLogin extends Frame implements ActionListener {
    
    private JFrame jframe;
    private JTextField lecturerIDField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JButton backButton;
    private JLabel lecturerIDLabel;
    private JLabel passwordLabel;    
    boolean secondstatus;
      
    public LecturerLogin() {
        
        jframe = new JFrame();
        jframe.setBackground(Color.gray);
        lecturerIDLabel = new JLabel("Lecturer ID:");
        lecturerIDLabel.setBounds(50,100, 75, 25);

        lecturerIDField = new JTextField();
        lecturerIDField.setBounds(125, 100, 200, 25);

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
           
        jframe.add(lecturerIDLabel);
        jframe.add(lecturerIDField);
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
        lecturerIDField.setText("");
        passwordField.setText("");
    } else if (e.getSource() == backButton) {
        Menu_Selection main = new Menu_Selection();
        jframe.dispose();
        main.setVisible(true);
    } else if (e.getSource() == loginButton) {
        String lecturerID = lecturerIDField.getText();
        String password = new String(passwordField.getPassword());
        String guiType = authenticateAndDetermineGUI(lecturerID, password);
        
        
        if (guiType != null) {
            jframe.dispose();
            String message = "Welcome, " + getUsername(lecturerID);
            JOptionPane.showMessageDialog(jframe, message);

            if (guiType.equals("Lecturer")) {
                LecturerGUI GUI = new LecturerGUI();
                GUI.setVisible(true);
            } else if (guiType.equals("SecondMarker")) {
                Marker2GUI GUI2 = new Marker2GUI();
                GUI2.setVisible(true);
            }

            this.dispose();
        } else {
            JOptionPane.showMessageDialog(jframe, "Invalid lecturer ID or password.");
        }
    }
}


public static String authenticateAndDetermineGUI(String lecturerID, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("lecturer_id.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\$");
                if ((parts.length == 5 || parts.length == 6 || parts.length == 7) && parts[0].equals(lecturerID) && parts[3].equals(password)) {
                    if (parts[4].contains("SM")) {
                        return "SecondMarker";
                    } else {
                        return "Lecturer";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
       
    public static String getUsername(String lecturerID) {
      try (BufferedReader br = new BufferedReader(new FileReader("lecturer_id.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
          String[] parts = line.split("\\$");
          if (parts.length >= 3 && parts[0].equals(lecturerID)) {
            return parts[2];
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }
 
//LT00001$ $DeHong$DH123$$ $SV00001
//LT00002$ $LianKang$LK123$SM00002$
//LT00003$PM00001$Wynn$Wynn123$ $AGH2F2402CS$SV00003
//    
//    
//    
    
}

//    public static void main(String[] args) {
//        new LecturerLogin();
//    }
//}