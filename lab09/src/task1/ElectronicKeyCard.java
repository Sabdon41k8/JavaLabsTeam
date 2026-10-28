package lab09.src.task1;

import lab09.src.common.Log;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ElectronicKeyCard {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private final int cardId;
    private String assignedRoom;
    private LocalDateTime validUntil;
    private boolean checkedOut;
    private int issueCount;

    public ElectronicKeyCard(int cardId) {
        this.cardId = cardId;
    }

    public int getCardId() {
        return cardId;
    }

    public String getAssignedRoom() {
        return assignedRoom;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public int getIssueCount() {
        return issueCount;
    }

    void programForRoom(String roomNumber) {
        Log.simulateWork("Картка-K-" + cardId,
                "Перепрограмування та реєстрація в системі замків", 120);
        assignedRoom = roomNumber;
        validUntil = LocalDateTime.now().plus(Duration.ofHours(24));
        checkedOut = true;
        issueCount++;
        Log.event("Картка-K-" + cardId, "Картка K-" + cardId + " → номер "
                + roomNumber + "; термін дії до " + validUntil.format(DATE_FORMAT));
    }

    void eraseAccess() {
        Log.simulateWork("Картка-K-" + cardId, "Стирання номера й терміну дії", 100);
        assignedRoom = null;
        validUntil = null;
        checkedOut = false;
        Log.event("Картка-K-" + cardId, "K-" + cardId
                + " повернено; попередній доступ і термін дії стерто");
    }

    public boolean canOpenRoom(String roomNumber) {
        boolean allowed = checkedOut
                && assignedRoom != null
                && assignedRoom.equals(roomNumber)
                && validUntil != null
                && LocalDateTime.now().isBefore(validUntil);
        Log.event("Замок", "Картка K-" + cardId + " → кімната " + roomNumber
                + ": " + (allowed ? "ДОСТУП ДОЗВОЛЕНО" : "ВІДМОВА У ДОСТУПІ"));
        return allowed;
    }

    @Override
    public String toString() {
        return "K-" + cardId + (assignedRoom == null
                ? " (доступ не запрограмовано)"
                : " (кімната " + assignedRoom + ")");
    }
}
