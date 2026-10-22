package lab07.task2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
 
public class ShippingCostCalculator {
 
 
    public BigDecimal calculateCost(Parcel parcel, ShippingStrategy strategy) {
        Objects.requireNonNull(parcel, "parcel must not be null");
        Objects.requireNonNull(strategy, "strategy must not be null");
 
        BigDecimal cost = strategy.calculate(parcel).setScale(2, RoundingMode.HALF_UP);
        System.out.println("[SHIPPING] Посилка " + parcel.getTrackingNumber()
                + " (" + strategy.name() + "): Вага=" + parcel.getWeightKg()
                + "кг, Відстань=" + parcel.getDistanceKm() + "км -> Вартість: $" + cost);
        return cost;
    }
 
    // Extension Point
 
    public interface ShippingStrategy {
        String name();
 
        BigDecimal calculate(Parcel parcel);
 
        static BigDecimal money(String value) {
            return new BigDecimal(value);
        }
    }
 
    public static class Parcel {
        private final String trackingNumber;
        private final BigDecimal weightKg;
        private final BigDecimal volumeM3;
        private final BigDecimal distanceKm;
 
        public Parcel(String trackingNumber, BigDecimal weightKg, BigDecimal volumeM3, BigDecimal distanceKm) {
            this.trackingNumber = trackingNumber;
            this.weightKg = weightKg;
            this.volumeM3 = volumeM3;
            this.distanceKm = distanceKm;
        }
 
        public String getTrackingNumber() { return trackingNumber; }
        public BigDecimal getWeightKg() { return weightKg; }
        public BigDecimal getVolumeM3() { return volumeM3; }
        public BigDecimal getDistanceKm() { return distanceKm; }
    }

    public static class StandardCourierStrategy implements ShippingStrategy {
        @Override public String name() { return "STANDARD_COURIER"; }
 
        @Override
        public BigDecimal calculate(Parcel p) {
            return ShippingStrategy.money("5.00")
                    .add(p.getWeightKg().multiply(ShippingStrategy.money("1.50")))
                    .add(p.getDistanceKm().multiply(ShippingStrategy.money("0.10")));
        }
    }
 
    public static class ExpressAirStrategy implements ShippingStrategy {
        @Override public String name() { return "EXPRESS_AIR"; }
 
        @Override
        public BigDecimal calculate(Parcel p) {
            BigDecimal weightFee = p.getWeightKg().multiply(ShippingStrategy.money("4.00"));
            BigDecimal volumeFee = p.getVolumeM3().multiply(ShippingStrategy.money("15.00"));
            return ShippingStrategy.money("25.00")
                    .add(weightFee.max(volumeFee))
                    .add(p.getDistanceKm().multiply(ShippingStrategy.money("0.50")));
        }
    }
 
    public static class OvernightExpressStrategy implements ShippingStrategy {
        @Override public String name() { return "OVERNIGHT_EXPRESS"; }
 
        @Override
        public BigDecimal calculate(Parcel p) {
            return ShippingStrategy.money("50.00")
                    .add(p.getWeightKg().multiply(ShippingStrategy.money("3.00")));
        }
    }
 
    public static class LockerPickupStrategy implements ShippingStrategy {
        private static final BigDecimal FREE_LIMIT_KG = ShippingStrategy.money("5.00");
 
        @Override public String name() { return "LOCKER_PICKUP"; }
 
        @Override
        public BigDecimal calculate(Parcel p) {
            BigDecimal extraWeight = p.getWeightKg().subtract(FREE_LIMIT_KG).max(BigDecimal.ZERO);
            return ShippingStrategy.money("3.00")
                    .add(extraWeight.multiply(ShippingStrategy.money("0.80")));
        }
    }
 
    // ===== Демонстрація відкритості до розширення =====
    // Новий спосіб доставки: жоден рядок у ShippingCostCalculator не змінено.
 
    /** $10 базово + $2.50 за кг + $0.30 за км. */
    public static class DroneDeliveryStrategy implements ShippingStrategy {
        @Override public String name() { return "DRONE_DELIVERY"; }
 
        @Override
        public BigDecimal calculate(Parcel p) {
            return ShippingStrategy.money("10.00")
                    .add(p.getWeightKg().multiply(ShippingStrategy.money("2.50")))
                    .add(p.getDistanceKm().multiply(ShippingStrategy.money("0.30")));
        }
    }
 
    public static void main(String[] args) {
        ShippingCostCalculator calculator = new ShippingCostCalculator();
 
        Parcel parcel = new Parcel("UA-0001", new BigDecimal("2"), new BigDecimal("0.1"), new BigDecimal("100"));
        Parcel heavy = new Parcel("UA-0002", new BigDecimal("7"), new BigDecimal("0.2"), new BigDecimal("50"));
 
        calculator.calculateCost(parcel, new StandardCourierStrategy());     // 18.00
        calculator.calculateCost(parcel, new ExpressAirStrategy());          // 83.00
        calculator.calculateCost(parcel, new OvernightExpressStrategy());    // 56.00
        calculator.calculateCost(heavy, new LockerPickupStrategy());         // 4.60
 
        // Нова стратегія без змін у калькуляторі
        calculator.calculateCost(parcel, new DroneDeliveryStrategy());       // 45.00
    }
}
