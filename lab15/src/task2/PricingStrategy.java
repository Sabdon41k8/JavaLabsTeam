package lab15.src.task2;

public interface PricingStrategy {
    String getName();
    double calculate(double basePricePerNight, int nights);
}
