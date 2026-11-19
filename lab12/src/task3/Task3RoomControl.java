package lab12.src.task3;

public class Task3RoomControl {

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

    public interface Equipment {
        String getName();

        void turnOn();

        void turnOff();
    }

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

    public abstract static class ControlDevice {
        protected final Equipment equipment;

        protected ControlDevice(Equipment equipment) {
            this.equipment = equipment;
        }

        public abstract String getDeviceName();

        public abstract void turnOn();

        public abstract void turnOff();

        protected void logCommand(String message) {
            Log.event(getDeviceName(), message);
        }
    }

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
