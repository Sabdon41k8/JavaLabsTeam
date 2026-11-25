package lab14.src.task2;

public interface RoomDevice {
    String getName();

    void setMediator(RoomMediator mediator);

    default void handle(RoomEvent event) {
        // Сенсори лише повідомляють про події; реакцію визначає координатор.
    }
}
