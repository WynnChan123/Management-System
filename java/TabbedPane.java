import javax.swing.*;
import java.awt.*;

public class TabbedPane extends JFrame {
    
    public TabbedPane(){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setResizable(false);
        
        JTabbedPane tabbed= new JTabbedPane();
        
        JPanel tab1 = new JPanel();
        tab1.add(new JLabel("Tab 1 Contect"));
        tabbed.addTab("Tab 1",tab1);
        
        JPanel tab2 = new JPanel();
        tab2.add(new JLabel("Tab 2 Content"));
        tabbed.addTab("Tab 2", tab2);
        
        add(tabbed, BorderLayout.CENTER);
        
        setLocationRelativeTo(null);//set at centre of the screen
        
        setVisible(true);
        
    }
}