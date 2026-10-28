package lab09.src;

import lab09.src.common.Log;
import lab09.src.task1.Task1Runner;
import lab09.src.task2.Task2Runner;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Log.event("Main", "Готель «Карпатські вершини» — лабораторна робота №9");
        runTask1();
        System.out.println();
        runTask2();
        Log.event("Main", "Демонстрацію завершено");
    }

    private static void runTask1() {
        new Task1Runner().run();
    }

    private static void runTask2() {
        new Task2Runner().run();
    }
}
