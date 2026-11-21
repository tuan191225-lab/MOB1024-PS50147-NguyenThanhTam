package Lab4_Demo.bai1;

import Lab4_Demo.bai1.model.Student;
import Lab4_Demo.bai1.service.StudentService;
import java.util.List;
import java.util.Scanner;

public class Main {
    @SuppressWarnings("resource") // Không đóng Scanner(System.in): đóng nó sẽ đóng luôn System.in
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();

        while (true) {
            System.out.println("\n===== QUẢN LÝ SINH VIÊN =====");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    try {
                        System.out.print("Nhập mã SV: ");
                        String id = sc.nextLine();

                        System.out.print("Nhập họ tên: ");
                        String name = sc.nextLine();

                        System.out.print("Nhập GPA (0.0 - 4.0): ");
                        double gpa = Double.parseDouble(sc.nextLine().trim());

                        service.addStudent(id, name, gpa);
                        System.out.println("=> Thêm sinh viên thành công!");
                    } catch (NumberFormatException e) {
                        System.out.println("=> Lỗi định dạng: GPA phải là số thực (ví dụ: 3.25)!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("=> Lỗi dữ liệu: " + e.getMessage());
                    }
                    break;

                case "2":
                    List<Student> list = service.getAllStudents();
                    if (list.isEmpty()) {
                        System.out.println("=> Danh sách sinh viên đang trống.");
                    } else {
                        System.out.println("--- DANH SÁCH SINH VIÊN ---");
                        for (Student s : list) {
                            System.out.println(s);
                        }
                    }
                    break;

                case "0":
                    System.out.println("Thoát chương trình.");
                    return;

                default:
                    System.out.println("=> Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }
}