package Lab4_Demo.bai3;

import Lab4_Demo.bai1.model.Student;
import Lab4_Demo.bai2.model.Employee;
import Lab4_Demo.bai3.service.GenericManager;

public class Main {
    public static void main(String[] args) {
        // 1 class GenericManager<T>, 2 kiểu dữ liệu khác nhau, không cần ép kiểu
        GenericManager<Student> studentManager = new GenericManager<>();
        studentManager.add(new Student("SV01", "Nguyễn Văn An", 3.45));
        studentManager.add(new Student("SV02", "Trần Thị Bình", 3.80));

        GenericManager<Employee> employeeManager = new GenericManager<>();
        employeeManager.add(new Employee("NV01", "Lê Minh Cường", 1500.0));
        employeeManager.add(new Employee("NV02", "Phạm Thu Dung", 1850.5));

        System.out.println("=== DANH SÁCH SINH VIÊN (Số lượng: " + studentManager.size() + ") ===");
        studentManager.display();

        // Lấy phần tử theo index không cần ép kiểu (cast)
        Student firstStudent = studentManager.get(0);
        System.out.println("Sinh viên đầu tiên: " + firstStudent.getName() + " - GPA: " + firstStudent.getGpa());

        System.out.println("\n=== DANH SÁCH NHÂN VIÊN (Số lượng: " + employeeManager.size() + ") ===");
        employeeManager.display();

        Employee firstEmployee = employeeManager.get(0);
        System.out.println("Nhân viên đầu tiên: " + firstEmployee.getName() + " - Lương: " + firstEmployee.getSalary());

        // Thử thêm Employee vào studentManager -> sẽ báo lỗi biên dịch ngay:
        // studentManager.add(new Employee("NV03", "Lỗi Kiểu", 1200.0));
    }
}