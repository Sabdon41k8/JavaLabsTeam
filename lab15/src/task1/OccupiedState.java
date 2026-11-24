package lab15.src.task1;

public class OccupiedState implements RoomState {
    @Override public String getName() { return "зайнятий"; }

    @Override public void book(Room room) { System.out.println("  Відмова: номер зайнятий, бронювання неможливе."); }
    @Override public void checkIn(Room room) { System.out.println("  Відмова: у номері вже проживає гість."); }

    @Override
    public void checkOut(Room room) {
        System.out.println("  Виїзд гостя з номера " + room.getNumber() + " оформлено.");
        room.setState(new NeedsCleaningState());
    }

    @Override public void clean(Room room) { System.out.println("  Відмова: не можна прибирати зайнятий номер."); }
    @Override public void startRepair(Room room) { System.out.println("  Відмова: не можна ремонтувати зайнятий номер."); }
    @Override public void finishRepair(Room room) { System.out.println("  Відмова: номер не на ремонті."); }
}
