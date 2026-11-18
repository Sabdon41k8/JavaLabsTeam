package lab13.src.task2;

public abstract class BillOperation {
    private final GuestBill bill;
    private final String description;
    private final int amount;
    private final String time;
    private boolean executed;

    protected BillOperation(GuestBill bill, String description, int amount, String time) {
        this.bill = bill;
        this.description = description;
        this.amount = amount;
        this.time = time;
    }

    public final String execute() {
        if (executed) {
            return "[" + time + "] [" + getDepartment().getDisplayName() + "] Нарахування вже виконано: " + description;
        }
        executed = true;
        return bill.apply(getDepartment(), description, amount, time, false);
    }

    public final String undo(String reversalTime) {
        if (!executed) {
            return "[" + reversalTime + "] [" + getDepartment().getDisplayName()
                    + "] Неможливо сторнувати ще не виконане нарахування: " + description;
        }
        executed = false;
        return bill.apply(getDepartment(), description, amount, reversalTime, true);
    }

    public GuestBill getBill() {
        return bill;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    protected abstract Department getDepartment();

    @Override
    public String toString() {
        return "Операція{" + getDepartment().getDisplayName() + ", \"" + description + "\", " + amount + " грн, " + time + "}";
    }
}
