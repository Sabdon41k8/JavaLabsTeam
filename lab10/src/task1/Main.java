package lab10.src.task1;

public class Main {
    public static void main(String[] args) {
        runTask1();
    }
 
    private static void runTask1() {
        header("Завдання 1. Підготовка номера за категорією (Abstract Factory)");
        Task1RoomPreparation.main(new String[0]);
    }
 
    private static void header(String title) {
        System.out.println();
        System.out.println("################ " + title + " ################");
    }

}
