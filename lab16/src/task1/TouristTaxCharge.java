package lab16.src.task1;

public class TouristTaxCharge implements Charge {
    private final String description;
    private final double amount;

    public TouristTaxCharge(String description, double amount) {
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
