package lab13.src.task2;

public class RestaurantCharge extends BillOperation {
    public RestaurantCharge(GuestBill bill, String description, int amount, String time) {
        super(bill, description, amount, time);
    }

    @Override
    protected Department getDepartment() {
        return Department.RESTAURANT;
    }
}
