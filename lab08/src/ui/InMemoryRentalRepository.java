package lab08.src.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lab08.src.VehicleRentalSystem.RentalAgreement;

public class InMemoryRentalRepository implements RentalRepository {
    private final List<RentalAgreement> agreements = new ArrayList<>();

    @Override
    public synchronized void save(RentalAgreement agreement) {
        Objects.requireNonNull(agreement, "Agreement is required");
        if (agreements.stream().anyMatch(
                existing -> existing.getId() == agreement.getId())) {
            throw new IllegalArgumentException(
                    "Agreement already exists: " + agreement.getId());
        }
        agreements.add(agreement);
    }

    @Override
    public synchronized List<RentalAgreement> findAll() {
        return List.copyOf(agreements);
    }
}
