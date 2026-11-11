package lab11.src.task2;
import java.util.HashMap;
import java.util.Map;

public class Task2CheckInFacade {
    static class Log {
        private static int seconds = 14 * 3600; // умовний час, починаємо з 14:00:00
 
        static void event(String source, String message) {
            seconds += 7;
            System.out.printf("[%02d:%02d:%02d] [%s] %s%n",
                    seconds / 3600, (seconds / 60) % 60, seconds % 60, source, message);
        }
    }
 
    static class Guest {
        private final String name;
        private final String bookingId;
 
        Guest(String name, String bookingId) {
            this.name = name;
            this.bookingId = bookingId;
        }
 
        String getName() { return name; }
        String getBookingId() { return bookingId; }
    }
 
    static class BookingVerificationService {
        private final Map<String, String> bookings = new HashMap<>();
 
        BookingVerificationService() {
            bookings.put("BK-1001", "Стандарт");
            bookings.put("BK-2002", "Люкс");
        }
 
        String verify(String bookingId) {
            String category = bookings.get(bookingId);
            if (category == null) {
                throw new IllegalArgumentException("Бронювання не знайдено: " + bookingId);
            }
            Log.event("BookingVerificationService", "Бронювання " + bookingId + " підтверджено, категорія: " + category);
            return category;
        }
    }
 
    static class GuestRegistrationService {
        void register(Guest guest) {
            Log.event("GuestRegistrationService", "Гостя «" + guest.getName() + "» зареєстровано");
        }
    }
 
    static class RoomAssignmentService {
        private int nextStandard = 305;
        private int nextLuxury = 501;
 
        int assign(String category) {
            int room = category.equals("Люкс") ? nextLuxury++ : nextStandard++;
            Log.event("RoomAssignmentService", "Призначено номер " + room + " (" + category + ")");
            return room;
        }
    }
 
    static class KeyCardService {
        void issue(int room, String guestName) {
            Log.event("KeyCardService", "Видано ключ-карту до номера " + room + " для «" + guestName + "»");
        }
 
        void reissue(int room, String guestName) {
            Log.event("KeyCardService", "Перевипущено ключ-карту до номера " + room + " для «" + guestName + "»; стару карту деактивовано");
        }
    }
 
    static class BillingAccountService {
        void open(Guest guest, int room) {
            Log.event("BillingAccountService", "Відкрито рахунок гостя «" + guest.getName() + "» (номер " + room + ")");
        }
    }
 
    static class HousekeepingNotifier {
        void notifyCheckIn(int room) {
            Log.event("HousekeepingNotifier", "Службу покоївок повідомлено про заселення в номер " + room);
        }
    }
 
    static class CheckInFacade {
        private final BookingVerificationService bookings;
        private final GuestRegistrationService registration;
        private final RoomAssignmentService rooms;
        private final KeyCardService keyCards;
        private final BillingAccountService billing;
        private final HousekeepingNotifier housekeeping;
 
        CheckInFacade(BookingVerificationService bookings, GuestRegistrationService registration,
                      RoomAssignmentService rooms, KeyCardService keyCards,
                      BillingAccountService billing, HousekeepingNotifier housekeeping) {
            this.bookings = bookings;
            this.registration = registration;
            this.rooms = rooms;
            this.keyCards = keyCards;
            this.billing = billing;
            this.housekeeping = housekeeping;
        }
 
        int checkIn(Guest guest) {
            Log.event("CheckInFacade", "Початок заселення гостя «" + guest.getName() + "»");
            String category = bookings.verify(guest.getBookingId());
            registration.register(guest);
            int room = rooms.assign(category);
            keyCards.issue(room, guest.getName());
            billing.open(guest, room);
            housekeeping.notifyCheckIn(room);
            Log.event("CheckInFacade", "Заселення завершено, номер " + room);
            return room;
        }
    }
 
    static class ReceptionDesk {
        private final CheckInFacade facade;
 
        ReceptionDesk(CheckInFacade facade) { this.facade = facade; }
 
        int checkInGuest(Guest guest) {
            Log.event("ReceptionDesk", "Адміністратор заселяє гостя «" + guest.getName() + "»");
            return facade.checkIn(guest);
        }
    }
 
    static class OnlineCheckInModule {
        private final CheckInFacade facade;
 
        OnlineCheckInModule(CheckInFacade facade) { this.facade = facade; }
 
        int checkInGuest(Guest guest) {
            Log.event("OnlineCheckInModule", "Гість «" + guest.getName() + "» пройшов онлайн-заселення");
            return facade.checkIn(guest);
        }
    }
 
}

