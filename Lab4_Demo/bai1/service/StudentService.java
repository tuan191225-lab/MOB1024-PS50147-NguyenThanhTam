package Lab4_Demo.bai1.service;

import Lab4_Demo.bai1.model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(String id, String name, double gpa) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã sinh viên không được để trống!");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên sinh viên không được để trống!");
        }
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA phải nằm trong khoảng từ 0.0 đến 4.0!");
        }

        students.add(new Student(id.trim(), name.trim(), gpa));
    }

    public List<Student> getAllStudents() {
        return students;
    }
}