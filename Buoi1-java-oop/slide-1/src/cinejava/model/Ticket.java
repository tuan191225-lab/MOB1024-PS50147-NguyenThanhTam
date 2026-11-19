package cinejava.model;

import java.util.Locale;

public class Ticket {
    private String id;
    private String movie;
    private double basePrice;

    public Ticket(String id, String movie, double basePrice) {
        setId(id);
        setMovie(movie);
        setBasePrice(basePrice);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã vé rỗng");
        }
        this.id = id.trim();
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        if (movie == null || movie.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên phim rỗng");
        }
        this.movie = movie.trim();
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        if (basePrice < 0) {
            throw new IllegalArgumentException("Giá vé phải >= 0");
        }
        this.basePrice = basePrice;
    }

    public double finalPrice() {
        return basePrice;
    }

    public void printInfo() {
        System.out.printf(
            Locale.US,
            "%-5s | %-12s | %,10.0f%n",
            id, movie, finalPrice()
        );
    }
}
