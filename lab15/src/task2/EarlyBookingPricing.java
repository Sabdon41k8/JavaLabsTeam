package lab15.src.task2;

public class EarlyBookingPricing implements PricingStrategy {
    @Override public String getName() { return "раннє бронювання"; }
    @Override public double calculate(double basePricePerNight, int nights) {
        return basePricePerNight * nights * 0.85;
    }
}
