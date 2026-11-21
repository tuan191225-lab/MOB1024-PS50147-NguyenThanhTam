package Lab4_Demo.bai4;

import Lab4_Demo.bai1.model.Student;
import Lab4_Demo.bai4.exception.EmptyListException;
import Lab4_Demo.bai4.service.GenericManager;

public class Main {
    public static void main(String[] args) {
        GenericManager<Student> studentManager = new GenericManager<>();

        // Lần 1: gọi khi danh sách rỗng
        System.out.println("=== LẦN 1: DANH SÁCH RỖNG ===");
        try {
            Student first = studentManager.getFirst();
            System.out.println("Phần tử đầu: " + first);
            Student last = studentManager.getLast();
            System.out.println("Phần tử cuối: " + last);
        } catch (EmptyListException e) {
            System.out.println("=> Lỗi: " + e.getMessage());
        } finally {
            System.out.println("-> Khối finally lần 1 luôn chạy (số phần tử hiện tại: " + studentManager.size() + ")");
        }

        // Thêm dữ liệu vào danh sách
        studentManager.add(new Student("SV01", "Nguyễn Văn An", 3.50));
        studentManager.add(new Student("SV02", "Trần Thị Bình", 3.75));
        studentManager.add(new Student("SV03", "Lê Minh Cường", 3.90));

        // Lần 2: gọi sau khi đã thêm dữ liệu
        System.out.println("\n=== LẦN 2: SAU KHI THÊM DỮ LIỆU ===");
        try {
            Student first = studentManager.getFirst();
            System.out.println("Phần tử đầu: " + first);
            Student last = studentManager.getLast();
            System.out.println("Phần tử cuối: " + last);
        } catch (EmptyListException e) {
            System.out.println("=> Lỗi: " + e.getMessage());
        } finally {
            System.out.println("-> Khối finally lần 2 luôn chạy (số phần tử hiện tại: " + studentManager.size() + ")");
        }
    }
}