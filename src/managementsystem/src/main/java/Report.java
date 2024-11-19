import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Report extends JPanel {
    private JButton viewWholeButton, viewByIntakeButton, viewByStudentButton, viewByStatusButton;
    private JTable reportTable;

    public Report() {
        setLayout(new BorderLayout());

         // Buttons to view reports
        viewWholeButton = new JButton("View Whole Report");
        viewByIntakeButton = new JButton("View By Intake");
        viewByStudentButton = new JButton("View By StudentID");
        viewByStatusButton = new JButton("View By Status");

        viewWholeButton.addActionListener(e -> viewReport("whole", null));
        viewByIntakeButton.addActionListener(e -> showIntakeDialog());
        viewByStudentButton.addActionListener(e -> showStudentDialog());
        viewByStatusButton.addActionListener(e -> showStatusDialog());

        // Panel for view buttons
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(viewWholeButton);
        topPanel.add(viewByIntakeButton);
        topPanel.add(viewByStudentButton);
        topPanel.add(viewByStatusButton);

        // Table to display report
        reportTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(reportTable);

        // Add components to the panel
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

     private void showIntakeDialog() {
        String[] intakes = {"AGH2F2402CS", "AGH2F2402CS (AI)", "AGH2F2402CS (CYB)",
                "AGH2F2402CS (DA)", "AGH2F2402CS (DF)"};
        String selectedIntake = (String) JOptionPane.showInputDialog(
                this,
                "Choose Intake:",
                "Select Intake",
                JOptionPane.PLAIN_MESSAGE,
                null,
                intakes,
                intakes[0]);

        if (selectedIntake != null) {
            viewReport("intake", selectedIntake);
        }
    }

    private void showStudentDialog() {
        String[] students = {"SPCC_ST00001", "JAVA_ST00001", "LINUX_ST00001", "FIS_ST00001"};
        String selectedStudent = (String) JOptionPane.showInputDialog(
                this,
                "Choose Student ID:",
                "Select Student ID",
                JOptionPane.PLAIN_MESSAGE,
                null,
                students,
                students[0]);

        if (selectedStudent != null) {
            viewReport("student", selectedStudent);
        }
    }

    private void showStatusDialog() {
        String[] statuses = {"Approved", "Remark", "Rejected"};
        String selectedStatus = (String) JOptionPane.showInputDialog(
                this,
                "Choose status:",
                "Select Status",
                JOptionPane.PLAIN_MESSAGE,
                null,
                statuses,
                statuses[0]);

        if (selectedStatus != null) {
            viewReport("status", selectedStatus);
        }
    }

    private void viewReport(String type, String filter) {
        // Read the report file and populate the table
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Intake");
        model.addColumn("Subject");
        model.addColumn("Student ID");
        model.addColumn("Score");
        model.addColumn("Feedback");
        model.addColumn("Status");

        String fileName = "report.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\$");
                if (fields.length < 6) {
                    // Skip lines that don't have the expected number of fields
                    continue;
                }
                if ("whole".equals(type)) {
                    model.addRow(fields);
                } else if ("intake".equals(type) && fields[0].equals(filter)) {
                    model.addRow(fields);
                } else if ("student".equals(type) && fields[2].equals(filter)) {
                    model.addRow(fields);
                } else if ("status".equals(type) && fields[5].equals(filter)) {
                    model.addRow(fields);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading report file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        reportTable.setModel(model);}
}