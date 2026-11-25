package lab14.src.task2;

public class EveningTimer extends SmartDevice {
    @Override
    public String getName() {
        return "Таймер вечора";
    }

    public void eveningArrived() {
        emit(RoomEvent.EVENING);
    }
}
