package lab07.task5;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class InventoryStockManager {
        public static class StockAdjustment {
        private String itemSku;
        private String warehouseZone;
        private int quantityDelta;
 
        public String getItemSku() { return itemSku; }
        public void setItemSku(String itemSku) { this.itemSku = itemSku; }
        public String getWarehouseZone() { return warehouseZone; }
        public void setWarehouseZone(String warehouseZone) { this.warehouseZone = warehouseZone; }
        public int getQuantityDelta() { return quantityDelta; }
        public void setQuantityDelta(int quantityDelta) { this.quantityDelta = quantityDelta; }
    }
 
    // ===== Абстракції (належать високому рівню) =====
 
    public interface InventoryRepository {
        void updateStock(String sku, int delta);
    }
 
    public interface ShelfBeacon {
        void signalShelfLight(String zone, String sku);
    }
 
    public interface StockEventLogger {
        void logStockEvent(String jsonMessage);
    }
 
    // ===== Високорівнева логіка: жодного new для інфраструктури =====
 
        private final InventoryRepository repository;
        private final ShelfBeacon beacon;
        private final StockEventLogger logger;
 
        // Constructor Injection
        public InventoryStockManager(InventoryRepository repository, ShelfBeacon beacon, StockEventLogger logger) {
            this.repository = Objects.requireNonNull(repository, "repository must not be null");
            this.beacon = Objects.requireNonNull(beacon, "beacon must not be null");
            this.logger = Objects.requireNonNull(logger, "logger must not be null");
        }
 
        public void adjustStockLevel(StockAdjustment adjustment) {
            if (adjustment == null || adjustment.getItemSku() == null || adjustment.getItemSku().isEmpty()) {
                throw new IllegalArgumentException("Некоректні дані коригування товару.");
            }
 
            repository.updateStock(adjustment.getItemSku(), adjustment.getQuantityDelta());
            beacon.signalShelfLight(adjustment.getWarehouseZone(), adjustment.getItemSku());
            logger.logStockEvent("{\"sku\":\"" + adjustment.getItemSku()
                    + "\", \"delta\":" + adjustment.getQuantityDelta() + "}");
        }
 
    // ===== Production-реалізації (низький рівень) =====
 
    public static class MongoInventoryRepository implements InventoryRepository {
        @Override
        public void updateStock(String sku, int delta) {
            System.out.println("[MongoDB Storage] Залишок для SKU '" + sku + "' змінено на " + delta + " шт.");
        }
    }
 
    public static class MqttWarehouseBeaconClient implements ShelfBeacon {
        @Override
        public void signalShelfLight(String zone, String sku) {
            System.out.println("[MQTT Protocol] Надіслано сигнал на радіо-маячок стелажа у зоні '" + zone + "' для SKU '" + sku + "'.");
        }
    }
 
    public static class JsonFileReportLogger implements StockEventLogger {
        @Override
        public void logStockEvent(String jsonMessage) {
            System.out.println("[JSON File Logger] Додано лог-запис у stock_events.json: " + jsonMessage);
        }
    }
 
    // ===== Mock-реалізації для підміни (тести/демо) =====
 
    public static class MockInventoryRepository implements InventoryRepository {
        public final List<String> calls = new ArrayList<>();
 
        @Override
        public void updateStock(String sku, int delta) {
            calls.add("updateStock(" + sku + ", " + delta + ")");
        }
    }
 
    public static class MockShelfBeacon implements ShelfBeacon {
        public final List<String> calls = new ArrayList<>();
 
        @Override
        public void signalShelfLight(String zone, String sku) {
            calls.add("signalShelfLight(" + zone + ", " + sku + ")");
        }
    }
 
    public static class MockStockEventLogger implements StockEventLogger {
        public final List<String> messages = new ArrayList<>();
 
        @Override
        public void logStockEvent(String jsonMessage) {
            messages.add(jsonMessage);
        }
    }
 
    // ===== Composition Root =====
 
    public static void main(String[] args) {
        StockAdjustment adjustment = new StockAdjustment();
        adjustment.setItemSku("SKU-1001");
        adjustment.setWarehouseZone("A-3");
        adjustment.setQuantityDelta(-5);
 
        // 1) Production-збірка
        InventoryStockManager production = new InventoryStockManager(
                new MongoInventoryRepository(),
                new MqttWarehouseBeaconClient(),
                new JsonFileReportLogger());
        production.adjustStockLevel(adjustment);
 
        // 2) Mock-збірка: той самий InventoryStockManager без змін, інші залежності
        MockInventoryRepository mockRepository = new MockInventoryRepository();
        MockShelfBeacon mockBeacon = new MockShelfBeacon();
        MockStockEventLogger mockLogger = new MockStockEventLogger();
 
        InventoryStockManager underTest = new InventoryStockManager(mockRepository, mockBeacon, mockLogger);
        underTest.adjustStockLevel(adjustment);
 
        System.out.println("--- Mock-перевірка ---");
        System.out.println(mockRepository.calls);
        System.out.println(mockBeacon.calls);
        System.out.println(mockLogger.messages);
    }
}
