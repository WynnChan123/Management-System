import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
public class LecturerGUI extends javax.swing.JFrame {

    public LecturerGUI() {
        initComponents();
        Color col = new Color(204, 255, 118);
        getContentPane().setBackground(col);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        superviseepanel = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        superviseetable = new javax.swing.JTable();
        searchreport = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        superviseeTextField = new javax.swing.JTextField();
        ViewAssigned = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        presentationtable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        presentationTextField = new javax.swing.JTextField();
        buttonpresentation = new javax.swing.JButton(); 
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        reporttable = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        reportTextField = new javax.swing.JTextField();
        buttonreport = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        superviseetable.setModel(new javax.swing.table.DefaultTableModel(
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
        superviseetable.getTableHeader().setReorderingAllowed(false);
        try {
            readDataFromFile("supervisee.txt", (DefaultTableModel) superviseetable.getModel());
        } catch (IOException e) {
            // Handle file reading error
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading file: supervisee.txt");
        };
        //);
    superviseetable.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(superviseetable);

    searchreport.setText("Search ");
    searchreport.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            searchreportActionPerformed(evt);
        }
    });

    jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
    jLabel1.setText("Search by Supervisee ID");

    superviseeTextField.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
    superviseeTextField.setText("SuperviseeID");
    superviseeTextField.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            superviseeTextFieldFocusGained(evt);
        }
        public void focusLost(java.awt.event.FocusEvent evt) {
            superviseeTextFieldFocusLost(evt);
        }
    });
    superviseeTextField.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            superviseeTextFieldActionPerformed(evt);
        }
    });

    ViewAssigned.setText("View Assigned Supervisees");
    ViewAssigned.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            ViewAssignedActionPerformed(evt);
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
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(superviseeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(searchreport)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ViewAssigned)))
            .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(superviseeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchreport)
                    .addComponent(ViewAssigned)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
            .addContainerGap())
    );

    superviseepanel.addTab("Supervisee Dashboard", jPanel4);

    jPanel5.setBackground(new java.awt.Color(255, 255, 255));

    presentationtable.setModel(new javax.swing.table.DefaultTableModel(
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

    presentationtable.getTableHeader().setReorderingAllowed(false);
    try {
        readDataFromFile("presentation.txt", (DefaultTableModel) presentationtable.getModel());
    } catch (IOException e) {
        // Handle file reading error
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error reading file: presentation.txt");
    };

    presentationtable.getModel().addTableModelListener(new TableModelListener() {
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
    jScrollPane2.setViewportView(presentationtable);

    jLabel2.setText("*Presentation time allocated is limited to 1 hour");

    jLabel14.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
    jLabel14.setText("Search by Presentation ID");

    presentationTextField.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
    presentationTextField.setText("PresentationID");
    presentationTextField.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            presentationTextFieldFocusGained(evt);
        }
        public void focusLost(java.awt.event.FocusEvent evt) {
            presentationTextFieldFocusLost(evt);
        }
    });
    presentationTextField.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            presentationTextFieldActionPerformed(evt);
        }
    });

    buttonpresentation.setText("Search");
    buttonpresentation.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonpresentationActionPerformed(evt);
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
                    .addComponent(presentationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(buttonpresentation)
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
                    .addComponent(presentationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonpresentation))
                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    superviseepanel.addTab("View Presentation", jPanel5);

    jPanel7.setBackground(new java.awt.Color(255, 255, 255));

    reporttable.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {},
        new String[] {
            "Intake", "Subject Name", "Student ID", "Score", "Remark", "Second Marker Comment"
        }
    ) {
        boolean[] canEdit = new boolean[] {
            false, false, false, true, true, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    });

    reporttable.getTableHeader().setReorderingAllowed(false);

    // Read data from file and populate the table
    try {
        readDataFromFile("report.txt", (DefaultTableModel) reporttable.getModel());
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error reading file: report.txt");
    }

    // Listen for changes in the table and save them
    reporttable.getModel().addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent e) {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int col = e.getColumn();
                Object newValue = reporttable.getModel().getValueAt(row, col);
                saveChangesTypeR(row, col, newValue);
            }
        }
    });
    //));
    jScrollPane3.setViewportView(reporttable);

    jLabel12.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
    jLabel12.setText("Search by StudentID:");

    reportTextField.setFont(new java.awt.Font("Dialog", 2, 11)); // NOI18N
    reportTextField.setText("ReportID");
    reportTextField.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            reportTextFieldFocusGained(evt);
        }
        public void focusLost(java.awt.event.FocusEvent evt) {
            reportTextFieldFocusLost(evt);
        }
    });
    reportTextField.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            reportTextFieldActionPerformed(evt);
        }
    });

    buttonreport.setText("Search");
    buttonreport.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonreportActionPerformed(evt);
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
                .addComponent(reportTextField))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonreport)
            .addContainerGap(514, Short.MAX_VALUE))
        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
    );
    jPanel7Layout.setVerticalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel12)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reportTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonreport))
                .addComponent(jLabel8))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
            .addGap(12, 12, 12))
    );

    superviseepanel.addTab("Report Feedback", jPanel7);

    logout.setText("Log Out");
    logout.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            logoutActionPerformed(evt);
        }
    });

    jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
    jLabel7.setText("Welcome:");

    jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
    jLabel3.setText("Lecturer");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(111, 111, 111)
            .addComponent(jLabel3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 584, Short.MAX_VALUE)
            .addComponent(logout)
            .addContainerGap())
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(superviseepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE)))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(logout)
            .addContainerGap(558, Short.MAX_VALUE))
        .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel3)
            .addGap(0, 0, Short.MAX_VALUE))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(31, 31, 31)
                .addComponent(superviseepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchreportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchreportActionPerformed
        String searchSuperviseeID = superviseeTextField.getText();
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
    }//GEN-LAST:event_searchreportActionPerformed

    private void superviseeTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_superviseeTextFieldFocusGained
        if (superviseeTextField.getText().equals("SuperviseeID")) {
            superviseeTextField.setText(null);
            superviseeTextField.setFont(superviseeTextField.getFont().deriveFont(Font.PLAIN));
            superviseeTextField.requestFocus();
        }
    }//GEN-LAST:event_superviseeTextFieldFocusGained

    private void superviseeTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_superviseeTextFieldFocusLost
        if (superviseeTextField.getText().length() == 0) {
            superviseeTextField.setText("SuperviseeID");
        }
    }//GEN-LAST:event_superviseeTextFieldFocusLost

    private void superviseeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_superviseeTextFieldActionPerformed
        String searchSuperviseeID = superviseeTextField.getText();
    }//GEN-LAST:event_superviseeTextFieldActionPerformed

    private void presentationTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_presentationTextFieldFocusGained
        if (presentationTextField.getText().equals("PresentationID")) {
            presentationTextField.setText(null);
            presentationTextField.setFont(presentationTextField.getFont().deriveFont(Font.PLAIN));
            presentationTextField.requestFocus();
        }
    }//GEN-LAST:event_presentationTextFieldFocusGained

    private void presentationTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_presentationTextFieldFocusLost
        if (presentationTextField.getText().length() == 0) {
            presentationTextField.setText("PresentationID");
        }
    }//GEN-LAST:event_presentationTextFieldFocusLost

    private void presentationTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presentationTextFieldActionPerformed
        String searchPresentationID = presentationTextField.getText();
    }//GEN-LAST:event_presentationTextFieldActionPerformed

    private void buttonpresentationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonpresentationActionPerformed
        String searchPresentationID = presentationTextField.getText();
        JFrame PresentationFrame = new JFrame("Search Presentation Details");
        PresentationFrame.setSize(400, 300);

        JLabel presentationIDLabel = new JLabel("PresentationID:");
        JLabel subjectLabel = new JLabel("Subject:");
        JLabel dateLabel = new JLabel("Date:");
        JLabel timeLabel = new JLabel("Time:");
        JLabel statusLabel = new JLabel("Status:");
        String[] presentationData = readData("presentation.txt",searchPresentationID);

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
    }//GEN-LAST:event_buttonpresentationActionPerformed

    private void reportTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_reportTextFieldFocusGained
        if (reportTextField.getText().equals("ReportID")) {
            reportTextField.setText(null);
            reportTextField.setFont(reportTextField.getFont().deriveFont(Font.PLAIN));
            reportTextField.requestFocus();
        }
    }//GEN-LAST:event_reportTextFieldFocusGained

    private void reportTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_reportTextFieldFocusLost
        if (reportTextField.getText().length() == 0) {
            reportTextField.setText("ReportID");
        }
    }//GEN-LAST:event_reportTextFieldFocusLost

    private void reportTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportTextFieldActionPerformed
    String searchReportID = reportTextField.getText();
    }//GEN-LAST:event_reportTextFieldActionPerformed

    private void buttonreportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonreportActionPerformed
        String searchReportID = reportTextField.getText();
        JFrame ReportFrame = new JFrame("Search Report Details");
        ReportFrame.setSize(600, 400);

        String[] columnNames = {"Intake", "Subject", "Student ID", "Score", "Remarks", "Second Marker Comment"};
        String[][] reportData = readDataforTable("report.txt", searchReportID);

        if (reportData == null || reportData.length == 0) {
            JLabel errorLabel = new JLabel("Error: No data found or invalid report data format in report.txt");
            ReportFrame.add(errorLabel);
        } else {
            JTable ReportTable = new JTable(reportData, columnNames);
            JScrollPane scrollPane = new JScrollPane(ReportTable);
            ReportTable.setFillsViewportHeight(true);

            ReportFrame.setLayout(new BorderLayout());
            ReportFrame.add(scrollPane, BorderLayout.CENTER);
        }

        ReportFrame.setLocationRelativeTo(null);
        ReportFrame.setVisible(true);
        ReportFrame.setAlwaysOnTop(true);
    }//GEN-LAST:event_buttonreportActionPerformed

    private String[][] readDataforTable(String filename, String searchReportID) {
        List<String[]> lines = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(searchReportID)) {
                    lines.add(line.split("\\$"));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: " + filename);
        } catch (Exception e) {
            System.err.println("Error: Error reading file: " + filename);
        }
        if (lines.isEmpty()) {
            return null;
        } else {
            return lines.toArray(new String[0][]);
        }
    }

    
    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        Menu_Selection main = new Menu_Selection();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutActionPerformed

    private void ViewAssignedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewAssignedActionPerformed
            List<String[]> approvedSupervisees = new ArrayList<>();

            // Read and filter the data from the file
            try (BufferedReader br = new BufferedReader(new FileReader("supervisee.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split("\\$");
                    if ("Approved".equals(data[1])) {
                        approvedSupervisees.add(data);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Create a new frame for the table
            JFrame tableFrame = new JFrame("Approved Supervisees");
            tableFrame.setSize(800, 400);
            tableFrame.setLocationRelativeTo(null);

            // Create the table model and add the data
            String[] columnNames = {"SuperviseeID", "Status", "Date", "Intake Code", "Assessment type"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            for (String[] supervisee : approvedSupervisees) {
                tableModel.addRow(supervisee);
            }

            // Create the table with the model
            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            tableFrame.add(scrollPane);

            // Display the table frame
            tableFrame.setVisible(true);       
    }//GEN-LAST:event_ViewAssignedActionPerformed

private void readDataFromFile(String fileName, DefaultTableModel model) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split("\\$");
            model.addRow(data);
        }
    }
}

// Read text file for search
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


//    Save data into file (presentation)
    private void saveTableDataToFile(String filename) throws IOException {
      FileWriter writer = new FileWriter(filename);  
      BufferedWriter bw = new BufferedWriter(writer);   

      for (int i = 0; i < presentationtable.getRowCount(); i++) {
        for (int j = 0; j < presentationtable.getColumnCount(); j++) {
          bw.write(presentationtable.getValueAt(i, j).toString());
          if (j != presentationtable.getColumnCount() - 1) {
            bw.write("$");  
          }
        }
        bw.newLine();  
      }
      bw.close();  
      writer.close();
    }
    
//    Save changes to presentation.txt
    private void saveChangesPagani(int row, int col, Object newValue) {
      try (FileWriter writer = new FileWriter("presentation.txt", true)) {
        writer.write(newValue.toString() + "$");
        writer.write("\n");
      } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saving data to file");
      }
    }
    
    // Save changes to report.txt when a cell is updated
    private void saveChangesTypeR(int row, int col, Object newValue) {
        try (FileWriter writer = new FileWriter("report.txt")) {
            DefaultTableModel model = (DefaultTableModel) reporttable.getModel();
            int rowCount = model.getRowCount();
            int colCount = model.getColumnCount();

            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    Object cellValue = model.getValueAt(i, j);
                    writer.write(cellValue != null ? cellValue.toString() : "");
                    if (j < colCount - 1) {
                        writer.write("$");
                    }
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data to file");
        }
    }


    // Method to save the entire table data to a specified file
    private void saveTableDataToFileTypeR(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename);
             BufferedWriter bw = new BufferedWriter(writer)) {

            int rowCount = reporttable.getRowCount();
            int colCount = reporttable.getColumnCount();

            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    Object cellValue = reporttable.getValueAt(i, j);
                    bw.write(cellValue != null ? cellValue.toString() : "");
                    if (j < colCount - 1) {
                        bw.write("$");
                    }
                }
                bw.newLine();
            }
        }
    }
    public static void LecturerGUI(String args[]) {
        /* Set the Nimbus look and feel */
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LecturerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LecturerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LecturerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LecturerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LecturerGUI().setVisible(true);
            }
        });
    }
    private javax.swing.JButton ViewAssigned;
    private javax.swing.JButton logout;
    private javax.swing.JButton searchreport;
    public javax.swing.JButton buttonreport;
    private javax.swing.JButton buttonpresentation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane superviseepanel;
    private javax.swing.JTable superviseetable;
    private javax.swing.JTable presentationtable;
    private javax.swing.JTable reporttable;
    private javax.swing.JTextField superviseeTextField;
    private javax.swing.JTextField presentationTextField;
    private javax.swing.JTextField reportTextField;
}