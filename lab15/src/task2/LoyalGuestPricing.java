package lab15.src.task2;

public class LoyalGuestPricing implements PricingStrategy {
    @Override public String getName() { return "постійний гість"; }
    @Override public double calculate(double basePricePerNight, int nights) {
        return basePricePerNight * nights * 0.9;
    }
}
