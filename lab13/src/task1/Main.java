package lab13.src.task1;

import java.util.Arrays;
import java.util.List;

public class Main {

    // Налаштування ланцюга для денної зміни
    public static RequestHandler buildDayShiftChain() {
        RequestHandler reception = new ReceptionHandler();
        RequestHandler housekeeping = new HousekeepingHandler();
        RequestHandler technical = new TechnicalHandler();
        RequestHandler manager = new ManagerHandler();

        reception.setNext(housekeeping).setNext(technical).setNext(manager);
        return reception;
    }

    // Налаштування ланцюга для нічної зміни (покоївок немає, прибирання приймає рецепція "на ранок")
    public static RequestHandler buildNightShiftChain() {
        RequestHandler reception = new ReceptionHandler(true);
        RequestHandler technical = new TechnicalHandler();
        RequestHandler manager = new ManagerHandler();

        reception.setNext(technical).setNext(manager);
        return reception;
    }

    private static void processAndLog(RequestHandler chain, GuestRequest request) {
        System.out.println("-> " + request);
        String result = chain.handle(request);
        System.out.println("   Результат: " + result);
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== Сценарій 1: Денна зміна, 4 запити різних типів ===");
        RequestHandler dayChain = buildDayShiftChain();

        List<GuestRequest> dayRequests = Arrays.asList(
                new GuestRequest(RequestType.TOWELS, "Потрібні додаткові рушники"),
                new GuestRequest(RequestType.CLEANING, "Прибрати номер 305"),
                new GuestRequest(RequestType.MALFUNCTION, "Не працює кондиціонер у номері 210"),
                new GuestRequest(RequestType.COMPLAINT, "Скарга на шум у сусідньому номері")
        );

        for (GuestRequest request : dayRequests) {
            processAndLog(dayChain, request);
        }

        System.out.println("=== Сценарій 2: Нічна зміна, запит на прибирання ===");
        RequestHandler nightChain = buildNightShiftChain();
        GuestRequest nightCleaning = new GuestRequest(RequestType.CLEANING, "Прибрати номер 305 (нічний запит)");
        processAndLog(nightChain, nightCleaning);

        System.out.println("=== Сценарій 3: Запит, який не може обробити жоден рівень ===");
        GuestRequest unhandled = new GuestRequest(RequestType.OTHER, "Замовити квитки на концерт");
        processAndLog(dayChain, unhandled);
    }
}
