package lab14.src.task1;

public class Main {
    public static void main(String[] args) {
        CheckoutNotifier notifier = new CheckoutNotifier();

        HousekeepingService housekeeping = new HousekeepingService();
        AccountingService accounting = new AccountingService();
        LockSystemService locks = new LockSystemService();
        MarketingService marketing = new MarketingService();

        System.out.println("=== Сценарій 1: підписані покоївки, бухгалтерія і система замків ===");
        notifier.subscribe(housekeeping);
        notifier.subscribe(accounting);
        notifier.subscribe(locks);

        notifier.notifyCheckout(new CheckoutEvent("Іван Петренко", 101, null, false));

        System.out.println();
        System.out.println("=== Сценарій 2: підписано маркетинг, гість виїжджає з відгуком ===");
        notifier.subscribe(marketing);

        notifier.notifyCheckout(new CheckoutEvent("Олена Коваль", 202, "Чудовий сервіс і затишний номер!", false));

        System.out.println();
        System.out.println("=== Сценарій 3: покоївки не працюють вночі і відписуються ===");
        notifier.unsubscribe(housekeeping);
        System.out.println("(Служба покоївок відписалася від сповіщень)");

        notifier.notifyCheckout(new CheckoutEvent("Марія Сидоренко", 303, null, true));
    }
}
