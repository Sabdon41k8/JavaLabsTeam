package lab15.src.task1;

public class FreeCleanState implements RoomState {
    @Override public String getName() { return "вільний і прибраний"; }

    @Override
    public void book(Room room) {
        System.out.println("  Бронювання номера " + room.getNumber() + " виконано.");
        room.setState(new BookedState());
    }

    @Override
    public void checkIn(Room room) {
        System.out.println("  Заселення гостя в номер " + room.getNumber() + " виконано.");
        room.setState(new OccupiedState());
    }

    @Override public void checkOut(Room room) { System.out.println("  Відмова: номер вільний, виїжджати нікому."); }
    @Override public void clean(Room room) { System.out.println("  Відмова: номер уже прибраний."); }

    @Override
    public void startRepair(Room room) {
        System.out.println("  Номер " + room.getNumber() + " переведено на ремонт.");
        room.setState(new UnderRepairState());
    }

    @Override public void finishRepair(Room room) { System.out.println("  Відмова: номер не на ремонті."); }
}
