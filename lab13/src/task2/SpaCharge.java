package lab13.src.task2;

public class SpaCharge extends BillOperation {
    public SpaCharge(GuestBill bill, String description, int amount, String time) {
        super(bill, description, amount, time);
    }

    @Override
    protected Department getDepartment() {
        return Department.SPA;
    }
}
