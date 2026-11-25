package lab14.src.task2;

public class AirConditioner extends SmartDevice {
    private boolean running = true;

    @Override
    public String getName() {
        return "Кондиціонер";
    }

    @Override
    public void handle(RoomEvent event) {
        if (event == RoomEvent.WINDOW_OPENED || event == RoomEvent.GUEST_LEFT) {
            if (running) {
                running = false;
                log("вимикається за вказівкою координатора (" + event.getDescription() + ").");
            } else {
                log("уже вимкнено — повторних дій не потрібно.");
            }
        }
    }
}
