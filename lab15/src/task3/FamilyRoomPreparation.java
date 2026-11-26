package lab15.src.task3;

public class FamilyRoomPreparation extends RoomPreparation {
    public FamilyRoomPreparation() {
        super("Сімейний номер");
    }

    @Override
    protected void additionalPreparation() {
        Log.event(getRoomType(), "підготовка дитячого набору та додаткових спальних місць");
    }
}
