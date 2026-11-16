package lab12.src.task3;

/**
 * Завдання 3. Керування обладнанням номера — шаблон Bridge.
 *
 * <p>Шаблон Bridge розділяє дві незалежні ієрархії:</p>
 * <ul>
 *   <li>Abstraction — {@link ControlDevice} та його нащадки: настінна панель,
 *       пульт і мобільний застосунок;</li>
 *   <li>Implementor — {@link Equipment} та конкретне обладнання:
 *       кондиціонер, освітлення, штори й тепла підлога.</li>
 * </ul>
 *
 * <p>Пристрій керування не створює обладнання сам, а отримує його через
 * конструктор. Тому будь-який пристрій можна поєднати з будь-яким обладнанням
 * під час виконання програми, а новий тип пристрою або обладнання додається
 * одним класом.</p>
 *
 * <p>Усі класи завдання зібрані в цьому файлі як вкладені статичні класи,
 * щоб структура рішення була компактною. Демонстраційний сценарій винесено
 * в окремий {@link Main}.</p>
 */
public class Task3RoomControl {

    /**
     * Допоміжний журнал подій.
     *
     * <p>Час умовний: кожна наступна подія зсувається на 5 секунд, починаючи
     * з 18:00:00. Формат рядка: {@code [час] [джерело] подія}.</p>
     */
    public static final class Log {
        private static int seconds = 18 * 3600;

        private Log() {
        }

        public static void event(String source, String message) {
            seconds += 5;
            System.out.printf("[%02d:%02d:%02d] [%s] %s%n",
                    seconds / 3600, (seconds / 60) % 60, seconds % 60, source, message);
        }
    }

    /**
     * Implementor — спільний інтерфейс обладнання.
     *
     * <p>Визначає, ЩО саме може виконувати команду. Конкретні реалізації
     * не знають, з якого пристрою керування надійшла команда.</p>
     */
    public interface Equipment {
        String getName();

        void turnOn();

        void turnOff();
    }

    /**
     * ConcreteImplementor — кондиціонер.
     */
    public static class AirConditioner implements Equipment {
        private final String name;

        public AirConditioner(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void turnOn() {
            Log.event("AirConditioner", "«" + name + "»: увімкнено, режим охолодження 22 °C");
        }

        @Override
        public void turnOff() {
            Log.event("AirConditioner", "«" + name + "»: вимкнено");
        }
    }

    /**
     * ConcreteImplementor — освітлення номера.
     */
    public static class Lighting implements Equipment {
        private final String name;

        public Lighting(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void turnOn() {
            Log.event("Lighting", "«" + name + "»: увімкнено");
        }

        @Override
        public void turnOff() {
            Log.event("Lighting", "«" + name + "»: вимкнено");
        }
    }

    /**
     * ConcreteImplementor — штори з електроприводом.
     *
     * <p>Для штор {@code turnOn()} означає відкрити їх, а {@code turnOff()} —
     * закрити.</p>
     */
    public static class Curtains implements Equipment {
        private final String name;

        public Curtains(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void turnOn() {
            Log.event("Curtains", "«" + name + "»: відкрито");
        }

        @Override
        public void turnOff() {
            Log.event("Curtains", "«" + name + "»: закрито");
        }
    }

    /**
     * ConcreteImplementor — тепла підлога.
     *
     * <p>Це нове обладнання, додане одним класом. Пристрої керування
     * не змінювалися, бо працюють через інтерфейс {@link Equipment}.</p>
     */
    public static class HeatedFloor implements Equipment {
        private final String name;

        public HeatedFloor(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void turnOn() {
            Log.event("HeatedFloor", "«" + name + "»: увімкнено, підігрів 27 °C");
        }

        @Override
        public void turnOff() {
            Log.event("HeatedFloor", "«" + name + "»: вимкнено");
        }
    }

    /**
     * Abstraction — абстрактний пристрій керування.
     *
     * <p>Містить посилання на Implementor через інтерфейс {@link Equipment}.
     * Саме це посилання є «мостом» між двома ієрархіями: пристрій керування
     * відповідає за те, ЯК подається команда, а обладнання — за те, ЩО вона
     * виконує.</p>
     */
    public abstract static class ControlDevice {
        /** Обладнання, яким керує цей пристрій. */
        protected final Equipment equipment;

        protected ControlDevice(Equipment equipment) {
            this.equipment = equipment;
        }

        /** Назва пристрою для журналу подій. */
        public abstract String getDeviceName();

        /** Подає обладнанню команду ввімкнення. */
        public abstract void turnOn();

        /** Подає обладнанню команду вимкнення. */
        public abstract void turnOff();

        /** Записує в журнал команду, подану конкретним пристроєм. */
        protected void logCommand(String message) {
            Log.event(getDeviceName(), message);
        }
    }

    /**
     * RefinedAbstraction — настінна панель.
     */
    public static class WallPanel extends ControlDevice {
        public WallPanel(Equipment equipment) {
            super(equipment);
        }

        @Override
        public String getDeviceName() {
            return "WallPanel";
        }

        @Override
        public void turnOn() {
            logCommand("Натиснуто кнопку живлення на настінній панелі для «"
                    + equipment.getName() + "»");
            equipment.turnOn();
        }

        @Override
        public void turnOff() {
            logCommand("Натиснуто кнопку вимкнення на настінній панелі для «"
                    + equipment.getName() + "»");
            equipment.turnOff();
        }
    }

    /**
     * RefinedAbstraction — пульт дистанційного керування.
     */
    public static class RemoteControl extends ControlDevice {
        public RemoteControl(Equipment equipment) {
            super(equipment);
        }

        @Override
        public String getDeviceName() {
            return "RemoteControl";
        }

        @Override
        public void turnOn() {
            logCommand("Натиснуто кнопку на пульті для «" + equipment.getName() + "»");
            equipment.turnOn();
        }

        @Override
        public void turnOff() {
            logCommand("Натиснуто кнопку вимкнення на пульті для «"
                    + equipment.getName() + "»");
            equipment.turnOff();
        }
    }

    /**
     * RefinedAbstraction — мобільний застосунок гостя.
     */
    public static class MobileApp extends ControlDevice {
        public MobileApp(Equipment equipment) {
            super(equipment);
        }

        @Override
        public String getDeviceName() {
            return "MobileApp";
        }

        @Override
        public void turnOn() {
            logCommand("Надіслано команду із застосунку гостя для «"
                    + equipment.getName() + "»");
            equipment.turnOn();
        }

        @Override
        public void turnOff() {
            logCommand("Надіслано команду вимкнення із застосунку гостя для «"
                    + equipment.getName() + "»");
            equipment.turnOff();
        }
    }
}
