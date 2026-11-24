package lab15.src.task1;

public class NeedsCleaningState implements RoomState {
    @Override public String getName() { return "потребує прибирання"; }

    @Override public void book(Room room) { System.out.println("  Відмова: номер потребує прибирання, бронювання недоступне."); }
    @Override public void checkIn(Room room) { System.out.println("  Відмова: не можна заселити гостя в номер, що потребує прибирання."); }
    @Override public void checkOut(Room room) { System.out.println("  Відмова: номер не зайнятий."); }

    @Override
    public void clean(Room room) {
        System.out.println("  Прибирання номера " + room.getNumber() + " завершено.");
        room.setState(new FreeCleanState());
    }

    @Override
    public void startRepair(Room room) {
        System.out.println("  Номер " + room.getNumber() + " переведено на ремонт.");
        room.setState(new UnderRepairState());
    }

    @Override public void finishRepair(Room room) { System.out.println("  Відмова: номер не на ремонті."); }
}
