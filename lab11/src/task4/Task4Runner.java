package lab11.src.task4;

public class Task4Runner {
    public static void main(String[] args) {
        Task4Billing.AuditLog auditLog = new Task4Billing.AuditLog();
        Task4Billing.BillingService realService = new Task4Billing.RealBillingService();

        Task4Billing.Employee waiter = new Task4Billing.Employee("Олег Петренко", Task4Billing.Role.WAITER);
        Task4Billing.Employee accountant = new Task4Billing.Employee("Марія Іваненко", Task4Billing.Role.ACCOUNTANT);

        Task4Billing.BillingService waiterService =
                new Task4Billing.BillingServiceProxy(realService, waiter, auditLog);
        Task4Billing.BillingService accountantService =
                new Task4Billing.BillingServiceProxy(realService, accountant, auditLog);

        System.out.println("=== Ситуація 1: офіціант нараховує вечерю гостю з номера 305 ===");
        waiterService.charge(305, "Вечеря", 650.0);

        System.out.println("\n=== Ситуація 2: офіціант намагається оформити повернення коштів ===");
        waiterService.refund(305, 100.0, "Помилкове нарахування");

        System.out.println("\n=== Ситуація 3: бухгалтер переглядає повний рахунок і оформлює повернення ===");
        String bill = accountantService.getFullBill(305);
        if (bill != null) {
            System.out.println(bill);
        }
        accountantService.refund(305, 100.0, "Скасування частини замовлення");

        System.out.println("\n=== Журнал аудиту ===");
        for (String entry : auditLog.getEntries()) {
            System.out.println(entry);
        }
    }
}
