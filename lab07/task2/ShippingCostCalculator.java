package lab07.task2;

/**
 * Завдання 4 (ISP), варіант 5: система управління автопарком.
 *
 * "Жирний" IVehicleController розбито на ролеві інтерфейси.
 * Кожен транспорт реалізує лише ті ролі, які реально підтримує,
 * а кожен клієнт залежить лише від тих ролей, які викликає.
 */


public class ShippingCostCalculator {
        // ===== Ролеві інтерфейси =====
 
    /** Роль: керування двигуном. */
    public interface IEngineControl {
        void startEngine();
        void stopEngine();
    }
 
    /** Роль: рух з заданою швидкістю. */
    public interface IDrivable {
        void accelerate(double speed);
    }
 
    /** Роль: політ. */
    public interface IFlyable {
        void flyToAltitude(double meters);
    }
 
    /** Роль: занурення під воду. */
    public interface ISubmersible {
        void submergeToDepth(double meters);
    }
 
    // ===== Реалізації: жодних заглушок і UnsupportedOperationException =====
 
    public static class CityCar implements IEngineControl, IDrivable {
        @Override public void startEngine() { System.out.println("Car engine started."); }
        @Override public void stopEngine() { System.out.println("Car engine stopped."); }
        @Override public void accelerate(double speed) { System.out.println("Car accelerated to " + speed + " km/h."); }
    }
 
    public static class CargoDrone implements IEngineControl, IDrivable, IFlyable {
        @Override public void startEngine() { System.out.println("Drone rotors armed."); }
        @Override public void stopEngine() { System.out.println("Drone rotors disarmed."); }
        @Override public void accelerate(double speed) { System.out.println("Drone speed set to " + speed + " km/h."); }
        @Override public void flyToAltitude(double meters) { System.out.println("Flying at " + meters + " meters altitude."); }
    }
 
    /** Транспорт, якому справді потрібна роль ISubmersible. */
    public static class CargoSubmarine implements IEngineControl, IDrivable, ISubmersible {
        @Override public void startEngine() { System.out.println("Submarine engine started."); }
        @Override public void stopEngine() { System.out.println("Submarine engine stopped."); }
        @Override public void accelerate(double speed) { System.out.println("Submarine speed set to " + speed + " km/h."); }
        @Override public void submergeToDepth(double meters) { System.out.println("Submerged to " + meters + " meters depth."); }
    }
 
    // ===== Клієнти: залежать лише від тих ролей, які викликають =====
 
    public static class FleetDispatcher {
 
        /** Потрібні лише двигун і рух. Приймає і авто, і дрона, і підводний човен. */
        public <T extends IEngineControl & IDrivable> void runRoute(T vehicle, double speed) {
            vehicle.startEngine();
            vehicle.accelerate(speed);
            vehicle.stopEngine();
        }
 
        /** Потрібен лише політ. */
        public void climb(IFlyable aircraft, double altitude) {
            aircraft.flyToAltitude(altitude);
        }
 
        /** Потрібне лише занурення. */
        public void dive(ISubmersible vessel, double depth) {
            vessel.submergeToDepth(depth);
        }
    }
 
    // ===== Демо =====
 
    public static void main(String[] args) {
        FleetDispatcher dispatcher = new FleetDispatcher();
 
        CityCar car = new CityCar();
        CargoDrone drone = new CargoDrone();
        CargoSubmarine submarine = new CargoSubmarine();
 
        dispatcher.runRoute(car, 60);
        dispatcher.runRoute(drone, 40);
        dispatcher.climb(drone, 120);
        dispatcher.runRoute(submarine, 15);
        dispatcher.dive(submarine, 50);
 
        // dispatcher.climb(car, 100);    // не скомпілюється: CityCar не IFlyable
        // dispatcher.dive(drone, 10);    // не скомпілюється: CargoDrone не ISubmersible
    }

}
