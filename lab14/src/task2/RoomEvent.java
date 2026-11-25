package lab14.src.task2;

public enum RoomEvent {
    WINDOW_OPENED("вікно відчинено"),
    GUEST_LEFT("гість вийшов із номера"),
    EVENING("настав вечір");

    private final String description;

    RoomEvent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
