package lab12.src.task2;

import java.util.ArrayList;
import java.util.List;

/**
 * Завдання 2. Склад пакетів проживання — шаблон Composite.
 *
 * <p>Шаблон Composite дозволяє однаково працювати з окремою послугою та
 * цілим пакетом послуг через спільний інтерфейс. Учасники шаблону:</p>
 * <ul>
 *   <li>Component — {@link StayComponent};</li>
 *   <li>Leaf — {@link HotelService}, окрема послуга без дочірніх елементів;</li>
 *   <li>Composite — {@link StayPackage}, пакет, що містить інші послуги
 *       або вкладені пакети.</li>
 * </ul>
 *
 * <p>Вартість і перелік послуг обчислюються рекурсивно, тому пакет можна
 * вкладати в пакет на будь-яку глибину без змін у коді розрахунку.</p>
 *
 * <p>Усі класи завдання зібрані в цьому файлі як вкладені статичні класи,
 * щоб структура рішення була компактною. Демонстраційний сценарій винесено
 * в окремий {@link Main}.</p>
 */
public class Task2StayPackages {

    /**
     * Допоміжний журнал подій.
     *
     * <p>Час умовний: кожна наступна подія зсувається на 5 секунд, починаючи
     * з 09:00:00. Формат рядка: {@code [час] [джерело] подія}.</p>
     */
    public static final class Log {
        private static int seconds = 9 * 3600;

        private Log() {
        }

        public static void event(String source, String message) {
            seconds += 5;
            System.out.printf("[%02d:%02d:%02d] [%s] %s%n",
                    seconds / 3600, (seconds / 60) % 60, seconds % 60, source, message);
        }
    }

    /**
     * Component — спільний інтерфейс для окремої послуги та пакета.
     *
     * <p>Клієнтський код працює з будь-яким вузлом структури через цей
     * інтерфейс і не перевіряє, чи це лист, чи складений пакет.</p>
     */
    public interface StayComponent {
        String getName();

        double getPrice();

        List<String> getServices();

        List<StayComponent> getChildren();
    }

    /**
     * Leaf — окрема послуга готелю.
     *
     * <p>Зберігає лише власні дані: назву та ціну. Не має дочірніх
     * елементів, тому {@link #getChildren()} повертає порожній список.</p>
     */
    public static class HotelService implements StayComponent {
        private final String name;
        private final double price;

        public HotelService(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public double getPrice() {
            return price;
        }

        @Override
        public List<String> getServices() {
            return List.of(name);
        }

        @Override
        public List<StayComponent> getChildren() {
            return List.of();
        }

        @Override
        public String toString() {
            return "HotelService{name='" + name + "', price=" + price + "}";
        }
    }

    /**
     * Composite — пакет, який складається з послуг та інших пакетів.
     *
     * <p>Вартість пакета — це сума вартості всіх дочірніх компонентів.
     * Перелік послуг так само збирається рекурсивно, тому вкладені пакети
     * обробляються тим самим кодом, що й окремі послуги.</p>
     */
    public static class StayPackage implements StayComponent {
        private final String name;
        private final List<StayComponent> children = new ArrayList<>();

        public StayPackage(String name) {
            this.name = name;
        }

        /** Додає до пакета послугу або вкладений пакет. */
        public void add(StayComponent component) {
            children.add(component);
            Log.event("StayPackage", "До пакета «" + name + "» додано «"
                    + component.getName() + "»");
        }

        /** Вилучає послугу або вкладений пакет. */
        public void remove(StayComponent component) {
            if (children.remove(component)) {
                Log.event("StayPackage", "З пакета «" + name + "» вилучено «"
                        + component.getName() + "»");
            }
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public double getPrice() {
            double total = 0.0;
            for (StayComponent child : children) {
                total += child.getPrice();
            }
            return total;
        }

        @Override
        public List<String> getServices() {
            List<String> services = new ArrayList<>();
            for (StayComponent child : children) {
                services.addAll(child.getServices());
            }
            return List.copyOf(services);
        }

        @Override
        public List<StayComponent> getChildren() {
            return List.copyOf(children);
        }

        @Override
        public String toString() {
            return "StayPackage{name='" + name + "', children=" + children.size() + '}';
        }
    }
}
