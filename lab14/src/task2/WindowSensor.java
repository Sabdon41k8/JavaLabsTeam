package lab14.src.task2;

public class WindowSensor extends SmartDevice {
    @Override
    public String getName() {
        return "Датчик вікон";
    }

    public void openWindow() {
        emit(RoomEvent.WINDOW_OPENED);
    }
}
