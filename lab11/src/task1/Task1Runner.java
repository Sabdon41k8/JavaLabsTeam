package lab11.src.task1;

public class Task1Runner {
    public static void main(String[] args) {
        Task1RoomBooking.Room standardRoom = new Task1RoomBooking.StandardRoom();
        System.out.println("=== Стандартний номер ===");
        printRoomInfo(standardRoom);

        System.out.println("\n=== Номер зі сніданком, видом на гори і паркуванням ===");
        Task1RoomBooking.Room roomWithServices = new Task1RoomBooking.Parking(
                new Task1RoomBooking.MountainView(
                        new Task1RoomBooking.Breakfast(
                                new Task1RoomBooking.StandardRoom())));
        printRoomInfo(roomWithServices);

        System.out.println("\n=== Інший номер з пізнім виїздом і дитячим ліжком ===");
        Task1RoomBooking.Room anotherRoom = new Task1RoomBooking.BabyCot(
                new Task1RoomBooking.LateCheckout(
                        new Task1RoomBooking.StandardRoom()));
        printRoomInfo(anotherRoom);
    }

    private static void printRoomInfo(Task1RoomBooking.Room room) {
        System.out.println("Опис: " + room.getDescription());
        System.out.printf("Вартість за ніч: %.2f грн%n", room.getCost());
    }
}
