package lab09.src.task2;

public enum Department {
    RECEPTION("Рецепція"),
    RESTAURANT("Ресторан"),
    SPA("SPA-центр"),
    EQUIPMENT_RENTAL("Прокат спорядження");

    private final String displayName;

    Department(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
