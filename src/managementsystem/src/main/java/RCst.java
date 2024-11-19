import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class RCst extends JPanel {
    private String studentID;
    private JTable resultsTable;

    public RCst(String studentID) {
        this.studentID = studentID;

        setLayout(new BorderLayout());

        // Create table model and table
        String[] columnNames = {"Intake", "Subject", "File", "Mark", "Comment", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        resultsTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        // Populate the table with data
        populateTableWithResults(tableModel, studentID);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        add(scrollPane, BorderLayout.CENTER);

        // Add download button
        JButton downloadButton = new JButton("Download as JPEG");
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadTableAsJPEG();
            }
        });
        add(downloadButton, BorderLayout.SOUTH);
    }

    private void populateTableWithResults(DefaultTableModel tableModel, String studentID) {
        List<String[]> results = getResultsForStudent(studentID, "report.txt");

        for (String[] result : results) {
            // Replace empty values with placeholders
            for (int i = 0; i < result.length; i++) {
                if (result[i].trim().isEmpty()) {
                    result[i] = "Pending";  // Placeholder for empty values
                }
            }
            tableModel.addRow(result);
        }
    }

    private List<String[]> getResultsForStudent(String studentID, String fileName) {
        List<String[]> results = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                String[] parts = line.split("\\$");
                if (parts.length >= 6 && parts[2].contains(studentID)) {
                    results.add(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    private void downloadTableAsJPEG() {
        try {
            // Create the file name based on student ID and current time
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String fileName = studentID + "_" + currentTime.format(formatter) + ".jpeg";

            // Create the image file
            Dimension size = resultsTable.getSize();
            BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            resultsTable.paint(g2);
            g2.dispose();

            // Save the image file to the desktop
            File desktopDir = new File(System.getProperty("user.home"), "Desktop");
            File outputFile = new File(desktopDir, fileName);
            ImageIO.write(image, "jpeg", outputFile);

            JOptionPane.showMessageDialog(this, "Table downloaded as " + fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to download table as JPEG.");
        }
    }
}
