package lab15.src.task3;

public class Main {
    public static void main(String[] args) {
        scenarioStandard();
        scenarioSuiteAndChalet();
        scenarioNewCategory();
    }

    private static void scenarioStandard() {
        System.out.println("=== Сценарій 1: підготовка стандартного номера ===");
        RoomPreparation room = new StandardRoomPreparation();
        room.prepare();
    }

    private static void scenarioSuiteAndChalet() {
        System.out.println();
        System.out.println("=== Сценарій 2: люкс і шале тією самою процедурою ===");
        RoomPreparation suite = new SuitePreparation();
        suite.prepare();

        System.out.println();
        RoomPreparation chalet = new ChaletPreparation();
        chalet.prepare();
    }

    private static void scenarioNewCategory() {
        System.out.println();
        System.out.println("=== Сценарій 3: нова категорія «сімейний номер» одним класом ===");
        RoomPreparation familyRoom = new FamilyRoomPreparation();
        familyRoom.prepare();
    }
}
