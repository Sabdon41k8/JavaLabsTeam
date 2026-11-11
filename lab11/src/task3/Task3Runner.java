package lab11.src.task3;
import java.time.LocalDateTime;

public class Task3Runner {
    public static void main(String[] args) {
        Task3LockAdapter.ReceptionSystem reception = new Task3LockAdapter.ReceptionSystem();
        LocalDateTime checkOut = LocalDateTime.of(2026, 11, 14, 12, 0);
 
        System.out.println("=== Ситуація 1: замки вендора A (головний корпус), номер 305 ===");
        reception.programAccess(
                new Task3LockAdapter.VendorALockAdapter(new Task3LockAdapter.VendorALockApi()), 305, checkOut);
 
        System.out.println("\n=== Ситуація 2: замки іншого вендора (новий корпус), той самий код рецепції ===");
        reception.programAccess(
                new Task3LockAdapter.VendorBLockAdapter(new Task3LockAdapter.VendorBLockApi()), 412, checkOut);
    }

}
