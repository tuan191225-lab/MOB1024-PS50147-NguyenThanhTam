package Lab4_Demo.bai2.service;

import java.util.ArrayList;
import java.util.List;
import Lab4_Demo.bai2.exception.DuplicateEmployeeException;
import Lab4_Demo.bai2.exception.InvalidSalaryException;
import Lab4_Demo.bai2.model.Employee;

public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee emp) throws DuplicateEmployeeException, InvalidSalaryException {
        if (emp == null) {
            throw new IllegalArgumentException("Thông tin nhân viên không được null!");
        }
        if (emp.getId() == null || emp.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("Mã nhân viên không được để trống!");
        }
        if (emp.getName() == null || emp.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên nhân viên không được để trống!");
        }
        if (emp.getSalary() <= 0) {
            throw new InvalidSalaryException("Lương nhân viên phải lớn hơn 0 (hiện tại: " + emp.getSalary() + ")!");
        }

        for (Employee e : employees) {
            if (e.getId().equalsIgnoreCase(emp.getId().trim())) {
                throw new DuplicateEmployeeException("Mã nhân viên '" + emp.getId().trim() + "' đã tồn tại!");
            }
        }

        emp.setId(emp.getId().trim());
        emp.setName(emp.getName().trim());
        employees.add(emp);
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }
}