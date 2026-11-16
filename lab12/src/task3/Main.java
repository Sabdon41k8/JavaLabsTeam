package lab12.src.task3;

import lab12.src.task3.Task3RoomControl.AirConditioner;
import lab12.src.task3.Task3RoomControl.ControlDevice;
import lab12.src.task3.Task3RoomControl.Curtains;
import lab12.src.task3.Task3RoomControl.Equipment;
import lab12.src.task3.Task3RoomControl.HeatedFloor;
import lab12.src.task3.Task3RoomControl.Lighting;
import lab12.src.task3.Task3RoomControl.MobileApp;
import lab12.src.task3.Task3RoomControl.RemoteControl;
import lab12.src.task3.Task3RoomControl.WallPanel;

public class Main {
    public static void main(String[] args) {
        // Один і той самий об'єкт обладнання можна передати різним пристроям
        // керування: саме це демонструє міст між двома ієрархіями.
        Equipment airConditioner = new AirConditioner("Кондиціонер у вітальні");
        Equipment curtains = new Curtains("Штори у вітальні");

        // Кондиціонер керується з настінної панелі.
        System.out.println("=== Кондиціонер: настінна панель ===");
        ControlDevice wallPanel = new WallPanel(airConditioner);
        wallPanel.turnOn();

        // Той самий кондиціонер керується із мобільного застосунку.
        System.out.println("\n=== Кондиціонер: мобільний застосунок ===");
        ControlDevice mobileApp = new MobileApp(airConditioner);
        mobileApp.turnOn();

        // Штори закриваються з пульта.
        System.out.println("\n=== Штори: пульт ===");
        ControlDevice remote = new RemoteControl(curtains);
        remote.turnOff();

        // Освітлення вмикається із застосунку.
        System.out.println("\n=== Освітлення: мобільний застосунок ===");
        ControlDevice lightingApp = new MobileApp(new Lighting("Освітлення у вітальні"));
        lightingApp.turnOn();

        // Нове обладнання додано одним класом HeatedFloor; пристрої
        // керування при цьому не змінювалися.
        System.out.println("\n=== Тепла підлога: мобільний застосунок (нове обладнання) ===");
        ControlDevice heatedFloorApp = new MobileApp(new HeatedFloor("Тепла підлога у ванній"));
        heatedFloorApp.turnOn();
    }
}
