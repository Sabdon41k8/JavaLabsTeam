package lab15.src.task2;

public class LastMinutePricing implements PricingStrategy {
    @Override public String getName() { return "останні хвилини"; }
    @Override public double calculate(double basePricePerNight, int nights) {
        return basePricePerNight * nights * 0.7;
    }
}
