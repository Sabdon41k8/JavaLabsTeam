package lab14.src.task1;

public class HousekeepingService implements CheckoutListener {
    @Override
    public void onGuestCheckout(CheckoutEvent event) {
        System.out.println("[Покоївки] Номер " + event.getRoomNumber()
                + " звільнено після гостя " + event.getGuestName() + ". Потрібно прибрати номер.");
    }

    @Override
    public String getName() {
        return "Служба покоївок";
    }
}
