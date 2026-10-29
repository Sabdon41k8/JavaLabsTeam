package lab08.src.ui;

import java.util.List;

import lab08.src.VehicleRentalSystem;
import lab08.src.VehicleRentalSystem.Vehicle;

public interface VehicleCatalog {
    List<Vehicle> findAll();

    Vehicle findById(int id);
}
