package lab10.src.task1;

public final class Log {
    private static int minutes = 9 * 60; // умовний початок дня 09:00
 
    private Log() {}
 
    public static void event(String source, String text) {
        System.out.printf("[%02d:%02d] [%s] %s%n", minutes / 60, minutes % 60, source, text);
        minutes++;
    }

}
