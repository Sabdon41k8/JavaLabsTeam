package lab11.src.task4;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Task4Billing {

    enum Role {
        WAITER("Офіціант"),
        RECEPTIONIST("Рецепція"),
        ACCOUNTANT("Бухгалтерія"),
        SPA_STAFF("SPA"),
        RENTAL_STAFF("Прокат");

        private final String displayName;

        Role(String displayName) {
            this.displayName = displayName;
        }

        String getDisplayName() {
            return displayName;
        }
    }

    static class Employee {
        private final String name;
        private final Role role;

        Employee(String name, Role role) {
            this.name = name;
            this.role = role;
        }

        String getName() {
            return name;
        }

        Role getRole() {
            return role;
        }
    }

    static class AuditLog {
        private final List<String> entries = new ArrayList<>();

        void record(String employeeName, String operation, String result) {
            String entry = String.format("[Аудит] Працівник: %s | Операція: %s | Результат: %s",
                    employeeName, operation, result);
            entries.add(entry);
            System.out.println(entry);
        }

        List<String> getEntries() {
            return entries;
        }
    }

    interface BillingService {
        void charge(int roomNumber, String description, double amount);

        String getFullBill(int roomNumber);

        void refund(int roomNumber, double amount, String reason);
    }

    static class RealBillingService implements BillingService {
        private final Map<Integer, List<String>> bills = new LinkedHashMap<>();
        private final Map<Integer, Double> totals = new LinkedHashMap<>();

        @Override
        public void charge(int roomNumber, String description, double amount) {
            bills.computeIfAbsent(roomNumber, r -> new ArrayList<>());
            bills.get(roomNumber).add(String.format("Нарахування: %s (+%.2f грн)", description, amount));
            totals.put(roomNumber, totals.getOrDefault(roomNumber, 0.0) + amount);
            System.out.printf("Нараховано %s для номера %d: %.2f грн%n", description, roomNumber, amount);
        }

        @Override
        public String getFullBill(int roomNumber) {
            List<String> lines = bills.get(roomNumber);
            double total = totals.getOrDefault(roomNumber, 0.0);
            StringBuilder sb = new StringBuilder();
            sb.append("Повний рахунок номера ").append(roomNumber).append(":\n");
            if (lines == null || lines.isEmpty()) {
                sb.append("  (операцій немає)\n");
            } else {
                for (String line : lines) {
                    sb.append("  - ").append(line).append("\n");
                }
            }
            sb.append(String.format("  Разом до сплати: %.2f грн", total));
            return sb.toString();
        }

        @Override
        public void refund(int roomNumber, double amount, String reason) {
            bills.computeIfAbsent(roomNumber, r -> new ArrayList<>());
            bills.get(roomNumber).add(String.format("Повернення: %s (-%.2f грн)", reason, amount));
            totals.put(roomNumber, totals.getOrDefault(roomNumber, 0.0) - amount);
            System.out.printf("Оформлено повернення для номера %d: %.2f грн (%s)%n", roomNumber, amount, reason);
        }
    }

    static class BillingServiceProxy implements BillingService {
        private final BillingService realService;
        private final Employee employee;
        private final AuditLog auditLog;

        BillingServiceProxy(BillingService realService, Employee employee, AuditLog auditLog) {
            this.realService = realService;
            this.employee = employee;
            this.auditLog = auditLog;
        }

        private boolean canViewBill() {
            Role role = employee.getRole();
            return role == Role.RECEPTIONIST || role == Role.ACCOUNTANT;
        }

        private boolean canRefund() {
            Role role = employee.getRole();
            return role == Role.RECEPTIONIST || role == Role.ACCOUNTANT;
        }

        @Override
        public void charge(int roomNumber, String description, double amount) {
            String operation = String.format("Нарахування (%s, номер %d, %.2f грн)", description, roomNumber, amount);
            try {
                realService.charge(roomNumber, description, amount);
                auditLog.record(employee.getName(), operation, "Успішно");
            } catch (RuntimeException e) {
                auditLog.record(employee.getName(), operation, "Помилка: " + e.getMessage());
                throw e;
            }
        }

        @Override
        public String getFullBill(int roomNumber) {
            String operation = String.format("Перегляд повного рахунку (номер %d)", roomNumber);
            if (!canViewBill()) {
                String message = "Недостатньо прав для операції перегляду рахунку";
                System.out.println(message);
                auditLog.record(employee.getName(), operation, "Відмова: " + message);
                return null;
            }
            try {
                String bill = realService.getFullBill(roomNumber);
                auditLog.record(employee.getName(), operation, "Успішно");
                return bill;
            } catch (RuntimeException e) {
                auditLog.record(employee.getName(), operation, "Помилка: " + e.getMessage());
                throw e;
            }
        }

        @Override
        public void refund(int roomNumber, double amount, String reason) {
            String operation = String.format("Повернення коштів (номер %d, %.2f грн, %s)", roomNumber, amount, reason);
            if (!canRefund()) {
                String message = "Недостатньо прав для операції повернення";
                System.out.println(message);
                auditLog.record(employee.getName(), operation, "Відмова: " + message);
                return;
            }
            try {
                realService.refund(roomNumber, amount, reason);
                auditLog.record(employee.getName(), operation, "Успішно");
            } catch (RuntimeException e) {
                auditLog.record(employee.getName(), operation, "Помилка: " + e.getMessage());
                throw e;
            }
        }
    }
}
