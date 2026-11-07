package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        // Tạo danh sách sinh viên
        List<Student> students = new ArrayList<>();

        students.add(new Student("SV001", "Nguyen Van An",
                StudentType.REGULAR));

        students.add(new Student("SV002", "Tran Thi Binh",
                StudentType.PART_TIME));

        students.add(new Student("SV003", "Le Van Cuong",
                StudentType.INTERNATIONAL));

        students.add(new Student("SV004", "Pham Thi Dung",
                StudentType.REGULAR));

        students.add(new Student("SV005", "Hoang Van Nam",
                StudentType.PART_TIME));

        students.add(new Student("SV006", "Nguyen Thi Hoa",
                StudentType.INTERNATIONAL));

        // 1. Hiển thị toàn bộ sinh viên
        System.out.println("===== DANH SACH TAT CA SINH VIEN =====");

        for (Student student : students) {
            System.out.println(student);
        }

        // 2. Hiển thị sinh viên theo từng loại
        System.out.println("\n===== DANH SACH SINH VIEN THEO LOAI =====");

        for (StudentType type : StudentType.values()) {
            System.out.println("\n" + type.getDescription() + ":");

            for (Student student : students) {
                if (student.getType() == type) {
                    System.out.println(student);
                }
            }
        }

        // 3. Đếm số lượng sinh viên mỗi loại bằng Map
        Map<StudentType, Integer> counts = new HashMap<>();

        // Khởi tạo số lượng bằng 0 cho từng loại
        for (StudentType type : StudentType.values()) {
            counts.put(type, 0);
        }

        // Đếm sinh viên
        for (Student student : students) {
            StudentType type = student.getType();

            counts.put(type, counts.get(type) + 1);
        }

        // Hiển thị kết quả đếm
        System.out.println("\n===== THONG KE SO LUONG SINH VIEN =====");

        for (StudentType type : StudentType.values()) {
            System.out.println(
                type.getDescription() + ": " + counts.get(type)
            );
        }
    }
}