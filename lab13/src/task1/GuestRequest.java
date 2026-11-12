package lab13.src.task1;

public class GuestRequest {
    private final RequestType type;
    private final String description;

    public GuestRequest(RequestType type, String description) {
        this.type = type;
        this.description = description;
    }

    public RequestType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Запит{" + type + ", \"" + description + "\"}";
    }
}
