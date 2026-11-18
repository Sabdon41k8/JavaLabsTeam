package lab13.src.task2;

import java.util.ArrayList;
import java.util.List;

public class GuestBill {
    private final String guestName;
    private final List<String> history = new ArrayList<>();
    private int total;

    public GuestBill(String guestName) {
        this.guestName = guestName;
    }

    public String apply(Department department, String description, int amount, String time, boolean reverse) {
        String action = reverse ? "Сторнування" : "Нарахування";
        total += reverse ? -amount : amount;

        String record = "[" + time + "] [Рахунок " + guestName + "] " + action + " " + department.getDisplayName()
                + ": " + description + " (" + (reverse ? "-" : "+") + amount + " грн); рахунок = " + total + " грн";
        history.add(record);
        return record;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getTotal() {
        return total;
    }

    public void printBill() {
        System.out.println("Рахунок гостя " + guestName + ": " + total + " грн");
    }

    public void printHistory() {
        System.out.println("Історія операцій за рахунком гостя " + guestName + ":");
        for (String record : history) {
            System.out.println("  " + record);
        }
    }

    @Override
    public String toString() {
        return "Рахунок{" + guestName + ", " + total + " грн}";
    }
}
