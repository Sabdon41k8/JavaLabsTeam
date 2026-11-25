package lab14.src.task2;

public class Main {
    public static void main(String[] args) {
        scenarioWindowOpened();
        scenarioGuestLeft();
        scenarioEvening();
    }

    /** Усі пристрої знають лише координатора й не звертаються одне до одного. */
    private static final class TestRoom {
        private final WindowSensor windowSensor = new WindowSensor();
        private final PresenceSensor presenceSensor = new PresenceSensor();
        private final EveningTimer eveningTimer = new EveningTimer();
        private final AirConditioner airConditioner = new AirConditioner();
        private final Lighting lighting = new Lighting();
        private final Curtains curtains = new Curtains();
        private final SmartRoomMediator mediator = new SmartRoomMediator(
                windowSensor, presenceSensor, eveningTimer, airConditioner, lighting, curtains);
    }

    private static void scenarioWindowOpened() {
        System.out.println("=== Сценарій 1: гість відчиняє вікно ===");
        TestRoom room = new TestRoom();
        room.windowSensor.openWindow();
    }

    private static void scenarioGuestLeft() {
        System.out.println();
        System.out.println("=== Сценарій 2: гість виходить із номера ===");
        TestRoom room = new TestRoom();
        room.presenceSensor.guestLeft();
    }

    private static void scenarioEvening() {
        System.out.println();
        System.out.println("=== Сценарій 3: настає вечір ===");
        TestRoom room = new TestRoom();
        room.eveningTimer.eveningArrived();
    }
}
