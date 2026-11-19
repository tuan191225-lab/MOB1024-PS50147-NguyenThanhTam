package cinejava.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cinejava.model.Ticket;
import cinejava.model.VipTicket;
import cinejava.model.StudentTicket;

public class TicketService {

    public static List<Ticket> createSampleTickets() {
        List<Ticket> tickets = new ArrayList<>();

        tickets.add(new Ticket("T01", "Mai", 90000));
        tickets.add(new VipTicket("V01", "Dune", 110000, 0.2, 30000));
        tickets.add(new StudentTicket("S01", "Mai", 90000, 20));
        tickets.add(new Ticket("T02", "Exhuma", 100000));
        tickets.add(new VipTicket("V02", "Inside Out 2", 90000, 0.1, 25000));

        return tickets;
    }

    public static void printAll(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            ticket.printInfo();
        }
    }

    public static Ticket findMostExpensive(List<Ticket> tickets) {
        if (tickets == null || tickets.isEmpty()) {
            return null;
        }

        Ticket mostExpensive = tickets.get(0);

        for (Ticket ticket : tickets) {
            if (ticket.finalPrice() > mostExpensive.finalPrice()) {
                mostExpensive = ticket;
            }
        }

        return mostExpensive;
    }

    public static void addInvalidTickets(List<Ticket> tickets) {
        try {
            tickets.add(new StudentTicket("S09", "Dune", 90000, 70));
        } catch (IllegalArgumentException e) {
            System.out.println("[LỖI] " + e.getMessage());
        }

        try {
            tickets.add(new Ticket("T09", "Mai", -5));
        } catch (IllegalArgumentException e) {
            System.out.println("[LỖI] " + e.getMessage());
        }
    }

    public static void importFromLines(List<Ticket> tickets, String[] lines) {
        for (String line : lines) {
            try {
                if (line == null) {
                    throw new IllegalArgumentException("Dòng dữ liệu null");
                }

                // -1 giúp giữ lại trường rỗng, ví dụ NORMAL;;Mai;90000;0
                String[] parts = line.split(";", -1);

                if (parts.length != 5) {
                    throw new IllegalArgumentException(
                        "Dòng dữ liệu phải có đúng 5 trường"
                    );
                }

                String type = parts[0].trim().toUpperCase(Locale.ROOT);
                String id = parts[1];
                String movie = parts[2];
                double basePrice = Double.parseDouble(parts[3].trim());
                String extra = parts[4].trim();

                Ticket ticket;

                if (type.equals("VIP")) {
                    double surchargeRate = Double.parseDouble(extra);
                    ticket = new VipTicket(
                        id, movie, basePrice, surchargeRate, 30000
                    );
                } else if (type.equals("STUDENT")) {
                    int discountPercent = Integer.parseInt(extra);
                    ticket = new StudentTicket(
                        id, movie, basePrice, discountPercent
                    );
                } else {
                    // NORMAL hoặc loại khác: tạo vé thường, bỏ qua tham số thứ 5.
                    ticket = new Ticket(id, movie, basePrice);
                }

                tickets.add(ticket);
                System.out.print("OK   ");
                ticket.printInfo();

            } catch (NumberFormatException e) {
                System.out.println("[LỖI SỐ] " + line);
            } catch (IllegalArgumentException e) {
                System.out.println("[LỖI] " + e.getMessage());
            }
        }
    }
}
