package lab14.src.task2;

public interface RoomMediator {
    void register(RoomDevice device);

    void notify(RoomDevice source, RoomEvent event);
}
