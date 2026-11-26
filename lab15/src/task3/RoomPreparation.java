package lab15.src.task3;

public abstract class RoomPreparation {
    private final String roomType;

    protected RoomPreparation(String roomType) {
        this.roomType = roomType;
    }

    public final void prepare() {
        Log.event(roomType, "початок підготовки номера");
        clean();
        changeLinen();
        restockMinibar();
        checkEquipment();
        additionalPreparation();
        finalInspection();
        Log.event(roomType, "підготовку завершено");
    }

    protected String getRoomType() {
        return roomType;
    }

    protected void clean() {
        Log.event(roomType, "прибирання номера");
    }

    protected void changeLinen() {
        Log.event(roomType, "заміна білизни");
    }

    protected void restockMinibar() {
        Log.event(roomType, "поповнення мінібару");
    }

    protected void checkEquipment() {
        Log.event(roomType, "перевірка обладнання");
    }

    protected void additionalPreparation() {
        Log.event(roomType, "додаткових етапів для цієї категорії немає");
    }

    protected void finalInspection() {
        Log.event(roomType, "фінальний огляд");
    }
}
