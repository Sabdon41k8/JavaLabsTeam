package lab14.src.task1;

public class MarketingService implements CheckoutListener {
    @Override
    public void onGuestCheckout(CheckoutEvent event) {
        if (event.hasReview()) {
            System.out.println("[Маркетинг] Отримано відгук від гостя " + event.getGuestName()
                    + " (номер " + event.getRoomNumber() + "): \"" + event.getReview() + "\"");
        } else {
            System.out.println("[Маркетинг] Гість " + event.getGuestName()
                    + " виїхав без відгуку — додаткових дій не потрібно.");
        }
    }

    @Override
    public String getName() {
        return "Маркетинг";
    }
}
