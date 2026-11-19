package lab12.src.task2;

import java.util.ArrayList;
import java.util.List;


public class Task2StayPackages {


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

    public interface StayComponent {
        String getName();

        double getPrice();

        List<String> getServices();

        List<StayComponent> getChildren();
    }

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

    public static class StayPackage implements StayComponent {
        private final String name;
        private final List<StayComponent> children = new ArrayList<>();

        public StayPackage(String name) {
            this.name = name;
        }

        public void add(StayComponent component) {
            children.add(component);
            Log.event("StayPackage", "До пакета «" + name + "» додано «"
                    + component.getName() + "»");
        }

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
