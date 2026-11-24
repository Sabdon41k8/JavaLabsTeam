package lab16.src.task1;

public class BonusPointsVisitor implements ChargeVisitor {
    private int totalPoints;

    @Override
    public void visit(AccommodationCharge charge) {
        addPoints(charge, 2);
    }

    @Override
    public void visit(FoodCharge charge) {
        addPoints(charge, 1);
    }

    @Override
    public void visit(SpaCharge charge) {
        addPoints(charge, 3);
    }

    @Override
    public void visit(RentalCharge charge) {
        addPoints(charge, 1);
    }

    @Override
    public void visit(TouristTaxCharge charge) {
        System.out.printf("  %-35s сума: %8.2f грн, бали: не нараховуються%n",
                charge.getDescription(), charge.getAmount());
    }

    private void addPoints(Charge charge, int pointsPer100) {
        int points = (int) Math.floor(charge.getAmount() / 100.0) * pointsPer100;
        totalPoints += points;
        System.out.printf("  %-35s сума: %8.2f грн, бали: %d%n",
                charge.getDescription(), charge.getAmount(), points);
    }

    public int getTotalPoints() {
        return totalPoints;
    }
}
