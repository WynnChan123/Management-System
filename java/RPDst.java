import javax.swing.*;
import org.jdatepicker.impl.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Properties;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;

public class RPDst extends JPanel {
    private JTextField nameField;
    private JComboBox<Subject> topicComboBox;
    private Map<String, String> studentData;
    private String studentID;
    private String intake;

    public RPDst(String studentID, String intake) {
        this.studentID = studentID;
        this.intake = intake;

        // Set layout for the custom panel
        setLayout(null);

        // Load student data
        studentData = loadStudentData("student_id.txt");

        // Create and add components to the panel
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 50, 200, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(250, 50, 200, 30);
        nameField.setEditable(false); // Make the name field non-editable
        add(nameField);

        String studentName = getStudentName(studentID);
        nameField.setText(studentName);

        JLabel dateLabel = new JLabel("Preferred Date:");
        dateLabel.setBounds(50, 100, 200, 30);
        add(dateLabel);

        // Create a date picker
        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(250, 100, 200, 30);
        add(datePicker);

        JLabel timeLabel = new JLabel("Preferred Time:");
        timeLabel.setBounds(50, 150, 200, 30);
        add(timeLabel);

        String[] times = {
            "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM",
            "12:00 PM", "01:00 PM", "02:00 PM", "03:00 PM",
            "04:00 PM", "05:00 PM"
        };
        JComboBox<String> timeComboBox = new JComboBox<>(times);
        timeComboBox.setBounds(250, 150, 200, 30);
        add(timeComboBox);

        JLabel timeLabel2 = new JLabel("*each presentation slot duration maximum 90mins");
        timeLabel2.setForeground(Color.RED);
        timeLabel2.setBounds(460, 150, 500, 15);
        add(timeLabel2);

        JLabel topicLabel = new JLabel("Presentation Topic:");
        topicLabel.setBounds(50, 200, 200, 30);
        add(topicLabel);

        List<Subject> subjects = SubjectUtil.getSubjectsForIntake(intake);
        topicComboBox = new JComboBox<>(subjects.toArray(new Subject[0]));
        topicComboBox.setBounds(250, 200, 200, 30);
        add(topicComboBox);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(250, 250, 100, 30);
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            Date selectedDate = (Date) datePicker.getModel().getValue();
            String selectedTime = (String) timeComboBox.getSelectedItem();
            Subject subject = (Subject) topicComboBox.getSelectedItem();

            if (name.isEmpty() || selectedDate == null || selectedTime == null || subject == null) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields.");
            } else {
                String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(selectedDate);
                String message = "Name: " + name + "\n";
                message += "Preferred Date: " + formattedDate + "\n";
                message += "Preferred Time: " + selectedTime + "\n";
                message += "Presentation Subject: " + subject.getName() + "\n";

                int option = JOptionPane.showOptionDialog(null, message, "Information Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Yes", "No"}, "Yes");

                if (option == JOptionPane.YES_OPTION) {
                    // User clicked "Yes" in information confirmation
                    if (submitPresentation(name, formattedDate, selectedTime, subject.getName())) {
                        JOptionPane.showMessageDialog(null, "Presentation date and time requested successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error occurred while submitting presentation details.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // User clicked "No" in information confirmation, do something else or close the dialog
                }
            }
        });
        add(submitButton);
    }

    private Map<String, String> loadStudentData(String fileName) {
        Map<String, String> data = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                String[] parts = line.split("\\$");
                if (parts.length >= 2) {
                    data.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String getStudentName(String studentID) {
        return studentData.getOrDefault(studentID, "");
    }

    // Custom formatter for the date picker
    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private String datePattern = "yyyy-MM-dd";
        private java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws java.text.ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws java.text.ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }
    
    private boolean submitPresentation(String name, String formattedDate, String selectedTime, String subjectName) {
        try {
            String presentationEntry = generatePresentationId() + "$" +
                    subjectName + "$" +
                    formattedDate + "$" +
                    selectedTime + "$" +
                    "Scheduled$" +
                    getLecturerId(subjectName) + "$" +
                    getStudentID(name);

            Files.write(Paths.get("presentation.txt"), (presentationEntry + "\n").getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String generatePresentationId() {
        String presentationId = "PS00001";

        try {
            List<String> lines = Files.readAllLines(Paths.get("presentation.txt"));
            if (!lines.isEmpty()) {
                String lastLine = lines.get(lines.size() - 1);
                String lastId = lastLine.split("\\$")[0];
                int newIdNumber = Integer.parseInt(lastId.substring(2)) + 1;
                presentationId = String.format("PS%05d", newIdNumber);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return presentationId;
    }

    private String getLecturerId(String subjectName) {
        for (Subject subject : SubjectUtil.getSubjectsForIntake(intake)) {
            if (subject.getName().equals(subjectName)) {
                return subject.getLecturerId();
            }
        }
        return "";
    }

    private String getStudentID(String name) {
        for (Map.Entry<String, String> entry : studentData.entrySet()) {
            if (entry.getValue().equals(name)) {
                return entry.getKey();
            }
        }
        return "";
    }
}
