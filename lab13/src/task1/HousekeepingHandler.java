package lab13.src.task1;

public class HousekeepingHandler extends RequestHandler {
    public HousekeepingHandler() {
        super("Служба покоївок");
    }

    @Override
    protected boolean canHandle(GuestRequest request) {
        return request.getType() == RequestType.CLEANING;
    }

    @Override
    protected String process(GuestRequest request) {
        return getHandlerName() + " обробила запит: " + request.getDescription();
    }
}
