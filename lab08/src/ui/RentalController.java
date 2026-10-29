package lab08.src.ui;

import java.util.Objects;

import lab08.src.DepositPolicy;
import lab08.src.VehicleRentalSystem.Client;
import lab08.src.VehicleRentalSystem.RentalAgreement;
import lab08.src.VehicleRentalSystem.Vehicle;

public class RentalController {
    private final VehicleCatalog vehicleCatalog;
    private final RentalRepository rentalRepository;
    private final DepositPolicy depositPolicy;
    private int nextRentalId = 1;

    public RentalController(
            VehicleCatalog vehicleCatalog,
            RentalRepository rentalRepository,
            DepositPolicy depositPolicy) {
        this.vehicleCatalog = Objects.requireNonNull(vehicleCatalog, "Каталог авто є обов'язковим");
        this.rentalRepository = Objects.requireNonNull(rentalRepository, "Репозиторій є обов'язковим");
        this.depositPolicy = Objects.requireNonNull(depositPolicy, "Політика застави є обов'язковою");
    }

    public synchronized RentalAgreement createRental(CreateRentalRequest request) {
        Objects.requireNonNull(request, "Запит на оренду є обов'язковим");

        Client client = new Client(
                request.clientId(),
                request.clientName(),
                request.driverExperienceYears(),
                request.clientPhone());
        Vehicle vehicle = vehicleCatalog.findById(request.vehicleId());

        RentalAgreement agreement = RentalAgreement.create(
                nextRentalId,
                client,
                vehicle,
                request.rentalDays(),
                request.insuranceType(),
                depositPolicy);

        agreement.approve();
        rentalRepository.save(agreement);
        nextRentalId++;

        return agreement;
    }
}
