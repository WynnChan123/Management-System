import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SubjectsHelper {
    private static List<Subject> subjectsList = new ArrayList<>();

    static {
        try {
            List<String> lines = Files.readAllLines(Paths.get("intake_subject.txt"));
            for (String line : lines) {
                String[] parts = line.split("\\$");
                String intake = parts[0];
                for (int i = 1; i < parts.length; i++) {
                    String[] subjectParts = parts[i].split("\\|");
                    String subjectName = subjectParts[0];
                    String lecturerId = subjectParts[1];
                    subjectsList.add(new Subject(intake, subjectName, lecturerId));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Subject> getSubjectsForIntake(String intake) {
        List<Subject> subjects = new ArrayList<>();
        for (Subject subject : subjectsList) {
            if (subject.getIntake().equalsIgnoreCase(intake)) {
                subjects.add(subject);
            }
        }
        return subjects;
    }

    public static Subject getSubjectByName(String subjectName) {
        for (Subject subject : subjectsList) {
            if (subject.getName().equalsIgnoreCase(subjectName)) {
                return subject;
            }
        }
        return null;
    }
}
