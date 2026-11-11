package lab11.src.task3;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Task3LockAdapter {
            static class Log {
        private static int seconds = 15 * 3600; // умовний час, починаємо з 15:00:00
 
        static void event(String source, String message) {
            seconds += 5;
            System.out.printf("[%02d:%02d:%02d] [%s] %s%n",
                    seconds / 3600, (seconds / 60) % 60, seconds % 60, source, message);
        }
    }
 
    //Цільовий інтерфейс системи рецепції (Target)
    static class AccessConfirmation {
        private final boolean granted;
        private final String message;
 
        AccessConfirmation(boolean granted, String message) {
            this.granted = granted;
            this.message = message;
        }
 
        boolean isGranted() { return granted; }
        String getMessage() { return message; }
    }
 
    interface RoomAccessSystem {
        AccessConfirmation grantAccess(int roomNumber, LocalDateTime checkOut);
    }
 
    // Адаптовані класи (Adaptee)
    static class VendorALockApi {
        int programAccess(String floorWingCode, long expirySecondsSince2000) {
            Log.event("VendorALockApi", "programAccess(\"" + floorWingCode + "\", " + expirySecondsSince2000 + ")");
            return 0; // 0 = OK
        }
    }
 
    static class VendorBLockApi {
        boolean setDoorAccess(char building, int level, int door, long expiryUnixMillis) {
            Log.event("VendorBLockApi", "setDoorAccess('" + building + "', " + level + ", " + door + ", " + expiryUnixMillis + ")");
            return true;
        }
    }
 
    static class VendorALockAdapter implements RoomAccessSystem {
        private static final LocalDateTime EPOCH_2000 = LocalDateTime.of(2000, 1, 1, 0, 0);
        private final VendorALockApi api;
 
        VendorALockAdapter(VendorALockApi api) { this.api = api; }
 
        @Override
        public AccessConfirmation grantAccess(int roomNumber, LocalDateTime checkOut) {
            int floor = roomNumber / 100;
            int door = roomNumber % 100;
            char wing = door <= 10 ? 'A' : 'B';
            String code = String.format("F%02d-%c%02d", floor, wing, door);
            long seconds = checkOut.toEpochSecond(ZoneOffset.UTC) - EPOCH_2000.toEpochSecond(ZoneOffset.UTC);
            Log.event("VendorALockAdapter", "Номер " + roomNumber + " -> код " + code + ", виїзд " + checkOut + " -> " + seconds + " с від 2000 р.");
            int status = api.programAccess(code, seconds);
            return status == 0
                    ? new AccessConfirmation(true, "Доступ до номера " + roomNumber + " запрограмовано до " + checkOut)
                    : new AccessConfirmation(false, "Замок повернув код помилки " + status);
        }
    }
 
    static class VendorBLockAdapter implements RoomAccessSystem {
        private final VendorBLockApi api;
 
        VendorBLockAdapter(VendorBLockApi api) { this.api = api; }
 
        @Override
        public AccessConfirmation grantAccess(int roomNumber, LocalDateTime checkOut) {
            int level = roomNumber / 100;
            int door = roomNumber % 100;
            long millis = checkOut.toInstant(ZoneOffset.UTC).toEpochMilli();
            Log.event("VendorBLockAdapter", "Номер " + roomNumber + " -> корпус 'N', поверх " + level + ", двері " + door + "; виїзд " + checkOut + " -> " + millis + " мс Unix");
            boolean ok = api.setDoorAccess('N', level, door, millis);
            return ok
                    ? new AccessConfirmation(true, "Доступ до номера " + roomNumber + " запрограмовано до " + checkOut)
                    : new AccessConfirmation(false, "Замок відхилив запит");
        }
    }
 
    static class ReceptionSystem {
        void programAccess(RoomAccessSystem locks, int room, LocalDateTime checkOut) {
            Log.event("ReceptionSystem", "Програмування доступу: номер " + room + ", виїзд " + checkOut);
            AccessConfirmation c = locks.grantAccess(room, checkOut);
            Log.event("ReceptionSystem", (c.isGranted() ? "Підтвердження: " : "Відмова: ") + c.getMessage());
        }
    }
}
