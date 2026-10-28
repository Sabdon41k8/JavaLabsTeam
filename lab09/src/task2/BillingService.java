package lab09.src.task2;

import lab09.src.common.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class BillingService {
    private static final BillingService INSTANCE = new BillingService();

    private final Map<String, List<Charge>> chargesByGuest = new LinkedHashMap<>();
    private final String serviceId;

    private BillingService() {
        Log.event("BillingService", "Підготовка єдиної служби розрахунків (імітація підключення)");
        try {
            Thread.sleep(250);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Ініціалізацію служби розрахунків перервано.", exception);
        }
        serviceId = "BILL-" + Integer.toHexString(System.identityHashCode(this)).toUpperCase();
        Log.event("BillingService", "Службу розрахунків ініціалізовано; ID=" + serviceId);
    }

    public static BillingService getInstance() {
        return INSTANCE;
    }

    public String getServiceId() {
        return serviceId;
    }

    public synchronized void addCharge(String guestId, String guestName, String roomNumber,
                                       Department department, String description, BigDecimal amount) {
        if (guestId == null || guestId.isBlank()) {
            throw new IllegalArgumentException("ID гостя не може бути порожнім.");
        }
        if (guestName == null || guestName.isBlank()) {
            throw new IllegalArgumentException("ПІБ гостя не може бути порожнім.");
        }
        if (roomNumber == null || roomNumber.isBlank()) {
            throw new IllegalArgumentException("Номер кімнати не може бути порожнім.");
        }
        if (department == null || description == null || description.isBlank()) {
            throw new IllegalArgumentException("Підрозділ і опис послуги обов'язкові.");
        }
        if (amount == null || amount.signum() < 0) {
            throw new IllegalArgumentException("Сума нарахування має бути невід'ємною.");
        }

        chargesByGuest.computeIfAbsent(guestId, key -> new ArrayList<>())
                .add(new Charge(guestId, guestName, roomNumber, department, description, amount));
        Log.event(department.getDisplayName(), "Служба " + serviceId + ": до рахунку гостя "
                + guestName + " (кімната " + roomNumber + ") додано "
                + description + " — " + amount.toPlainString() + " грн");
    }

    public synchronized void printFinalBill(String guestId) {
        List<Charge> charges = chargesByGuest.getOrDefault(guestId, List.of());
        if (charges.isEmpty()) {
            Log.event("BillingService", "Для гостя " + guestId + " нарахувань немає");
            return;
        }

        Charge first = charges.get(0);
        Log.event("BillingService", "=== ПІДСУМКОВИЙ РАХУНОК: " + first.guestName()
                + ", кімната " + first.roomNumber() + " ===");
        BigDecimal total = BigDecimal.ZERO;
        for (Charge charge : charges) {
            Log.event("BillingService", charge.department().getDisplayName()
                    + " | " + charge.description() + " | "
                    + charge.amount().toPlainString() + " грн");
            total = total.add(charge.amount());
        }
        Log.event("BillingService", "РАЗОМ ДО СПЛАТИ: " + total.toPlainString() + " грн");
        Log.event("BillingService", "=== Кінець рахунку ===");
    }

    private record Charge(String guestId, String guestName, String roomNumber,
                          Department department, String description, BigDecimal amount) {
    }
}
