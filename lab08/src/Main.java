package lab08.src;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import lab08.src.VehicleRentalSystem.InsuranceType;
import lab08.src.VehicleRentalSystem.RentalAgreement;
import lab08.src.VehicleRentalSystem.Vehicle;
import lab08.src.VehicleRentalSystem.VehicleClass;
import lab08.src.ui.CreateRentalRequest;
import lab08.src.ui.InMemoryRentalRepository;
import lab08.src.ui.InMemoryVehicleCatalog;
import lab08.src.ui.RentalController;
import lab08.src.ui.RentalRepository;
import lab08.src.ui.VehicleCatalog;

public class Main {

    public static void main(String[] args) {
        VehicleCatalog vehicleCatalog = new InMemoryVehicleCatalog(List.of(
                new Vehicle(1, "Toyota Corolla", VehicleClass.ECONOMY, Money.of(1500)),
                new Vehicle(2, "Toyota Camry", VehicleClass.BUSINESS, Money.of(2200)),
                new Vehicle(3, "Toyota RAV4", VehicleClass.SUV, Money.of(1800))));
        
        RentalRepository rentalRepository = new InMemoryRentalRepository();
        DepositPolicy depositPolicy = ExperienceBasedDepositPolicy.defaultConfig();

        RentalController controller =
                new RentalController(vehicleCatalog, rentalRepository, depositPolicy);

        try (Scanner scanner = new Scanner(System.in)) {
            runConsole(scanner, vehicleCatalog, controller);
        }
    }

    private static void runConsole(
            Scanner scanner,
            VehicleCatalog vehicleCatalog,
            RentalController controller) {
        while (true) {
            System.out.println("=== Оренда автомобіля ===");
            System.out.println("1 — оформити оренду");
            System.out.println("0 — вихід");
            int choice = readInt(scanner, "Ваш вибір: ");

            if (choice == 0) return;
            if (choice != 1) {
                System.out.println("Оберіть 1 або 0.");
                continue;
            }

            try {
                RentalAgreement agreement = readAndCreateRental(scanner, vehicleCatalog, controller);
                printAgreement(agreement);
            } catch (IllegalArgumentException exception) {
                System.out.println("Не вдалося оформити оренду: " + exception.getMessage());
            }
        }
    }

    private static RentalAgreement readAndCreateRental(
            Scanner scanner,
            VehicleCatalog vehicleCatalog,
            RentalController controller) {
        int clientId = readInt(scanner, "ID клієнта: ");
        String clientName = readLine(scanner, "Ім'я клієнта: ");
        int experience = readInt(scanner, "Стаж водіння (років): ");
        String phone = readLine(scanner, "Номер телефону: ");

        System.out.println("Доступні автомобілі:");
        for (Vehicle vehicle : vehicleCatalog.findAll()) {
            System.out.printf(
                    Locale.forLanguageTag("uk-UA"),
                    "%d — %s (%s), %s/день%n",
                    vehicle.getId(),
                    vehicle.getModel(),
                    vehicle.getVehicleClass(),
                    vehicle.getDailyRate());
        }

        int vehicleId = readInt(scanner, "ID автомобіля: ");
        int rentalDays = readInt(scanner, "Кількість днів: ");
        InsuranceType insuranceType = readInsuranceType(scanner);

        CreateRentalRequest request = new CreateRentalRequest(
                clientId,
                clientName,
                experience,
                phone,
                vehicleId,
                rentalDays,
                insuranceType);
        return controller.createRental(request);
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Введіть ціле число.");
            }
        }
    }

    private static String readLine(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static InsuranceType readInsuranceType(Scanner scanner) {
        while (true) {
            System.out.print("Тип страховки (BASIC/FULL): ");
            String input = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
            try {
                return InsuranceType.valueOf(input);
            } catch (IllegalArgumentException exception) {
                System.out.println("Допустимі значення: " + Arrays.toString(InsuranceType.values()));
            }
        }
    }

    private static void printAgreement(RentalAgreement agreement) {
        System.out.printf(
                Locale.forLanguageTag("uk-UA"),
                """
                Ваучер оренди #%d
                Клієнт: %s
                Авто: %s
                Оренда: %s
                Страховка: %s
                Застава: %s
                Загалом: %s
                Статус: %s
                %n""",
                agreement.getId(),
                agreement.getClient().getName(),
                agreement.getVehicle().getModel(),
                agreement.calculateRentalTotal(),
                agreement.calculateInsuranceTotal(),
                agreement.calculateDeposit(),
                agreement.calculateFinalPrice(),
                agreement.getStatus());
    }
}