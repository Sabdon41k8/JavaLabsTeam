package lab08.src;

import lab08.src.VehicleRentalSystem.Client;

// Polymorphism: конкретна стратегія розрахунку застави на основі стажу водія
public class ExperienceBasedDepositPolicy implements DepositPolicy {
    private final Money baseDeposit;
    private final Money extraDeposit;
    private final int minExperienceYears;

    public ExperienceBasedDepositPolicy(Money baseDeposit, Money extraDeposit, int minExperienceYears) {
        this.baseDeposit = baseDeposit;
        this.extraDeposit = extraDeposit;
        this.minExperienceYears = minExperienceYears;
    }

    public static ExperienceBasedDepositPolicy defaultConfig() {
        return new ExperienceBasedDepositPolicy(Money.of(5000), Money.of(3000), 3);
    }

    @Override
    public Money calculateDeposit(Client client) {
        if (client.isInexperiencedDriver(minExperienceYears)) {
            return baseDeposit.add(extraDeposit);
        }
        return baseDeposit;
    }
}