package Lab4_Demo.bai2;

import java.util.List;
import java.util.Scanner;
import Lab4_Demo.bai2.exception.DuplicateEmployeeException;
import Lab4_Demo.bai2.exception.InvalidSalaryException;
import Lab4_Demo.bai2.model.Employee;
import Lab4_Demo.bai2.service.EmployeeService;

public class Main {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        while (true) {
            System.out.println("\n===== QUẢN LÝ NHÂN VIÊN =====");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            String choice = sc.nextLine().trim();
            
            if (choice.equals("0")) {
                System.out.println("Tạm biệt!");
                break;
            }

            switch (choice) {
                case "1":
                    try {
                        System.out.print("Nhập mã NV: ");
                        String id = sc.nextLine();

                        System.out.print("Nhập họ tên: ");
                        String name = sc.nextLine();

                        System.out.print("Nhập lương: ");
                        double salary = Double.parseDouble(sc.nextLine().trim());

                        Employee emp = new Employee(id, name, salary);
                        service.addEmployee(emp);
                        System.out.println("=> Thêm nhân viên thành công!");
                    } catch (NumberFormatException e) {
                        System.out.println("=> Lỗi định dạng: Lương phải là một số hợp lệ!");
                    } catch (DuplicateEmployeeException e) {
                        System.out.println("=> Lỗi trùng mã: " + e.getMessage());
                    } catch (InvalidSalaryException e) {
                        System.out.println("=> Lỗi lương: " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("=> Lỗi dữ liệu: " + e.getMessage());
                    }
                    break;

                case "2":
                    List<Employee> list = service.getAllEmployees();
                    if (list.isEmpty()) {
                        System.out.println("=> Danh sách nhân viên đang trống.");
                    } else {
                        System.out.println("--- DANH SÁCH NHÂN VIÊN ---");
                        for (Employee e : list) {
                            System.out.println(e);
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