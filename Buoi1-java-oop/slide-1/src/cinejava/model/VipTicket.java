package cinejava.model;

public class VipTicket extends Ticket {
    private double surchargeRate;
    private double comboFee;

    public VipTicket(
        String id,
        String movie,
        double basePrice,
        double surchargeRate,
        double comboFee
    ) {
        super(id, movie, basePrice);
        setSurchargeRate(surchargeRate);
        setComboFee(comboFee);
    }

    public double getSurchargeRate() {
        return surchargeRate;
    }

    public void setSurchargeRate(double surchargeRate) {
        if (surchargeRate < 0 || surchargeRate > 1) {
            throw new IllegalArgumentException(
                "Phụ thu phải trong khoảng 0-1"
            );
        }
        this.surchargeRate = surchargeRate;
    }

    public double getComboFee() {
        return comboFee;
    }

    public void setComboFee(double comboFee) {
        if (comboFee < 0) {
            throw new IllegalArgumentException("Phí combo phải >= 0");
        }
        this.comboFee = comboFee;
    }

    @Override
    public double finalPrice() {
        return getBasePrice()
            + getBasePrice() * surchargeRate
            + comboFee;
    }
}
