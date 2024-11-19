public class Subject {
    private String intake;
    private String name;
    private String lecturerId;

    public Subject(String intake, String name, String lecturerId) {
        this.intake = intake;
        this.name = name;
        this.lecturerId = lecturerId;
    }

    public String getIntake() {
        return intake;
    }

    public String getName() {
        return name;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    @Override
    public String toString() {
        return name;
    }
}