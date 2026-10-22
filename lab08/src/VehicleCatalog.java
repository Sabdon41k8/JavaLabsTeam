package lab08.src;

import java.util.List;
import lab08.src.VehicleRentalSystem.Vehicle;

public interface VehicleCatalog {
    List<Vehicle> findAll();

    Vehicle findById(int id);
}
