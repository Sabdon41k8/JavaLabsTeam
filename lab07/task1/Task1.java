package lab07.task1;

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

class PayrollValidator {

    public void validate(EmployeePayrollData emp) {

        if (emp.getName() == null ||
                emp.getName().isEmpty()) {

            throw new IllegalArgumentException(
                    "Invalid employee name");
        }
    }
}

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

class PayslipGenerator {

    public String generate(
            EmployeePayrollData emp,
            double netSalary) {

        return "Employee: " + emp.getName()
                + "\nNet salary: " + netSalary;
    }
}

class BankRegistryExporter {

    public void export(
            EmployeePayrollData emp,
            double salary) {

        System.out.println(
                "Exporting data to bank registry...");
    }
}

class AuditLogger {

    public void log(
            EmployeePayrollData emp,
            double salary) {

        System.out.println(
                "Audit record created.");
    }
}

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