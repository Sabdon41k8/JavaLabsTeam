package lab16.src.task1;

public class TaxVisitor implements ChargeVisitor {
    private double totalTax;

    @Override
    public void visit(AccommodationCharge charge) {
        applyTax(charge, 0.18);
    }

    @Override
    public void visit(FoodCharge charge) {
        applyTax(charge, 0.20);
    }

    @Override
    public void visit(SpaCharge charge) {
        applyTax(charge, 0.07);
    }

    @Override
    public void visit(RentalCharge charge) {
        applyTax(charge, 0.10);
    }

    @Override
    public void visit(TouristTaxCharge charge) {
        applyTax(charge, 0.00);
    }

    private void applyTax(Charge charge, double rate) {
        double tax = charge.getAmount() * rate;
        totalTax += tax;
        System.out.printf("  %-35s сума: %8.2f грн, ставка: %5.1f%%, податок: %8.2f грн%n",
                charge.getDescription(), charge.getAmount(), rate * 100, tax);
    }

    public double getTotalTax() {
        return totalTax;
    }
}
