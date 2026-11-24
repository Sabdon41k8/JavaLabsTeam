package lab16.src.task1;

public class AccommodationCharge implements Charge {
    private final String description;
    private final double amount;

    public AccommodationCharge(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void accept(ChargeVisitor visitor) {
        visitor.visit(this);
    }
}
