package lab15.src.task1;

public class Room {
    private final int number;
    private RoomState state;

    public Room(int number) {
        this.number = number;
        this.state = new FreeCleanState();
    }

    public int getNumber() { return number; }
    public RoomState getState() { return state; }

    public void setState(RoomState state) {
        this.state = state;
        System.out.println("  [Номер " + number + "] -> стан: " + state.getName());
    }

    public void book() { state.book(this); }
    public void checkIn() { state.checkIn(this); }
    public void checkOut() { state.checkOut(this); }
    public void clean() { state.clean(this); }
    public void startRepair() { state.startRepair(this); }
    public void finishRepair() { state.finishRepair(this); }
}
