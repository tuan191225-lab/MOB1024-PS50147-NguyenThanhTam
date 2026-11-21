package Lab4_Demo.bai5;

import Lab4_Demo.bai1.model.Student;
import Lab4_Demo.bai5.exception.StudentNotFoundException;
import Lab4_Demo.bai5.service.StudentService;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        service.addStudent(new Student("SV01", "Nguyễn Văn An", 3.40));
        service.addStudent(new Student("SV02", "Trần Thị Bình", 3.75));

        // 1. Tìm kiếm thành công và không tồn tại
        try {
            System.out.println("=== TÌM KIẾM SINH VIÊN ===");
            System.out.println("Tìm SV01: " + service.findById("SV01"));
            System.out.println("Tìm SV99: " + service.findById("SV99"));
        } catch (StudentNotFoundException e) {
            System.out.println("=> Lỗi tìm kiếm: " + e.getMessage() + " [Mã: " + e.getStudentId() + "]");
        }

        // 2. Cập nhật khi mã không tồn tại
        try {
            System.out.println("\n=== CẬP NHẬT SINH VIÊN ===");
            service.updateById("SV88", "Tên Mới", 3.60);
        } catch (StudentNotFoundException e) {
            System.out.println("=> Lỗi cập nhật: " + e.getMessage() + " [Mã: " + e.getStudentId() + "]");
        } catch (IllegalArgumentException e) {
            System.out.println("=> Lỗi dữ liệu cập nhật: " + e.getMessage());
        }

        // 3. Xóa thành công và xóa mã không tồn tại
        try {
            System.out.println("\n=== XÓA SINH VIÊN ===");
            service.deleteById("SV02");
            System.out.println("Đã xóa SV02 thành công.");
            service.deleteById("SV02");
        } catch (StudentNotFoundException e) {
            System.out.println("=> Lỗi xóa: " + e.getMessage() + " [Mã: " + e.getStudentId() + "]");
        }
    }
}