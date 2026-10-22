package lab08.src;

import java.util.Objects;
import lab08.src.VehicleRentalSystem.Client;
import lab08.src.VehicleRentalSystem.Vehicle;
import lab08.src.VehicleRentalSystem.RentalAgreement;
import lab08.src.VehicleRentalSystem.InsuranceType;

public class RentalController {
    private final VehicleCatalog vehicleCatalog;
    private final RentalRepository rentalRepository;
    private int nextRentalId = 1;

    public RentalController(
            VehicleCatalog vehicleCatalog,
            RentalRepository rentalRepository) {
        this.vehicleCatalog = Objects.requireNonNull(
                vehicleCatalog, "Vehicle catalog is required");
        this.rentalRepository = Objects.requireNonNull(
                rentalRepository, "Rental repository is required");
    }

    public synchronized RentalAgreement createRental(CreateRentalRequest request) {
        Objects.requireNonNull(request, "Rental request is required");

        Client client = new Client(
                request.clientId(),
                request.clientName(),
                request.driverExperienceYears(),
                request.clientPhone());
        Vehicle vehicle = vehicleCatalog.findById(request.vehicleId());

        RentalAgreement agreement =
                RentalAgreement.create(
                        nextRentalId,
                        client,
                        vehicle,
                        request.rentalDays(),
                        request.insuranceType());

        agreement.approve();
        rentalRepository.save(agreement);
        nextRentalId++;

        return agreement;
    }
}
