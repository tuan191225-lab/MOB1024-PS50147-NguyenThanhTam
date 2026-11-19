package cinejava;

import java.util.List;

import cinejava.model.Ticket;
import cinejava.service.TicketService;

public class Main {
    public static void main(String[] args) {
        // Bài 3: tạo danh sách vé mẫu
        List<Ticket> tickets = TicketService.createSampleTickets();

        System.out.println("===== DANH SÁCH VÉ =====");
        TicketService.printAll(tickets);

        // Tìm và in vé đắt nhất
        Ticket mostExpensive = TicketService.findMostExpensive(tickets);
        if (mostExpensive != null) {
            System.out.print("Vé đắt nhất: ");
            mostExpensive.printInfo();
        }

        // Thử thêm dữ liệu không hợp lệ
        System.out.println();
        System.out.println("===== DỮ LIỆU LỖI =====");
        TicketService.addInvalidTickets(tickets);

        // Bài 4: nhập vé từ chuỗi
        System.out.println();
        System.out.println("===== NHẬP VÉ TỪ CHUỖI =====");

        String[] lines = {
            "VIP;V03;Mai;100000;0.15",
            "STUDENT;S02;Dune;abc;10",
            "NORMAL;;Mai;90000;0",
            "STUDENT;S03;Exhuma;95000;30"
        };

        TicketService.importFromLines(tickets, lines);

        System.out.println("Tổng số vé: " + tickets.size());
    }
}
