package lab14.src.task2;

public class Lighting extends SmartDevice {
    private String mode = "звичайне світло";

    @Override
    public String getName() {
        return "Освітлення";
    }

    @Override
    public void handle(RoomEvent event) {
        if (event == RoomEvent.GUEST_LEFT) {
            mode = "вимкнено";
            log("вимикається: гість вийшов із номера.");
        } else if (event == RoomEvent.EVENING) {
            mode = "нічне освітлення";
            log("перемикається на нічне освітлення.");
        }
        log("поточний режим: " + mode + ".");
    }
}
