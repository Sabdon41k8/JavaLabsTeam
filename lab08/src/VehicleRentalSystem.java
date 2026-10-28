package lab08.src;

import java.util.Objects;

public class VehicleRentalSystem {

    public static class Client {
        private final int id;
        private final String name;
        private final int driverExperienceYears;
        private final String phone;

        public Client(int id, String name, int driverExperienceYears, String phone) {
            if (id <= 0) throw new IllegalArgumentException("ID клієнта має бути додатним");
            if (name == null || name.isBlank()) throw new IllegalArgumentException("Ім'я не може бути порожнім");
            if (driverExperienceYears < 0) throw new IllegalArgumentException("Стаж не може бути від'ємним");
            if (phone == null || phone.isBlank()) throw new IllegalArgumentException("Телефон не може бути порожнім");

            this.id = id;
            this.name = name;
            this.driverExperienceYears = driverExperienceYears;
            this.phone = phone;
        }

        public boolean isInexperiencedDriver(int thresholdYears) {
            return driverExperienceYears < thresholdYears;
        }

        public String getName() { return name; }
        public String getPhone() { return phone; }
        public int getDriverExperienceYears() { return driverExperienceYears; }
    }

    public static class Vehicle {
        private final int id;
        private final String model;
        private final VehicleClass vehicleClass;
        private final Money dailyRate;

        public Vehicle(int id, String model, VehicleClass vehicleClass, Money dailyRate) {
            if (id <= 0) throw new IllegalArgumentException("ID авто має бути додатним");
            if (model == null || model.isBlank()) throw new IllegalArgumentException("Модель не може бути порожньою");
            if (vehicleClass == null) throw new IllegalArgumentException("Клас авто є обов'язковим");
            if (dailyRate == null || dailyRate.compareTo(Money.ZERO) <= 0) {
                throw new IllegalArgumentException("Тариф має бути додатним");
            }

            this.id = id;
            this.model = model;
            this.vehicleClass = vehicleClass;
            this.dailyRate = dailyRate;
        }

        // Information Expert: Vehicle сам обчислює вартість оренди з урахуванням свого класу
        public Money calculateRentalCost(int days) {
            return dailyRate.multiply(days).multiply(vehicleClass.getMultiplier());
        }

        public int getId() { return id; }
        public String getModel() { return model; }
        public VehicleClass getVehicleClass() { return vehicleClass; }
        public Money getDailyRate() { return dailyRate; }
    }

    public static class RentalAgreement {
        private final int id;
        private final Client client;
        private final Vehicle vehicle;
        private final int rentalDays;
        private final InsuranceType insuranceType;
        private final DepositPolicy depositPolicy;

        private RentalStatus status;

        private RentalAgreement(
                int id, Client client, Vehicle vehicle, int rentalDays,
                InsuranceType insuranceType, DepositPolicy depositPolicy) {

            if (id <= 0) throw new IllegalArgumentException("ID договору має бути додатним");
            this.client = Objects.requireNonNull(client, "Клієнт є обов'язковим");
            this.vehicle = Objects.requireNonNull(vehicle, "Авто є обов'язковим");
            if (rentalDays <= 0) throw new IllegalArgumentException("Дні оренди мають бути додатними");
            this.insuranceType = Objects.requireNonNull(insuranceType, "Тип страховки є обов'язковим");
            this.depositPolicy = Objects.requireNonNull(depositPolicy, "Політика застави є обов'язковою");

            this.id = id;
            this.rentalDays = rentalDays;
            this.status = RentalStatus.NEW;
        }

        // Creator (GRASP)
        public static RentalAgreement create(
                int id, Client client, Vehicle vehicle, int rentalDays,
                InsuranceType insuranceType, DepositPolicy depositPolicy) {
            return new RentalAgreement(id, client, vehicle, rentalDays, insuranceType, depositPolicy);
        }

        // Information Expert (GRASP)
        public Money calculateRentalTotal() {
            return vehicle.calculateRentalCost(rentalDays);
        }

        public Money calculateInsuranceTotal() {
            return insuranceType.getDailyCost().multiply(rentalDays);
        }

        // Delegated to DepositPolicy Strategy (Polymorphism + Protected Variations)
        public Money calculateDeposit() {
            return depositPolicy.calculateDeposit(client);
        }

        public Money calculateFinalPrice() {
            return calculateRentalTotal().add(calculateInsuranceTotal());
        }

        public void approve() {
            if (status == RentalStatus.APPROVED) {
                throw new IllegalStateException("Договір вже підтверджено");
            }
            status = RentalStatus.APPROVED;
        }

        public int getId() { return id; }
        public Client getClient() { return client; }
        public Vehicle getVehicle() { return vehicle; }
        public int getRentalDays() { return rentalDays; }
        public InsuranceType getInsuranceType() { return insuranceType; }
        public RentalStatus getStatus() { return status; }
    }

    public enum VehicleClass {
        ECONOMY(1.0),
        BUSINESS(1.3),
        SUV(1.2);

        private final double multiplier;

        VehicleClass(double multiplier) {
            this.multiplier = multiplier;
        }

        public double getMultiplier() { return multiplier; }
    }

    public enum InsuranceType {
        BASIC(Money.of(200)),
        FULL(Money.of(500));

        private final Money dailyCost;

        InsuranceType(Money dailyCost) {
            this.dailyCost = dailyCost;
        }

        public Money getDailyCost() { return dailyCost; }
    }

    public enum RentalStatus { NEW, APPROVED }
}