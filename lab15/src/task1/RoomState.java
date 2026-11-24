package lab15.src.task1;

public interface RoomState {
    String getName();
    void book(Room room);
    void checkIn(Room room);
    void checkOut(Room room);
    void clean(Room room);
    void startRepair(Room room);
    void finishRepair(Room room);
}
