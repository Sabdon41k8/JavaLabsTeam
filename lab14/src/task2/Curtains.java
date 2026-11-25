package lab14.src.task2;

public class Curtains extends SmartDevice {
    private boolean closed = false;

    @Override
    public String getName() {
        return "Штори";
    }

    @Override
    public void handle(RoomEvent event) {
        if (event == RoomEvent.EVENING && !closed) {
            closed = true;
            log("зачиняються: настав вечір.");
        }
    }
}
