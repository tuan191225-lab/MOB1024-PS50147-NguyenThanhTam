import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        bai1();
        bai2();
        bai3();
        bai4();
        bai5();
    }

    // =====================================
    // BAI 1: Lambda, Comparator, Predicate, forEach
    // =====================================

    public static void bai1() {

        System.out.println("\n========== BAI 1 ==========");

        ArrayList<String> names = new ArrayList<>(
                Arrays.asList(
                        "Nghia", "Duy", "Dang", "Quy", "Dung",
                        "Hoa", "Lan", "Hong", "Phuong", "Nhi"
                )
        );

        // In toàn bộ danh sách
        System.out.println("Danh sach ten:");

        names.forEach(name ->
                System.out.println(name));

        // Lọc tên có độ dài > 5
        System.out.println("\nTen co do dai > 5:");

        names.stream()
                .filter(name -> name.length() > 5)
                .forEach(System.out::println);

        // Sắp xếp tên A-Z
        System.out.println("\nSap xep ten A-Z:");

        names.stream()
                .sorted()
                .forEach(System.out::println);

        // Sắp xếp theo độ dài tên tăng dần
        System.out.println("\nSap xep theo do dai ten:");

        names.stream()
                .sorted((a, b) ->
                        Integer.compare(a.length(), b.length()))
                .forEach(System.out::println);
    }


    // =====================================
    // BAI 2: filter, map, sorted, count
    // =====================================

    public static List<Employee> createEmployees() {

        List<Employee> employees = Arrays.asList(
                new Employee(1, "An", 18000000),
                new Employee(2, "Binh", 12000000),
                new Employee(3, "Anh", 20000000),
                new Employee(4, "Cuong", 16000000),
                new Employee(5, "Dung", 14000000),
                new Employee(6, "Hoa", 25000000),
                new Employee(7, "A Long", 17000000),
                new Employee(8, "Minh", 11000000)
        );

        return employees;
    }

    public static void bai2() {

        System.out.println("\n========== BAI 2 ==========");

        List<Employee> employees = createEmployees();

        // In danh sách nhân viên
        System.out.println("Danh sach nhan vien:");

        employees.forEach(System.out::println);

        // Lọc nhân viên có lương >= 15 triệu
        System.out.println("\nNhan vien co luong >= 15 trieu:");

        employees.stream()
                .filter(e -> e.getSalary() >= 15000000)
                .forEach(System.out::println);

        // Sắp xếp lương giảm dần
        System.out.println("\nSap xep luong giam dan:");

        employees.stream()
                .sorted((a, b) ->
                        Double.compare(b.getSalary(), a.getSalary()))
                .forEach(System.out::println);

        // Lấy danh sách tên nhân viên
        System.out.println("\nDanh sach ten nhan vien:");

        List<String> employeeNames = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        employeeNames.forEach(System.out::println);

        // Đếm nhân viên có tên bắt đầu bằng chữ A
        System.out.println("\nDem nhan vien co ten bat dau bang A:");

        long count = employees.stream()
                .filter(e -> e.getName()
                        .toUpperCase()
                        .startsWith("A"))
                .count();

        System.out.println("So luong: " + count);
    }


    // =====================================
    // BAI 3: mapToDouble, sum, max, average
    // =====================================

    public static void bai3() {

        System.out.println("\n========== BAI 3 ==========");

        List<Employee> employees = createEmployees();

        // Tổng lương
        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();

        System.out.println("Tong luong: " + totalSalary);

        // Lương trung bình
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);

        System.out.println("Luong trung binh: " + averageSalary);

        // Nhân viên có lương cao nhất
        Employee maxSalaryEmployee = employees.stream()
                .max(Comparator.comparingDouble(
                        Employee::getSalary))
                .orElse(null);

        System.out.println("Nhan vien co luong cao nhat:");

        if (maxSalaryEmployee != null) {
            System.out.println(maxSalaryEmployee);
        }
    }


    // =====================================
    // BAI 4: OOP + Enum + Stream
    // =====================================

    public static List<Student> createStudents() {

        List<Student> students = Arrays.asList(
                new Student(1, "An", StudentType.REGULAR, 3.5),
                new Student(2, "Binh", StudentType.PART_TIME, 3.0),
                new Student(3, "Cuong", StudentType.INTERNATIONAL, 3.8),
                new Student(4, "Dung", StudentType.REGULAR, 2.9),
                new Student(5, "Hoa", StudentType.PART_TIME, 3.6),
                new Student(6, "Lan", StudentType.INTERNATIONAL, 3.2),
                new Student(7, "Minh", StudentType.REGULAR, 3.9),
                new Student(8, "Nam", StudentType.PART_TIME, 2.8),
                new Student(9, "Phuong", StudentType.INTERNATIONAL, 3.7),
                new Student(10, "Tuan", StudentType.REGULAR, 3.3)
        );

        return students;
    }

    public static void bai4() {

        System.out.println("\n========== BAI 4 ==========");

        List<Student> students = createStudents();

        // In danh sách sinh viên
        System.out.println("Danh sach sinh vien:");

        students.forEach(System.out::println);

        // Lọc sinh viên quốc tế có GPA >= 3.2
        System.out.println("\nSinh vien quoc te co GPA >= 3.2:");

        students.stream()
                .filter(s ->
                        s.getType() == StudentType.INTERNATIONAL
                                && s.getGpa() >= 3.2)
                .forEach(System.out::println);

        // Sắp xếp GPA giảm dần và lấy top 3
        System.out.println("\nTop 3 sinh vien co GPA cao nhat:");

        students.stream()
                .sorted((a, b) ->
                        Double.compare(b.getGpa(), a.getGpa()))
                .limit(3)
                .forEach(System.out::println);

        // Lấy tên sinh viên PART_TIME
        System.out.println("\nDanh sach ten sinh vien PART_TIME:");

        List<String> partTimeNames = students.stream()
                .filter(s ->
                        s.getType() == StudentType.PART_TIME)
                .map(Student::getName)
                .collect(Collectors.toList());

        partTimeNames.forEach(System.out::println);
    }


    // =====================================
    // BAI 5: Collectors, groupingBy, counting
    // =====================================

    public static void bai5() {

        System.out.println("\n========== BAI 5 ==========");

        List<Student> students = createStudents();

        // Đếm số sinh viên theo từng loại
        Map<StudentType, Long> studentCount =
                students.stream()
                        .collect(Collectors.groupingBy(
                                Student::getType,
                                Collectors.counting()
                        ));

        System.out.println("\nSo sinh vien theo tung loai:");

        studentCount.forEach((type, count) ->
                System.out.println(type + ": " + count));

        // Tính GPA trung bình theo từng loại
        Map<StudentType, Double> averageGpa =
                students.stream()
                        .collect(Collectors.groupingBy(
                                Student::getType,
                                Collectors.averagingDouble(
                                        Student::getGpa)
                        ));

        System.out.println("\nGPA trung binh theo tung loai:");

        averageGpa.forEach((type, avg) ->
                System.out.println(type + ": " + avg));

        // Tìm loại sinh viên có GPA trung bình cao nhất
        System.out.println(
                "\nLoai sinh vien co GPA trung binh cao nhat:");

        averageGpa.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry ->
                        System.out.println(
                                entry.getKey()
                                        + " - GPA: "
                                        + entry.getValue()
                        ));
    }
}