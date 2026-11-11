package lab11.src.task2;

public class Task2Runner {
        public static void main(String[] args) {
        Task2CheckInFacade.BookingVerificationService bookings = new Task2CheckInFacade.BookingVerificationService();
        Task2CheckInFacade.GuestRegistrationService registration = new Task2CheckInFacade.GuestRegistrationService();
        Task2CheckInFacade.RoomAssignmentService rooms = new Task2CheckInFacade.RoomAssignmentService();
        Task2CheckInFacade.KeyCardService keyCards = new Task2CheckInFacade.KeyCardService();
        Task2CheckInFacade.BillingAccountService billing = new Task2CheckInFacade.BillingAccountService();
        Task2CheckInFacade.HousekeepingNotifier housekeeping = new Task2CheckInFacade.HousekeepingNotifier();
 
        Task2CheckInFacade.CheckInFacade facade = new Task2CheckInFacade.CheckInFacade(
                bookings, registration, rooms, keyCards, billing, housekeeping);
 
        System.out.println("=== Ситуація 1: заселення через рецепцію ===");
        int room = new Task2CheckInFacade.ReceptionDesk(facade)
                .checkInGuest(new Task2CheckInFacade.Guest("Іван Коваленко", "BK-1001"));
 
        System.out.println("\n=== Ситуація 2: онлайн-заселення (та сама операція) ===");
        new Task2CheckInFacade.OnlineCheckInModule(facade)
                .checkInGuest(new Task2CheckInFacade.Guest("Олена Мельник", "BK-2002"));
 
        System.out.println("\n=== Ситуація 3: перевипуск ключ-карти без заселення ===");
        Task2CheckInFacade.Log.event("ReceptionDesk", "Гість загубив ключ-карту, звернення напряму до підсистеми");
        keyCards.reissue(room, "Іван Коваленко");
    }

}
