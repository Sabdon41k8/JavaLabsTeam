package lab13.src.task2;

public class Main {

    private static void executeAndLog(Accountant accountant, BillOperation operation) {
        System.out.println("-> " + operation);
        System.out.println("   Результат: " + accountant.execute(operation));
        System.out.println();
    }

    private static void undoAndLog(Accountant accountant, BillOperation operation, String time) {
        System.out.println("-> Сторнування: " + operation);
        System.out.println("   Результат: " + accountant.undo(operation, time));
        System.out.println();
    }

    public static void main(String[] args) {
        GuestBill bill = new GuestBill("Коваленко");
        Accountant accountant = new Accountant("Бухгалтерія");

        System.out.println("=== Сценарій 1: Ресторан, SPA і прокат нараховують послуги ===");
        BillOperation dinner = new RestaurantCharge(bill, "Вечеря в ресторані", 850, "10:15");
        BillOperation massage = new SpaCharge(bill, "Масаж", 1200, "10:20");
        BillOperation skis = new SkiRentalCharge(bill, "Прокат лиж", 600, "10:25");

        executeAndLog(accountant, dinner);
        executeAndLog(accountant, massage);
        executeAndLog(accountant, skis);
        bill.printBill();
        System.out.println();

        System.out.println("=== Сценарій 2: Сторнування помилкового нарахування SPA ===");
        undoAndLog(accountant, massage, "10:40");
        bill.printBill();
        System.out.println();

        System.out.println("=== Сценарій 3: Повна історія операцій за рахунком ===");
        bill.printHistory();
    }
}
