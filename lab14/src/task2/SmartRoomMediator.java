package lab14.src.task2;

public class SmartRoomMediator implements RoomMediator {
    private final WindowSensor windowSensor;
    private final PresenceSensor presenceSensor;
    private final EveningTimer eveningTimer;
    private final AirConditioner airConditioner;
    private final Lighting lighting;
    private final Curtains curtains;

    public SmartRoomMediator(WindowSensor windowSensor, PresenceSensor presenceSensor,
                             EveningTimer eveningTimer, AirConditioner airConditioner,
                             Lighting lighting, Curtains curtains) {
        this.windowSensor = windowSensor;
        this.presenceSensor = presenceSensor;
        this.eveningTimer = eveningTimer;
        this.airConditioner = airConditioner;
        this.lighting = lighting;
        this.curtains = curtains;

        register(windowSensor);
        register(presenceSensor);
        register(eveningTimer);
        register(airConditioner);
        register(lighting);
        register(curtains);
    }

    @Override
    public void register(RoomDevice device) {
        device.setMediator(this);
    }

    @Override
    public void notify(RoomDevice source, RoomEvent event) {
        Log.event("Координатор", "отримав від «" + source.getName() + "» подію: "
                + event.getDescription());

        switch (event) {
            case WINDOW_OPENED:
                airConditioner.handle(event);
                break;
            case GUEST_LEFT:
                airConditioner.handle(event);
                lighting.handle(event);
                break;
            case EVENING:
                curtains.handle(event);
                lighting.handle(event);
                break;
        }
    }
}
