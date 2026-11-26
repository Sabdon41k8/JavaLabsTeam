package lab15.src.task3;

public class ChaletPreparation extends RoomPreparation {
    public ChaletPreparation() {
        super("Шале");
    }

    @Override
    protected void additionalPreparation() {
        Log.event(getRoomType(), "розпалювання каміна");
    }
}
