package lab14.src.task1;

import java.util.ArrayList;
import java.util.List;

public class CheckoutNotifier {
    private final List<CheckoutListener> listeners = new ArrayList<>();

    public void subscribe(CheckoutListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void unsubscribe(CheckoutListener listener) {
        listeners.remove(listener);
    }

    public void notifyCheckout(CheckoutEvent event) {
        // iterate over a copy to allow safe subscribe/unsubscribe during notification if needed
        for (CheckoutListener listener : new ArrayList<>(listeners)) {
            listener.onGuestCheckout(event);
        }
    }
}
