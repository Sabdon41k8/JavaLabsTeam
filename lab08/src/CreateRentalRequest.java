package lab08.src;

import lab08.src.VehicleRentalSystem.InsuranceType;

public record CreateRentalRequest(
        int clientId,
        String clientName,
        int driverExperienceYears,
        String clientPhone,
        int vehicleId,
        int rentalDays,
        InsuranceType insuranceType) {
}
