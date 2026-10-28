package lab09.src.task1;

public final class Guest {
    private final String name;
    private final String roomNumber;

    public Guest(String name, String roomNumber) {
        this.name = name;
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
