package lab09.src.task2;

import lab09.src.common.Log;

import java.math.BigDecimal;

public final class Task2Runner {
    public void run() {
        Log.event("Task2Runner", "=== Завдання 2: Singleton — спільна служба розрахунків ===");

        BillingService receptionService = BillingService.getInstance();
        BillingService restaurantService = BillingService.getInstance();
        BillingService spaService = BillingService.getInstance();
        BillingService rentalService = BillingService.getInstance();

        Log.event("Рецепція", "Отримано службу розрахунків ID=" + receptionService.getServiceId());
        Log.event("Ресторан", "Отримано службу розрахунків ID=" + restaurantService.getServiceId());
        Log.event("SPA-центр", "Отримано службу розрахунків ID=" + spaService.getServiceId());
        Log.event("Прокат спорядження", "Отримано службу розрахунків ID=" + rentalService.getServiceId());
        Log.event("Task2Runner", "Усі підрозділи використовують один екземпляр: "
                + (receptionService == restaurantService
                && receptionService == spaService
                && receptionService == rentalService));

        String guestId = "G-305";
        String guestName = "Олена Коваль";
        String roomNumber = "305";

        receptionService.addCharge(guestId, guestName, roomNumber, Department.RECEPTION,
                "Проживання, 2 ночі", new BigDecimal("3600.00"));
        restaurantService.addCharge(guestId, guestName, roomNumber, Department.RESTAURANT,
                "Вечеря в ресторані", new BigDecimal("780.00"));
        spaService.addCharge(guestId, guestName, roomNumber, Department.SPA,
                "SPA-процедура", new BigDecimal("950.00"));
        rentalService.addCharge(guestId, guestName, roomNumber, Department.EQUIPMENT_RENTAL,
                "Прокат лижного спорядження", new BigDecimal("620.00"));

        Log.event("Рецепція", "Гість із кімнати 305 виїжджає; формуємо спільний рахунок");
        receptionService.printFinalBill(guestId);
        Log.event("Task2Runner", "=== Завдання 2 завершено ===");
    }
}
