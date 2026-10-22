package lab08.src;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lab08.src.VehicleRentalSystem.Vehicle;

public class InMemoryVehicleCatalog implements VehicleCatalog {
    private final List<Vehicle> vehicles;
    private final Map<Integer, Vehicle> vehiclesById;

    public InMemoryVehicleCatalog(List<Vehicle> vehicles) {
        this.vehicles = List.copyOf(vehicles);
        this.vehiclesById = new HashMap<>();

        for (Vehicle vehicle : this.vehicles) {
            if (vehiclesById.putIfAbsent(vehicle.getId(), vehicle) != null) {
                throw new IllegalArgumentException(
                        "Duplicate vehicle id: " + vehicle.getId());
            }
        }
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicles;
    }

    @Override
    public Vehicle findById(int id) {
        Vehicle vehicle = vehiclesById.get(id);
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found: " + id);
        }
        return vehicle;
    }
}
