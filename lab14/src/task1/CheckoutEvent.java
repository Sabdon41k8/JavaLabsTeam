package lab14.src.task1;

public class CheckoutEvent {
    private final String guestName;
    private final int roomNumber;
    private final String review; // null or empty if no review
    private final boolean night;

    public CheckoutEvent(String guestName, int roomNumber, String review, boolean night) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.review = review;
        this.night = night;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getReview() {
        return review;
    }

    public boolean hasReview() {
        return review != null && !review.isBlank();
    }

    public boolean isNight() {
        return night;
    }
}
