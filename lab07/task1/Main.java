package lab07.task1;

public class Main {

    public static void main(String[] args) {

        EmployeePayrollData employee =
                new EmployeePayrollData(
                        "Marat",
                        160,
                        150
                );

        PayrollProcessor processor =
                new PayrollProcessor();

        processor.process(employee);
    }
}