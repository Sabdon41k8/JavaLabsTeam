package lab15.src.task1;

public class BookedState implements RoomState {
    @Override public String getName() { return "заброньований"; }

    @Override public void book(Room room) { System.out.println("  Відмова: номер уже заброньований."); }

    @Override
    public void checkIn(Room room) {
        System.out.println("  Заселення гостя за бронюванням у номер " + room.getNumber() + " виконано.");
        room.setState(new OccupiedState());
    }

    @Override public void checkOut(Room room) { System.out.println("  Відмова: номер заброньований, але гість ще не заселений."); }
    @Override public void clean(Room room) { System.out.println("  Відмова: прибирання недоступне для заброньованого номера."); }
    @Override public void startRepair(Room room) { System.out.println("  Відмова: не можна ремонтувати заброньований номер."); }
    @Override public void finishRepair(Room room) { System.out.println("  Відмова: номер не на ремонті."); }
}
