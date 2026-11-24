package lab16.src.task1;

public interface Charge {
    String getDescription();
    double getAmount();
    void accept(ChargeVisitor visitor);
}
