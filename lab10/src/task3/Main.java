package lab10.src.task3;

import lab10.src.task1.Log;
import lab10.src.task3.Task3Confirmation.BookingChannel;
import lab10.src.task3.Task3Confirmation.OnlineServiceChannel;
import lab10.src.task3.Task3Confirmation.ReceptionChannel;
import lab10.src.task3.Task3Confirmation.TourOperatorChannel;
import lab10.src.task3.Task3Confirmation.WebsiteChannel;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        header("Завдання 3. Підтвердження бронювання (Template Method)");

        Log.event("Раннер", "=== Завдання 3: підтвердження бронювання різними каналами ===");

        BookingChannel[] channels = {
                new ReceptionChannel(),
                new WebsiteChannel(),
                new TourOperatorChannel(),
                new OnlineServiceChannel()
        };
        String[] bookingIds = {"R-101", "W-205", "T-310", "O-415"};

        for (int i = 0; i < channels.length; i++) {
            channels[i].confirm(bookingIds[i]);
        }
    }

    private static void header(String title) {
        System.out.println();
        System.out.println("################ " + title + " ################");
    }
}
