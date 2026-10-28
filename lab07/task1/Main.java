package lab07.task1;

/**
 * Точка входу для демонстрації завдання 1.
 * Створює тестового працівника і запускає процес нарахування зарплати.
 */
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