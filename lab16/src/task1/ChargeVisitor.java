package lab16.src.task1;

public interface ChargeVisitor {
    void visit(AccommodationCharge charge);
    void visit(FoodCharge charge);
    void visit(SpaCharge charge);
    void visit(RentalCharge charge);
    void visit(TouristTaxCharge charge);
}
