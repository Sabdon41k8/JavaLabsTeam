package lab13.src.task1;

public class TechnicalHandler extends RequestHandler {
    public TechnicalHandler() {
        super("Технічна служба");
    }

    @Override
    protected boolean canHandle(GuestRequest request) {
        return request.getType() == RequestType.MALFUNCTION;
    }

    @Override
    protected String process(GuestRequest request) {
        return getHandlerName() + " обробила запит: " + request.getDescription();
    }
}
