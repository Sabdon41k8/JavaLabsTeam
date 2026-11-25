package lab14.src.task2;

public abstract class SmartDevice implements RoomDevice {
    protected RoomMediator mediator;

    @Override
    public void setMediator(RoomMediator mediator) {
        this.mediator = mediator;
    }

    protected void emit(RoomEvent event) {
        Log.event(getName(), "повідомляє координатора: " + event.getDescription());
        mediator.notify(this, event);
    }

    protected void log(String text) {
        Log.event(getName(), text);
    }
}
