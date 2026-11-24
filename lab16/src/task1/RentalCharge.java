package lab16.src.task1;

public class RentalCharge implements Charge {
    private final String description;
    private final double amount;

    public RentalCharge(String description, double amount) {
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
