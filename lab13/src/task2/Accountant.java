package lab13.src.task2;

public class Accountant {
    private final String name;

    public Accountant(String name) {
        this.name = name;
    }

    public String execute(BillOperation operation) {
        return "[" + operation.getTime() + "] [" + name + "] Отримано нарахування \"" + operation.getDescription()
                + "\" -> " + operation.execute();
    }

    public String undo(BillOperation operation, String time) {
        return "[" + time + "] [" + name + "] Отримано запит на сторнування \"" + operation.getDescription()
                + "\" -> " + operation.undo(time);
    }
}
