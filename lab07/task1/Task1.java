package lab07.task1;

/**
 * Клас даних про працівника.
 * Відповідає лише за зберігання інформації про співробітника:
 * ім'я, кількість відпрацьованих годин та погодинну ставку.
 * Тут не відбувається валідація, розрахунки чи форматування звітів.
 */
class EmployeePayrollData {

    private String name;
    private double hoursWorked;
    private double hourlyRate;

    public EmployeePayrollData(String name,
                               double hoursWorked,
                               double hourlyRate) {
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public String getName() {
        return name;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}

/**
 * Сервіс валідації вхідних даних.
 * Відповідає тільки за перевірку, чи передані коректні дані працівника.
 * Наприклад: ім'я не порожнє.
 */
class PayrollValidator {

    public void validate(EmployeePayrollData emp) {

        if (emp.getName() == null ||
                emp.getName().isEmpty()) {

            throw new IllegalArgumentException(
                    "Invalid employee name");
        }
    }
}

/**
 * Клас обчислення зарплати.
 * Відповідає тільки за бізнес-розрахунок чистого доходу на основі
 * відпрацьованих годин і погодинної ставки.
 */
class SalaryCalculator {

    public double calculateNetSalary(
            EmployeePayrollData emp) {

        double grossSalary =
                emp.getHoursWorked()
                        * emp.getHourlyRate();

        double tax = grossSalary * 0.18;

        return grossSalary - tax;
    }
}

/**
 * Генератор розрахункового листка.
 * Відповідає лише за формування текстового представлення виплати.
 */
class PayslipGenerator {

    public String generate(
            EmployeePayrollData emp,
            double netSalary) {

        return "Employee: " + emp.getName()
                + "\nNet salary: " + netSalary;
    }
}

/**
 * Експортер до банківського реєстру.
 * Відповідає лише за передачу/експорт даних до банківської системи.
 */
class BankRegistryExporter {

    public void export(
            EmployeePayrollData emp,
            double salary) {

        System.out.println(
                "Exporting data to bank registry...");
    }
}

/**
 * Журнал аудиту.
 * Відповідає лише за фіксацію події аудиту про виплату працівника.
 */
class AuditLogger {

    public void log(
            EmployeePayrollData emp,
            double salary) {

        System.out.println(
                "Audit record created.");
    }
}

/**
 * Оркестратор процесу нарахування зарплати.
 * Координує роботу інших сервісів:
 * 1) перевірка даних,
 * 2) розрахунок заробітку,
 * 3) формування payslip,
 * 4) експорт у реєстр,
 * 5) логування аудиту.
 * Цей клас керує потоком, але не містить бізнес-логіки самостійно.
 */
class PayrollProcessor {

    private PayrollValidator validator =
            new PayrollValidator();

    private SalaryCalculator calculator =
            new SalaryCalculator();

    private PayslipGenerator generator =
            new PayslipGenerator();

    private BankRegistryExporter exporter =
            new BankRegistryExporter();

    private AuditLogger logger =
            new AuditLogger();

    public void process(EmployeePayrollData emp) {

        validator.validate(emp);

        double salary =
                calculator.calculateNetSalary(emp);

        String payslip =
                generator.generate(emp, salary);

        exporter.export(emp, salary);

        logger.log(emp, salary);

        System.out.println("\n--- PAYSLIP ---");
        System.out.println(payslip);
    }
}