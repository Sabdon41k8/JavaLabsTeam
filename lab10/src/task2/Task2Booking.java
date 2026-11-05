package lab10.src.task2;

import java.time.LocalDate;

public final class Task2Booking {
    private Task2Booking() {}

    public static final class Booking {
        private final String guest;
        private final LocalDate checkIn;
        private final LocalDate checkOut;
        private final String roomCategory;
        private final boolean breakfasts;
        private final boolean transfer;
        private final boolean lateCheckout;
        private final boolean childBed;
        private final String comment;

        private Booking(Builder builder) {
            guest = builder.guest;
            checkIn = builder.checkIn;
            checkOut = builder.checkOut;
            roomCategory = builder.roomCategory;
            breakfasts = builder.breakfasts;
            transfer = builder.transfer;
            lateCheckout = builder.lateCheckout;
            childBed = builder.childBed;
            comment = builder.comment;
        }

        public String summary() {
            return "Гість: " + guest + "; категорія: " + roomCategory
                    + "; проживання: " + checkIn + " — " + checkOut
                    + "; ночей: " + (checkOut.toEpochDay() - checkIn.toEpochDay())
                    + "; сніданки: " + yesNo(breakfasts)
                    + "; трансфер: " + yesNo(transfer)
                    + "; пізній виїзд: " + yesNo(lateCheckout)
                    + "; дитяче ліжко: " + yesNo(childBed)
                    + "; коментар: " + (comment.isBlank() ? "немає" : comment);
        }

        private static String yesNo(boolean value) {
            return value ? "так" : "ні";
        }
    }

    public static final class Builder {
        private String guest;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private String roomCategory;
        private boolean breakfasts;
        private boolean transfer;
        private boolean lateCheckout;
        private boolean childBed;
        private String comment = "";

        public Builder guest(String guest) {
            this.guest = guest;
            return this;
        }

        public Builder dates(LocalDate checkIn, LocalDate checkOut) {
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            return this;
        }

        public Builder roomCategory(String roomCategory) {
            this.roomCategory = roomCategory;
            return this;
        }

        public Builder breakfasts() {
            breakfasts = true;
            return this;
        }

        public Builder transfer() {
            transfer = true;
            return this;
        }

        public Builder lateCheckout() {
            lateCheckout = true;
            return this;
        }

        public Builder childBed() {
            childBed = true;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment == null ? "" : comment;
            return this;
        }

        public Booking build() {
            if (guest == null || guest.isBlank()) {
                throw new IllegalStateException("не вказано гостя");
            }
            if (roomCategory == null || roomCategory.isBlank()) {
                throw new IllegalStateException("не вказано категорію номера");
            }
            if (checkIn == null || checkOut == null || !checkOut.isAfter(checkIn)) {
                throw new IllegalStateException("некоректні дати проживання");
            }
            return new Booking(this);
        }
    }

    public interface BookingRecipe {
        Booking create(String guest, LocalDate checkIn);
    }

    public static final class WeekendRecipe implements BookingRecipe {
        @Override
        public Booking create(String guest, LocalDate checkIn) {
            return new Builder()
                    .guest(guest)
                    .dates(checkIn, checkIn.plusDays(2))
                    .roomCategory("стандарт")
                    .breakfasts()
                    .build();
        }
    }

    public static final class SkiWeekRecipe implements BookingRecipe {
        @Override
        public Booking create(String guest, LocalDate checkIn) {
            return new Builder()
                    .guest(guest)
                    .dates(checkIn, checkIn.plusDays(7))
                    .roomCategory("люкс")
                    .breakfasts()
                    .transfer()
                    .build();
        }
    }
}
