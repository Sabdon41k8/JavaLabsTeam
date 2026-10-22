package lab08.src;

import java.util.List;
import lab08.src.VehicleRentalSystem.RentalAgreement;

public interface RentalRepository {
    void save(RentalAgreement agreement);

    List<RentalAgreement> findAll();
}
