package lab09.src.common;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class Log {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private Log() {
    }

    public static synchronized void event(String source, String message) {
        System.out.printf("[%s] [%s] %s%n",
                LocalTime.now().format(FORMAT), source, message);
    }

    public static void simulateWork(String source, String operation, long milliseconds) {
        event(source, operation + " — початок (імітація витратної операції)");
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            event(source, operation + " — перервано");
            return;
        }
        event(source, operation + " — завершено");
    }
}
