package lab08.task2;

public class Main {

    public static void main(String[] args) {

        VehicleRentalSystem.Client client =
                new VehicleRentalSystem.Client(
                        1,
                        "Іван Петренко",
                        1,
                        "+380501234567"
                );

        VehicleRentalSystem.Vehicle vehicle =
                new VehicleRentalSystem.Vehicle(
                        1,
                        "Toyota Corolla",
                        VehicleRentalSystem.VehicleClass.ECONOMY,
                        1500
                );

        VehicleRentalSystem.RentalAgreement agreement =
                VehicleRentalSystem.RentalAgreement.create(
                        1,
                        client,
                        vehicle,
                        5,
                        VehicleRentalSystem.InsuranceType.FULL
                );

        System.out.println("Client: " + client.getName());
        System.out.println("Vehicle: " + vehicle.getModel());

        System.out.println("Rental cost: "
                + agreement.calculateRentalTotal());

        System.out.println("Insurance cost: "
                + agreement.calculateInsuranceTotal());

        System.out.println("Deposit: "
                + agreement.calculateDeposit());

        System.out.println("Final price: "
                + agreement.calculateFinalPrice());

        agreement.approve();

        System.out.println("Status: "
                + agreement.getStatus());
    }
}