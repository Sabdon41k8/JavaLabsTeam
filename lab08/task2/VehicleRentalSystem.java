package lab08.task2;

public class VehicleRentalSystem {

    public static class Client {

        private final int id;
        private final String name;
        private final int driverExperienceYears;
        private final String phone;

        public Client(
                int id,
                String name,
                int driverExperienceYears,
                String phone) {

            if (driverExperienceYears < 0) {
                throw new IllegalArgumentException(
                        "Experience cannot be negative");
            }

            this.id = id;
            this.name = name;
            this.driverExperienceYears = driverExperienceYears;
            this.phone = phone;
        }

        public boolean isInexperiencedDriver() {
            return driverExperienceYears < 3;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }
    }

    public static class Vehicle {

        private final int id;
        private final String model;
        private final VehicleClass vehicleClass;
        private final double dailyRate;

        public Vehicle(
                int id,
                String model,
                VehicleClass vehicleClass,
                double dailyRate) {

            this.id = id;
            this.model = model;
            this.vehicleClass = vehicleClass;
            this.dailyRate = dailyRate;
        }

        public double calculateRentalCost(int days) {
            return dailyRate
                    * days
                    * vehicleClass.getMultiplier();
        }

        public String getModel() {
            return model;
        }
    }

    public static class RentalAgreement {

        private static final double BASE_DEPOSIT = 5000;
        private static final double EXTRA_DEPOSIT = 3000;

        private final int id;
        private final Client client;
        private final Vehicle vehicle;
        private final int rentalDays;
        private final InsuranceType insuranceType;

        private RentalStatus status;

        private RentalAgreement(
                int id,
                Client client,
                Vehicle vehicle,
                int rentalDays,
                InsuranceType insuranceType) {

            if (rentalDays <= 0) {
                throw new IllegalArgumentException(
                        "Rental days must be positive");
            }

            this.id = id;
            this.client = client;
            this.vehicle = vehicle;
            this.rentalDays = rentalDays;
            this.insuranceType = insuranceType;
            this.status = RentalStatus.NEW;
        }

        // GRASP Creator
        public static RentalAgreement create(
                int id,
                Client client,
                Vehicle vehicle,
                int rentalDays,
                InsuranceType insuranceType) {

            return new RentalAgreement(
                    id,
                    client,
                    vehicle,
                    rentalDays,
                    insuranceType
            );
        }

        // GRASP Information Expert
        public double calculateRentalTotal() {
            return vehicle.calculateRentalCost(rentalDays);
        }

        public double calculateInsuranceTotal() {
            return insuranceType.getDailyCost()
                    * rentalDays;
        }

        public double calculateDeposit() {

            double deposit = BASE_DEPOSIT;

            if (client.isInexperiencedDriver()) {
                deposit += EXTRA_DEPOSIT;
            }

            return deposit;
        }

        public double calculateFinalPrice() {
            return calculateRentalTotal()
                    + calculateInsuranceTotal();
        }

        public void approve() {

            if (status == RentalStatus.APPROVED) {
                throw new IllegalStateException(
                        "Agreement already approved");
            }

            status = RentalStatus.APPROVED;
        }

        public RentalStatus getStatus() {
            return status;
        }
    }

    public enum VehicleClass {

        ECONOMY(1.0),
        BUSINESS(1.3),
        SUV(1.2);

        private final double multiplier;

        VehicleClass(double multiplier) {
            this.multiplier = multiplier;
        }

        public double getMultiplier() {
            return multiplier;
        }
    }

    public enum InsuranceType {

        BASIC(200),
        FULL(500);

        private final double dailyCost;

        InsuranceType(double dailyCost) {
            this.dailyCost = dailyCost;
        }

        public double getDailyCost() {
            return dailyCost;
        }
    }

    public enum RentalStatus {
        NEW,
        APPROVED
    }
}