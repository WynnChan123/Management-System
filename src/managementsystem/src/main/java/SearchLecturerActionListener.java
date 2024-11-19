import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SearchLecturerActionListener implements ActionListener {
    private AssignPMrole parent;
    private JTextField searchField;
    private DefaultTableModel lecturerTableModel;

    public SearchLecturerActionListener(AssignPMrole parent, JTextField searchField, DefaultTableModel lecturerTableModel) {
        this.parent = parent;
        this.searchField = searchField;
        this.lecturerTableModel = lecturerTableModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String searchId = searchField.getText().trim();
        if (searchId != null) {
            parent.searchLecturer(searchId);
        } else {
            JOptionPane.showMessageDialog(parent, "Please enter a Lecturer ID to search.", "No Input", JOptionPane.WARNING_MESSAGE);
        }

        File lecturerIdFile = new File("lecturer_id.txt");
        if (!lecturerIdFile.exists()) {
            JOptionPane.showMessageDialog(parent, "Error: Lecturer ID file not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Scanner fileScanner = new Scanner(lecturerIdFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\$");
                if (parts.length == 5 && parts[0].equals(searchId) && !parts[1].startsWith("PM")) {
                    lecturerTableModel.addRow(parts);
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(parent, "Error reading lecturer ID file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}