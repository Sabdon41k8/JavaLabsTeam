package lab13.src.task2;

public class SkiRentalCharge extends BillOperation {
    public SkiRentalCharge(GuestBill bill, String description, int amount, String time) {
        super(bill, description, amount, time);
    }

    @Override
    protected Department getDepartment() {
        return Department.SKI_RENTAL;
    }
}
