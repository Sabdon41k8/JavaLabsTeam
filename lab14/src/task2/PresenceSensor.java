package lab14.src.task2;

public class PresenceSensor extends SmartDevice {
    @Override
    public String getName() {
        return "Датчик присутності";
    }

    public void guestLeft() {
        emit(RoomEvent.GUEST_LEFT);
    }
}
