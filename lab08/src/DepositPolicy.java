package lab08.src;

import lab08.src.VehicleRentalSystem.Client;

// Protected Variations: ізолює домен від алгоритмів обчислення застави
public interface DepositPolicy {
    Money calculateDeposit(Client client);
}