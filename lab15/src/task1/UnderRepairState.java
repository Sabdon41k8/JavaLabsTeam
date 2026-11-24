package lab15.src.task1;

public class UnderRepairState implements RoomState {
    @Override public String getName() { return "на ремонті"; }

    @Override public void book(Room room) { System.out.println("  Відмова: номер на ремонті, бронювання неможливе."); }
    @Override public void checkIn(Room room) { System.out.println("  Відмова: номер на ремонті, заселення неможливе."); }
    @Override public void checkOut(Room room) { System.out.println("  Відмова: номер не зайнятий."); }
    @Override public void clean(Room room) { System.out.println("  Відмова: номер на ремонті, звичайне прибирання недоступне."); }
    @Override public void startRepair(Room room) { System.out.println("  Відмова: номер уже на ремонті."); }

    @Override
    public void finishRepair(Room room) {
        System.out.println("  Ремонт номера " + room.getNumber() + " завершено, номер потребує прибирання.");
        room.setState(new NeedsCleaningState());
    }
}
