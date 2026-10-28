package lab09.src.task3;

import java.math.BigDecimal;
import java.util.Arrays;

public final class Task3Demo {
    private Task3Demo() {
    }

    public static void main(String[] args) {
        runDemo();
    }

    public static void runDemo() {
        StayPackage template = new StayPackage(
                "Лижний тиждень",
                Arrays.asList("сніданок", "трансфер до курорту"),
                Arrays.asList("сауна"),
                7,
                new BigDecimal("10"));

        StayPackage guestPackage1 = template.copyForGuest("Олена");
        StayPackage guestPackage2 = template.copyForGuest("Максим");
        StayPackage guestPackage3 = template.copyForGuest("Ірина");

        guestPackage1.addSpaProcedure("масаж");
        guestPackage1.setSkiPassDays(5);
        guestPackage1.setDiscountPercent(new BigDecimal("15"));

        verifyIndependentCopies(template, guestPackage1, guestPackage2, guestPackage3);

        System.out.println("Типовий пакет та пакети гостей після персоналізації:");
        System.out.println(template);
        System.out.println(guestPackage1);
        System.out.println(guestPackage2);
        System.out.println(guestPackage3);
        System.out.println("Перевірка: зміни пакета Олени не вплинули на шаблон та інших гостей.");
    }

    private static void verifyIndependentCopies(
            StayPackage template,
            StayPackage customizedPackage,
            StayPackage unchangedPackage1,
            StayPackage unchangedPackage2) {
        if (template.getSpaProcedures().contains("масаж")
                || template.getSkiPassDays() != 7
                || template.getDiscountPercent().compareTo(new BigDecimal("10")) != 0
                || unchangedPackage1.getSpaProcedures().contains("масаж")
                || unchangedPackage1.getSkiPassDays() != 7
                || unchangedPackage1.getDiscountPercent().compareTo(new BigDecimal("10")) != 0
                || unchangedPackage2.getSpaProcedures().contains("масаж")
                || unchangedPackage2.getSkiPassDays() != 7
                || unchangedPackage2.getDiscountPercent().compareTo(new BigDecimal("10")) != 0
                || !customizedPackage.getSpaProcedures().contains("масаж")
                || customizedPackage.getSkiPassDays() != 5
                || customizedPackage.getDiscountPercent().compareTo(new BigDecimal("15")) != 0) {
            throw new IllegalStateException("Guest package changes leaked into another package");
        }
    }
}
