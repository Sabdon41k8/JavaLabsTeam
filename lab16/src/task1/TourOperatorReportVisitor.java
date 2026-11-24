package lab16.src.task1;

public class TourOperatorReportVisitor implements ChargeVisitor {
    private double totalAmount;
    private int items;

    @Override
    public void visit(AccommodationCharge charge) {
        add(charge, "Проживання");
    }

    @Override
    public void visit(FoodCharge charge) {
        add(charge, "Харчування");
    }

    @Override
    public void visit(SpaCharge charge) {
        add(charge, "SPA");
    }

    @Override
    public void visit(RentalCharge charge) {
        add(charge, "Прокат");
    }

    @Override
    public void visit(TouristTaxCharge charge) {
        add(charge, "Туристичний збір");
    }

    private void add(Charge charge, String category) {
        items++;
        totalAmount += charge.getAmount();
        System.out.printf("  [%s] %s: %.2f грн%n",
                category, charge.getDescription(), charge.getAmount());
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getItems() {
        return items;
    }
}
