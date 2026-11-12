package lab13.src.task1;

public class ReceptionHandler extends RequestHandler {
    private final boolean acceptCleaningForMorning;

    public ReceptionHandler() {
        this(false);
    }

    public ReceptionHandler(boolean acceptCleaningForMorning) {
        super("Рецепція");
        this.acceptCleaningForMorning = acceptCleaningForMorning;
    }

    @Override
    protected boolean canHandle(GuestRequest request) {
        RequestType type = request.getType();
        if (type == RequestType.TOWELS || type == RequestType.WAKE_UP_CALL) {
            return true;
        }
        if (acceptCleaningForMorning && type == RequestType.CLEANING) {
            return true;
        }
        return false;
    }

    @Override
    protected String process(GuestRequest request) {
        if (request.getType() == RequestType.CLEANING) {
            return getHandlerName() + " обробила запит: " + request.getDescription() + " (прийнято, прибирання — на ранок)";
        }
        return getHandlerName() + " обробила запит: " + request.getDescription();
    }
}
