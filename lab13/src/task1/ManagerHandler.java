package lab13.src.task1;

public class ManagerHandler extends RequestHandler {
    public ManagerHandler() {
        super("Черговий менеджер");
    }

    @Override
    protected boolean canHandle(GuestRequest request) {
        RequestType type = request.getType();
        return type == RequestType.COMPLAINT || type == RequestType.COMPENSATION;
    }

    @Override
    protected String process(GuestRequest request) {
        return getHandlerName() + " обробила запит: " + request.getDescription();
    }
}
