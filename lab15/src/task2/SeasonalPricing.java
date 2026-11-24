package lab15.src.task2;

public class SeasonalPricing implements PricingStrategy {
    @Override public String getName() { return "сезонний тариф (лижний сезон)"; }
    @Override public double calculate(double basePricePerNight, int nights) {
        return basePricePerNight * nights * 1.3;
    }
}
