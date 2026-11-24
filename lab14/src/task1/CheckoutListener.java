package lab14.src.task1;

public interface CheckoutListener {
    void onGuestCheckout(CheckoutEvent event);
    String getName();
}
