package lab14.src.task1;

public class MinibarService implements CheckoutListener {
    @Override
    public void onGuestCheckout(CheckoutEvent event) {
        System.out.println("[Мінібар] Перевірка споживання в номері " + event.getRoomNumber()
                + " (гість: " + event.getGuestName() + ").");
    }

    @Override
    public String getName() {
        return "Мінібар";
    }
}
