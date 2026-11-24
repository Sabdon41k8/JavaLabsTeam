package lab15.src.task2;

public class StandardPricing implements PricingStrategy {
    @Override public String getName() { return "стандартний тариф"; }
    @Override public double calculate(double basePricePerNight, int nights) {
        return basePricePerNight * nights;
    }
}
