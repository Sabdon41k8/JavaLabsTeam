package lab09.src.task1;

import lab09.src.common.Log;

import java.util.ArrayList;
import java.util.List;

public final class Task1Runner {
    public void run() {
        Log.event("Task1Runner", "=== Завдання 1: Object Pool — електронні ключ-картки ===");
        KeyCardPool pool = new KeyCardPool(5);
        List<ElectronicKeyCard> issuedCards = new ArrayList<>();

        Guest[] guests = {
                new Guest("Олена", "305"),
                new Guest("Андрій", "302"),
                new Guest("Марія", "303"),
                new Guest("Ігор", "304"),
                new Guest("Софія", "306"),
                new Guest("Тарас", "307"),
                new Guest("Наталія", "308")
        };

        for (Guest guest : guests) {
            Log.event("Рецепція", "Поселення гостя " + guest.getName()
                    + ", кімната " + guest.getRoomNumber());
            ElectronicKeyCard card = pool.acquire(guest.getRoomNumber());
            if (card == null) {
                Log.event("Рецепція", "Гостю " + guest.getName()
                        + " видано тимчасовий механічний ключ");
            } else {
                issuedCards.add(card);
                Log.event("Рецепція", "Гостю " + guest.getName()
                        + " видано електронну картку " + card);
            }
        }

        ElectronicKeyCard room305Card = issuedCards.stream()
                .filter(card -> "305".equals(card.getAssignedRoom()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Картку кімнати 305 не знайдено."));

        Log.event("Рецепція", "Гість із кімнати 305 виїжджає");
        pool.release(room305Card);

        Log.event("Рецепція", "Повторна видача картки K-" + room305Card.getCardId()
                + " новому гостю в кімнату 412");
        ElectronicKeyCard reusedCard = pool.acquire("412");

        if (reusedCard != null) {
            Log.event("Task1Runner", "Перевірка старого доступу після перепрограмування:");
            reusedCard.canOpenRoom("305");
            Log.event("Task1Runner", "Перевірка нового доступу:");
            reusedCard.canOpenRoom("412");
        }

        pool.printStatistics();
        Log.event("Task1Runner", "=== Завдання 1 завершено ===");
    }
}
