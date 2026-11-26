package lab15.src.task3;

public class SuitePreparation extends RoomPreparation {
    public SuitePreparation() {
        super("Люкс");
    }

    @Override
    protected void additionalPreparation() {
        Log.event(getRoomType(), "приготування вітального кошика");
    }
}
