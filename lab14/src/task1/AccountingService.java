package lab14.src.task1;

public class AccountingService implements CheckoutListener {
    @Override
    public void onGuestCheckout(CheckoutEvent event) {
        System.out.println("[Бухгалтерія] Закриття рахунку гостя " + event.getGuestName()
                + " за номер " + event.getRoomNumber() + ".");
    }

    @Override
    public String getName() {
        return "Бухгалтерія";
    }
}
