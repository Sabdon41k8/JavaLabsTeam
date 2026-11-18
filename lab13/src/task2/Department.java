package lab13.src.task2;

public enum Department {
    RESTAURANT("Ресторан"),
    SPA("SPA"),
    SKI_RENTAL("Прокат лиж");

    private final String displayName;

    Department(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
