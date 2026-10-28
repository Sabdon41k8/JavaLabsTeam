package lab09.src.task1;

import lab09.src.common.Log;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public final class KeyCardPool {
    private final Deque<ElectronicKeyCard> available = new ArrayDeque<>();
    private final List<ElectronicKeyCard> allCards = new ArrayList<>();

    public KeyCardPool(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Розмір пулу має бути більшим за нуль.");
        }
        for (int i = 1; i <= capacity; i++) {
            ElectronicKeyCard card = new ElectronicKeyCard(i);
            available.addLast(card);
            allCards.add(card);
        }
        Log.event("KeyCardPool", "Створено пул із " + capacity + " електронних карток");
    }

    public synchronized ElectronicKeyCard acquire(String roomNumber) {
        if (available.isEmpty()) {
            Log.event("KeyCardPool", "Вільних карток немає для кімнати " + roomNumber
                    + "; обрана стратегія — тимчасовий механічний ключ");
            return null;
        }

        ElectronicKeyCard card = available.removeFirst();
        card.programForRoom(roomNumber);
        Log.event("KeyCardPool", "Картку K-" + card.getCardId()
                + " видано для кімнати " + roomNumber);
        return card;
    }

    public synchronized void release(ElectronicKeyCard card) {
        if (card == null || !allCards.contains(card)) {
            Log.event("KeyCardPool", "Повернення відхилено: картка не належить цьому пулу");
            return;
        }
        if (!card.isCheckedOut()) {
            Log.event("KeyCardPool", "Картка K-" + card.getCardId()
                    + " уже вільна; повторне повернення проігноровано");
            return;
        }

        // Перед поверненням у пул очищаємо номер і термін дії.
        card.eraseAccess();
        available.addLast(card);
        Log.event("KeyCardPool", "Картка K-" + card.getCardId()
                + " знову доступна для видачі");
    }

    public synchronized int getAvailableCount() {
        return available.size();
    }

    public int getCapacity() {
        return allCards.size();
    }

    public synchronized void printStatistics() {
        Log.event("KeyCardPool", "=== Статистика використання карток ===");
        for (ElectronicKeyCard card : allCards) {
            Log.event("KeyCardPool", "K-" + card.getCardId()
                    + ": видано " + card.getIssueCount() + " раз(и)");
        }
        Log.event("KeyCardPool", "Загальна кількість створених карток: " + allCards.size());
        Log.event("KeyCardPool", "Вільно зараз: " + available.size()
                + "/" + allCards.size());
    }
}
