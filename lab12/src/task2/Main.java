package lab12.src.task2;

import lab12.src.task2.Task2StayPackages.HotelService;
import lab12.src.task2.Task2StayPackages.Log;
import lab12.src.task2.Task2StayPackages.StayComponent;
import lab12.src.task2.Task2StayPackages.StayPackage;

public class Main {
    public static void main(String[] args) {
        StayPackage familyWeek = new StayPackage("Сімейний тиждень");

        familyWeek.add(new HotelService("Проживання в сімейному номері", 9800.0));
        familyWeek.add(new HotelService("Харчування за системою «напівпансіон»", 4200.0));

        StayPackage kidsClub = new StayPackage("Дитячий клуб");
        kidsClub.add(new HotelService("Заняття з аніматором", 1200.0));
        kidsClub.add(new HotelService("Творча майстерня", 800.0));
        kidsClub.add(new HotelService("Екскурсія в гори", 1500.0));

        familyWeek.add(kidsClub);

        StayPackage spaWeekend = new StayPackage("SPA-вікенд");
        spaWeekend.add(new HotelService("Масаж", 1800.0));
        spaWeekend.add(new HotelService("Відвідування басейну", 700.0));

        System.out.println();
        Log.event("Main", "Структура пакета «" + familyWeek.getName() + "»:");
        printStructure(familyWeek, "");

        System.out.println();
        Log.event("Main", "Вартість обчислено одним викликом getPrice() для кожного вузла:");
        printPrice(familyWeek);
        printPrice(kidsClub);
        printPrice(spaWeekend);

        System.out.println();
        Log.event("Main", "Перелік послуг пакета «" + familyWeek.getName() + "»:");
        for (String service : familyWeek.getServices()) {
            System.out.println("  - " + service);
        }
    }

    private static void printStructure(StayComponent component, String indent) {
        System.out.printf("%s%s — %.2f грн%n", indent, component.getName(), component.getPrice());
        for (StayComponent child : component.getChildren()) {
            printStructure(child, indent + "  ");
        }
    }

    private static void printPrice(StayComponent component) {
        Log.event("Main", "Вартість «" + component.getName() + "»: "
                + String.format("%.2f", component.getPrice()) + " грн");
    }
}
