package lab15.src.task2;

public class Booking {
    private final String roomType;
    private final double basePricePerNight;
    private final int nights;
    private PricingStrategy pricingStrategy;

    public Booking(String roomType, double basePricePerNight, int nights, PricingStrategy pricingStrategy) {
        this.roomType = roomType;
        this.basePricePerNight = basePricePerNight;
        this.nights = nights;
        this.pricingStrategy = pricingStrategy;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
        System.out.println("  Правило змінено на: " + pricingStrategy.getName());
    }

    public double calculateTotal() {
        return pricingStrategy.calculate(basePricePerNight, nights);
    }

    public void printTotal() {
        double total = calculateTotal();
        System.out.printf("  %s, %d ночей, правило «%s»: %.2f грн%n",
                roomType, nights, pricingStrategy.getName(), total);
    }
}
