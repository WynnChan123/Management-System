import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;


public class SecondMarkerGUI extends javax.swing.JFrame {
    public SecondMarkerGUI() {
        initComponents();
        Color col = new Color(204, 255, 118);
        getContentPane().setBackground(col);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        SecondMarker = new java.awt.Label();
        wallpaper = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(204, 255, 118));
        //getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Supervisee ID", "Status", "Date", "Intake", "Assessment type"
            }
        ) {
            boolean[] canEdit = new boolean[] {
                false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        try {
            readDataFromFile("supervisee.txt", (DefaultTableModel) jTable1.getModel());
        } catch (IOException e) {
            // Handle file reading error
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading file: supervisee.txt");
        };
        //);
    jTable1.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(jTable1);
    if (jTable1.getColumnModel().getColumnCount() > 0) {
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(13);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(15);
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setResizable(false);
    }

    jButton3.setText("Search ");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });

    jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
    jLabel1.setText("Search by Supervisee ID");

    jTextField1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
    jTextField1.setText("SuperviseeID");
    jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            jTextField1FocusGained(evt);
        }
        public void focusLost(java.awt.event.FocusEvent evt) {
            jTextField1FocusLost(evt);
        }
    });
    jTextField1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextField1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3)))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton3))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
            .addContainerGap())
    );

    jTabbedPane2.addTab("Supervisee Dashboard", jPanel4);

    jPanel5.setBackground(new java.awt.Color(255, 255, 255));

    jTable2.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {},
        new String[] {
            "Presentation ID", "Subject", "Date", "Time", "Status"
        }
    ) {
        boolean[] canEdit = new boolean[] {
            false, false, false, false, true
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    });

    jTable2.getTableHeader().setReorderingAllowed(false);
    try {
        readDataFromFile("presentation.txt", (DefaultTableModel) jTable2.getModel());
    } catch (IOException e) {
        // Handle file reading error
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error reading file: presentation.txt");
    };

    jTable2.getModel().addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent e) {
            if (e.getType() == TableModelEvent.UPDATE) {
                try {
                    saveTableDataToFile("presentation.txt");
                } catch (IOException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error saving data to file");
                }
            }
        }
    });

    //);
    jScrollPane2.setViewportView(jTable2);
    if (jTable2.getColumnModel().getColumnCount() > 0) {
        jTable2.getColumnModel().getColumn(0).setResizable(false);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable2.getColumnModel().getColumn(1).setResizable(false);
        jTable2.getColumnModel().getColumn(2).setResizable(false);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(13);
        jTable2.getColumnModel().getColumn(3).setResizable(false);
        jTable2.getColumnModel().getColumn(3).setPreferredWidth(13);
        jTable2.getColumnModel().getColumn(4).setResizable(false);
    }

    jLabel2.setText("*Presentation time allocated is limited to 1 hour");

    jLabel14.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
    jLabel14.setText("Search by Presentation ID");

    jTextField2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
    jTextField2.setText("PresentationID");
    jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            jTextField2FocusGained(evt);
        }
        public void focusLost(java.awt.event.FocusEvent evt) {
            jTextField2FocusLost(evt);
        }
    });
    jTextField2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextField2ActionPerformed(evt);
        }
    });

    jButton6.setText("Search");
    jButton6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton6ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addComponent(jLabel14)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)))
            .addContainerGap())
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel14)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    jTabbedPane2.addTab("View Presentation", jPanel5);

    jPanel7.setBackground(new java.awt.Color(255, 255, 255));

    jTable3.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {},
        new String[] {
            "ReportID", "Subject", "Score", "Remarks","Second Marker Comment"
        }
    ) {
        boolean[] canEdit = new boolean[] {
            false, false, true, true, true
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    });

    jTable3.getTableHeader().setReorderingAllowed(false);
    try {
        readDataFromFile("report.txt", (DefaultTableModel) jTable3.getModel());
    } catch (IOException e) {
        // Handle file reading error
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error reading file: report.txt");
    };

    jTable3.getModel().addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent e) {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int col = e.getColumn();
                Object newValue = jTable3.getModel().getValueAt(row, col);
                saveChangesTypeR(row, col, newValue);
            }
        }
    });
    //));
    jScrollPane3.setViewportView(jTable3);

    jLabel12.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
    jLabel12.setText("Search by ReportID:");

    jTextField3.setFont(new java.awt.Font("Dialog", 2, 11)); // NOI18N
    jTextField3.setText("ReportID");
    jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            jTextField3FocusGained(evt);
        }
        public void focusLost(java.awt.event.FocusEvent evt) {
            jTextField3FocusLost(evt);
        }
    });
    jTextField3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextField3ActionPerformed(evt);
        }
    });

    jButton4.setText("Search");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
        }
    });

    jButton7.setText("Save");
    jButton7.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton7ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
    jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel7Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(90, 90, 90)
                    .addComponent(jLabel8))
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField3))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addComponent(jButton4)
                    .addGap(0, 508, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton7)))
            .addContainerGap())
        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
    );
    jPanel7Layout.setVerticalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel12)
                .addComponent(jButton7))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addComponent(jLabel8))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
            .addGap(12, 12, 12))
    );

    jTabbedPane2.addTab("Report Feedback", jPanel7);

    //getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 121, -1, -1));

    jButton1.setText("Log Out");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });
    //getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(856, 10, -1, -1));

    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AGH logo.png"))); // NOI18N
    //getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 90));

    jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
    jLabel7.setText("Welcome:");
    //getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 10, -1, -1));

    SecondMarker.setBackground(new java.awt.Color(204, 255, 118));
    SecondMarker.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
    SecondMarker.setText("Second Marker");
    //getContentPane().add(SecondMarker, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, 10));

    wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/golden-geometric-frame-with-green-leaves-in-watercolor-style-luxury-polygonal-border-for-decoration-valentine-s-day-wedding-invitations-greeting-cards-on-transparent-background-png-ezgif.com-webp-to-j.png"))); // NOI18N
    //getContentPane().add(wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 660));

    pack();
    }// </editor-fold>                        

//    logout button
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        Menu_Selection main = new Menu_Selection();
        main.setVisible(true);
        this.dispose();
    }                                        

//    Search report function
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String searchReportID = jTextField3.getText();

        JFrame reportFrame = new JFrame("Search Report Details");
        reportFrame.setSize(400, 300); 
        JLabel reportIDLabel = new JLabel("Report ID:");
        JLabel subjectLabel = new JLabel("Subject:");
        JLabel scoreLabel = new JLabel("Score:");
        JLabel remarksLabel = new JLabel("Remarks:");
        JLabel markerCommentLabel = new JLabel("2nd Marker:");
        String[] reportData = readData("report.txt",searchReportID);

        if (reportData == null || reportData.length != 5) {
          JLabel errorLabel = new JLabel("Error: Invalid report data format in report.txt");
          reportFrame.add(errorLabel);
        } else {
          JLabel reportIDValue = new JLabel(reportData[0]);
          JLabel subjectValue = new JLabel(reportData[1]);
          JLabel scoreValue = new JLabel(reportData[2]);
          JLabel remarksValue = new JLabel(reportData[3]);
          JLabel markerCommentValue = new JLabel(reportData[4]);

          reportFrame.setLayout(null); 
          reportIDLabel.setBounds(20, 20, 80, 20);
          reportIDValue.setBounds(110, 20, 150, 20);
          subjectLabel.setBounds(20, 50, 80, 20);
          subjectValue.setBounds(110, 50, 150, 20);
          scoreLabel.setBounds(20, 80, 80, 20);
          scoreValue.setBounds(110, 80, 150, 20);
          remarksLabel.setBounds(20, 110, 80, 20);
          remarksValue.setBounds(110, 110, 250, 20); 
          markerCommentLabel.setBounds(20, 140, 150, 20);
          markerCommentValue.setBounds(110, 140, 250, 20); 

          reportFrame.add(reportIDLabel);
          reportFrame.add(reportIDValue);
          reportFrame.add(subjectLabel);
          reportFrame.add(subjectValue);
          reportFrame.add(scoreLabel);
          reportFrame.add(scoreValue);
          reportFrame.add(remarksLabel);
          reportFrame.add(remarksValue);
          reportFrame.add(markerCommentLabel);
          reportFrame.add(markerCommentValue);
        }
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setVisible(true);
        reportFrame.setAlwaysOnTop(true);    
    }                                        
    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String searchReportID = jTextField3.getText();
    }                                           
    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {                                      
        if (jTextField3.getText().length() == 0) {
            jTextField3.setText("ReportID");
        }
    }                                     
    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {                                        
        if (jTextField3.getText().equals("ReportID")) {
            jTextField3.setText(null);
            jTextField3.setFont(jTextField3.getFont().deriveFont(Font.PLAIN));            
            jTextField3.requestFocus();
        }
    }                                       

//    Search supervisee function
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String searchSuperviseeID = jTextField1.getText();
        JFrame superviseeFrame = new JFrame("Search Report Details");
        superviseeFrame.setSize(400, 300);

        JLabel superviseeIDLabel = new JLabel("SuperviseeID:");
        JLabel statusLabel = new JLabel("Status:");
        JLabel dateLabel = new JLabel("Date:");
        JLabel intakeLabel = new JLabel("Intake:");
        JLabel assessmentLabel = new JLabel("Assessment:");
        String[] superviseeData = readData("supervisee.txt",searchSuperviseeID);

        if (superviseeData == null || superviseeData.length != 5) {
          JLabel errorLabel = new JLabel("Error: Invalid supervisee data format in supervisee.txt");
          superviseeFrame.add(errorLabel);
        } else {
          JLabel superviseeIDValue = new JLabel(superviseeData[0]);
          JLabel statusValue = new JLabel(superviseeData[1]);
          JLabel dateValue = new JLabel(superviseeData[2]);
          JLabel intakeValue = new JLabel(superviseeData[3]);
          JLabel assessmentValue = new JLabel(superviseeData[4]);

          superviseeFrame.setLayout(null); 
          superviseeIDLabel.setBounds(20, 20, 80, 20);
          superviseeIDValue.setBounds(110, 20, 150, 20);
          statusLabel.setBounds(20, 50, 80, 20);
          statusValue.setBounds(110, 50, 150, 20);
          dateLabel.setBounds(20, 80, 80, 20);
          dateValue.setBounds(110, 80, 150, 20);
          intakeLabel.setBounds(20, 110, 80, 20);
          intakeValue.setBounds(110, 110, 250, 20); 
          assessmentLabel.setBounds(20, 140, 150, 20);
          assessmentValue.setBounds(110, 140, 250, 20); 

          superviseeFrame.add(superviseeIDLabel);
          superviseeFrame.add(superviseeIDValue);
          superviseeFrame.add(statusLabel);
          superviseeFrame.add(statusValue);
          superviseeFrame.add(dateLabel);
          superviseeFrame.add(dateValue);
          superviseeFrame.add(intakeLabel);
          superviseeFrame.add(intakeValue);
          superviseeFrame.add(assessmentLabel);
          superviseeFrame.add(assessmentValue);
        }
        superviseeFrame.setLocationRelativeTo(null);
        superviseeFrame.setVisible(true);
        superviseeFrame.setAlwaysOnTop(true);       
    }                                        
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String searchSuperviseeID = jTextField1.getText();
    }                                           
    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {                                        
        if (jTextField1.getText().equals("SuperviseeID")) {
            jTextField1.setText(null);
            jTextField1.setFont(jTextField1.getFont().deriveFont(Font.PLAIN));
            jTextField1.requestFocus();
        }
    }                                       
    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {                                      
        if (jTextField1.getText().length() == 0) {
            jTextField1.setText("SuperviseeID");
        }
    }                                     

//    Search presentation function
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String searchPresentationID = jTextField2.getText();
        JFrame PresentationFrame = new JFrame("Search Presentation Details");
        PresentationFrame.setSize(400, 300);

        JLabel presentationIDLabel = new JLabel("PresentationID:");
        JLabel subjectLabel = new JLabel("Subject:");
        JLabel dateLabel = new JLabel("Date:");
        JLabel timeLabel = new JLabel("Time:");
        JLabel statusLabel = new JLabel("Status:");
        String[] presentationData = readData("supervisee.txt",searchPresentationID);

        if (presentationData == null || presentationData.length != 5) {
            JLabel errorLabel = new JLabel("Error: Invalid report data format in supervisee.txt");
            PresentationFrame.add(errorLabel);
        } else {
            JLabel presentationIDValue = new JLabel(presentationData[0]);
            JLabel subjectValue = new JLabel(presentationData[1]);
            JLabel dateValue = new JLabel(presentationData[2]);
            JLabel TimeValue = new JLabel(presentationData[3]);
            JLabel statusValue = new JLabel(presentationData[4]);

            PresentationFrame.setLayout(null);
            presentationIDLabel.setBounds(20, 20, 80, 20);
            presentationIDValue.setBounds(110, 20, 150, 20);
            subjectLabel.setBounds(20, 50, 80, 20);
            subjectValue.setBounds(110, 50, 150, 20);
            dateLabel.setBounds(20, 80, 80, 20);
            dateValue.setBounds(110, 80, 150, 20);
            timeLabel.setBounds(20, 110, 80, 20);
            TimeValue.setBounds(110, 110, 250, 20);
            statusLabel.setBounds(20, 140, 150, 20);
            statusValue.setBounds(110, 140, 250, 20);

            PresentationFrame.add(presentationIDLabel);
            PresentationFrame.add(presentationIDValue);
            PresentationFrame.add(subjectLabel);
            PresentationFrame.add(subjectValue);
            PresentationFrame.add(dateLabel);
            PresentationFrame.add(dateValue);
            PresentationFrame.add(timeLabel);
            PresentationFrame.add(TimeValue);
            PresentationFrame.add(statusLabel);
            PresentationFrame.add(statusValue);
        }
        PresentationFrame.setLocationRelativeTo(null);
        PresentationFrame.setVisible(true);
        PresentationFrame.setAlwaysOnTop(true);
    }                                        
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String searchPresentationID = jTextField2.getText();
    }                                           
    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {                                      
        if (jTextField2.getText().length() == 0) {
            jTextField2.setText("PresentationID");
        }
    }                                     
    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {                                        
        if (jTextField2.getText().equals("PresentationID")) {
            jTextField2.setText(null);
            jTextField2.setFont(jTextField2.getFont().deriveFont(Font.PLAIN));
            jTextField2.requestFocus();
        }
    }                                       

//    Save button for report table (redundant)
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {                                         

    }                                        

//    Read textfile for table
    private void readDataFromFile(String fileName, DefaultTableModel model) throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = reader.readLine()) != null) {
          String[] data = line.split("\\$");
          model.addRow(data);
        }
      } 
    }

//    Read textfile for search 
    private String[] readData(String filename, String searchReportID) {
      try {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          if (line.contains(searchReportID)) {
            scanner.close();
            return line.split("\\$");
          }
        }
        scanner.close();
        return null; 
      } catch (FileNotFoundException e) {
        System.err.println("Error: File not found: " + filename);
      } catch (Exception e) {
        System.err.println("Error: Error reading file: " + filename);
      }
      return null;
    }

//    Save data into file
    private void saveTableDataToFile(String filename) throws IOException {
      FileWriter writer = new FileWriter(filename);  
      BufferedWriter bw = new BufferedWriter(writer);   

      for (int i = 0; i < jTable2.getRowCount(); i++) {
        for (int j = 0; j < jTable2.getColumnCount(); j++) {
          bw.write(jTable2.getValueAt(i, j).toString());
          if (j != jTable2.getColumnCount() - 1) {
            bw.write("$");  
          }
        }
        bw.newLine();  
      }
      bw.close();  
      writer.close();
    }
    
//    Save cahnges to report.txt
    private void saveChangesPagani(int row, int col, Object newValue) {
      try (FileWriter writer = new FileWriter("presentation.txt", true)) {
        writer.write(newValue.toString() + "$");
        writer.write("\n");
      } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saving data to file");
      }
    }
    
//    save changes to presentation.txt
    private void saveChangesTypeR(int row, int col, Object newValue) {
        try (FileWriter writer = new FileWriter("report.txt")) {
          DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
          int rowCount = model.getRowCount();
          for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
              writer.write(model.getValueAt(i, j).toString() + "$");
            }
            writer.write("\n");
          }
        } catch (IOException e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(this, "Error saving data to file");
        }
      }
    private void saveTableDataToFileTypeR(String filename) throws IOException {
      FileWriter writer = new FileWriter(filename);  
      BufferedWriter bw = new BufferedWriter(writer);   

      for (int i = 0; i < jTable3.getRowCount(); i++) {
        for (int j = 0; j < jTable3.getColumnCount(); j++) {
          bw.write(jTable3.getValueAt(i, j).toString());
          if (j != jTable3.getColumnCount() - 1) {
            bw.write("$");  
          }
        }
        bw.newLine();  
      }
      bw.close();  
      writer.close();
    }
    /**
     * @param args the command line arguments
     */
    public static void SecondMarkerGUI(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LecturerMainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LecturerMainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LecturerMainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LecturerMainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    Menu_Selection g = new Menu_Selection ();
                    g.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    private java.awt.Label SecondMarker;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    public javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel wallpaper;
    // End of variables declaration                   
}