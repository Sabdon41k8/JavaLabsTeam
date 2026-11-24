package lab15.src.task1;

public class Main {
    public static void main(String[] args) {
        Room room = new Room(101);

        System.out.println("=== Сценарій 1: повний цикл номера ===");
        System.out.println("Початковий стан: " + room.getState().getName());
        room.book();
        room.checkIn();
        room.checkOut();
        room.clean();

        System.out.println();
        System.out.println("=== Сценарій 2: заселення в номер, що потребує прибирання ===");
        Room dirtyRoom = new Room(102);
        dirtyRoom.checkIn(); // вільний -> зайнятий
        dirtyRoom.checkOut(); // зайнятий -> потребує прибирання
        System.out.println("Спроба заселити гостя:");
        dirtyRoom.checkIn();

        System.out.println();
        System.out.println("=== Сценарій 3: ремонт і спроба бронювання ===");
        Room repairRoom = new Room(103);
        repairRoom.startRepair();
        System.out.println("Спроба забронювати номер:");
        repairRoom.book();
    }
}
