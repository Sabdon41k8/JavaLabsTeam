package lab10.src.task2;

import java.time.LocalDate;

import lab10.src.task1.Log;
import lab10.src.task2.Task2Booking.Booking;
import lab10.src.task2.Task2Booking.Builder;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        header("Завдання 2. Оформлення бронювання (Builder)");

        try {
            Booking booking = new Builder()
                    .guest("Олена Коваль")
                    .dates(LocalDate.of(2026, 12, 20), LocalDate.of(2026, 12, 25))
                    .roomCategory("люкс")
                    .breakfasts()
                    .transfer()
                    .childBed()
                    .build();
            Log.event("Служба бронювання", "Створено бронювання: " + booking.summary());
        } catch (IllegalStateException error) {
            Log.event("Служба бронювання", "Неможливо сформувати бронювання: " + error.getMessage());
        }

        Booking weekend = new Task2Booking.WeekendRecipe()
                .create("Андрій Мельник", LocalDate.of(2026, 12, 26));
        Log.event("Служба бронювання", "Типове бронювання «Вікенд»: " + weekend.summary());

        Booking skiWeek = new Task2Booking.SkiWeekRecipe()
                .create("Марія Бойко", LocalDate.of(2027, 1, 10));
        Log.event("Служба бронювання", "Типове бронювання «Лижний тиждень»: " + skiWeek.summary());

        try {
            new Builder()
                    .guest("Іван Петренко")
                    .dates(LocalDate.of(2026, 12, 28), LocalDate.of(2026, 12, 27))
                    .roomCategory("стандарт")
                    .build();
        } catch (IllegalStateException error) {
            Log.event("Служба бронювання", "Неможливо сформувати бронювання: " + error.getMessage());
        }
    }

    private static void header(String title) {
        System.out.println();
        System.out.println("################ " + title + " ################");
    }
}
