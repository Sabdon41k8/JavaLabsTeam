package lab15.src.task2;

public class Main {
    public static void main(String[] args) {
        double luxeBasePrice = 2500.0;
        int nights = 5;

        System.out.println("=== Сценарій 1: 5 ночей у люксі (стандартний і сезонний) ===");
        Booking booking = new Booking("Люкс", luxeBasePrice, nights, new StandardPricing());
        booking.printTotal();
        booking.setPricingStrategy(new SeasonalPricing());
        booking.printTotal();

        System.out.println();
        System.out.println("=== Сценарій 2: зміна правила на «постійний гість» ===");
        booking.setPricingStrategy(new LoyalGuestPricing());
        booking.printTotal();

        System.out.println();
        System.out.println("=== Сценарій 3: нове правило «останні хвилини» (окремий клас) ===");
        booking.setPricingStrategy(new LastMinutePricing());
        booking.printTotal();
    }
}
