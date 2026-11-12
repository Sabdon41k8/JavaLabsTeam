package lab12.src.task1;
import java.time.LocalDate;

public class OccupancyRecord {
    private final int roomNumber;
    private final LocalDate date;
    private final RoomState state;
    private final RoomCategory category;

    public OccupancyRecord(int roomNumber, LocalDate date, RoomState state, RoomCategory category) {
        this.roomNumber = roomNumber;
        this.date = date;
        this.state = state;
        this.category = category;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public RoomState getState() {
        return state;
    }

    public RoomCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "OccupancyRecord{roomNumber=" + roomNumber +
                ", date=" + date +
                ", state=" + state +
                ", category=" + category.getName() +
                ", categoryObj=" + System.identityHashCode(category) + "}";
    }
}
