package lab14.src.task1;

public class LockSystemService implements CheckoutListener {
    @Override
    public void onGuestCheckout(CheckoutEvent event) {
        System.out.println("[Система замків] Анулювання ключа для номера " + event.getRoomNumber()
                + " (гість: " + event.getGuestName() + ").");
    }

    @Override
    public String getName() {
        return "Система замків";
    }
}
