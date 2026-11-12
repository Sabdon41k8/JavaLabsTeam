package lab13.src.task1;

public abstract class RequestHandler {
    private RequestHandler next;
    private final String handlerName;

    protected RequestHandler(String handlerName) {
        this.handlerName = handlerName;
    }

    public RequestHandler setNext(RequestHandler next) {
        this.next = next;
        return next;
    }

    public String handle(GuestRequest request) {
        if (canHandle(request)) {
            return process(request);
        }
        if (next != null) {
            return next.handle(request);
        }
        return "Гість отримує повідомлення: на жаль, запит \"" + request.getDescription()
                + "\" наразі не може бути оброблений. Будь ласка, зверніться на рецепцію для уточнення.";
    }

    protected String getHandlerName() {
        return handlerName;
    }

    protected abstract boolean canHandle(GuestRequest request);

    protected abstract String process(GuestRequest request);
}
