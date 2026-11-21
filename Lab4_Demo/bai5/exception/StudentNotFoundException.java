package Lab4_Demo.bai5.exception;

public class StudentNotFoundException extends Exception {
    private final String studentId;

    public StudentNotFoundException(String studentId) {
        super("Không tìm thấy sinh viên có mã: " + studentId);
        this.studentId = studentId;
    }

    public StudentNotFoundException(String studentId, String message) {
        super(message);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }
}