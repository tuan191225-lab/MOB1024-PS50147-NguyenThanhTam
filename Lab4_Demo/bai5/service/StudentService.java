package Lab4_Demo.bai5.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Lab4_Demo.bai1.model.Student;
import Lab4_Demo.bai5.exception.StudentNotFoundException;

public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Thông tin sinh viên không được null!");
        }
        students.add(student);
    }

    public Student findById(String id) throws StudentNotFoundException {
        if (id != null) {
            String targetId = id.trim();
            for (Student s : students) {
                if (s.getId() != null && s.getId().equalsIgnoreCase(targetId)) {
                    return s;
                }
            }
        }
        throw new StudentNotFoundException(id);
    }

    public void updateById(String id, String newName, double newGpa) throws StudentNotFoundException {
        Student s = findById(id);
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên sinh viên mới không được để trống!");
        }
        if (newGpa < 0.0 || newGpa > 4.0) {
            throw new IllegalArgumentException("GPA mới phải nằm trong khoảng từ 0.0 đến 4.0!");
        }
        s.setName(newName.trim());
        s.setGpa(newGpa);
    }

    public void deleteById(String id) throws StudentNotFoundException {
        if (id != null) {
            String targetId = id.trim();
            Iterator<Student> it = students.iterator();
            while (it.hasNext()) {
                Student s = it.next();
                if (s.getId() != null && s.getId().equalsIgnoreCase(targetId)) {
                    it.remove();
                    return;
                }
            }
        }
        throw new StudentNotFoundException(id, "Không thể xóa: không tìm thấy sinh viên có mã '" + id + "'");
    }

    public List<Student> getAllStudents() {
        return students;
    }
}