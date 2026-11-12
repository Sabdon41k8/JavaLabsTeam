package lab12.src.task1;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CategoryFactory factory = new CategoryFactory();

        // 4 категорії номерів (дані зберігаються один раз у фабриці/об'єктах категорій)
        RoomCategory standard = factory.getCategory(
                "Стандарт",
                "Зручний номер для одного або двох гостей.",
                22.0,
                List.of("Wi-Fi", "Телевізор", "Кондиціонер"),
                List.of("standard1.jpg", "standard2.jpg"),
                1200.0
        );
        RoomCategory comfort = factory.getCategory(
                "Комфорт",
                "Просторий номер з додатковими зручностями.",
                30.0,
                List.of("Wi-Fi", "Телевізор", "Кондиціонер", "Мінібар"),
                List.of("comfort1.jpg", "comfort2.jpg"),
                1800.0
        );
        RoomCategory deluxe = factory.getCategory(
                "Делюкс",
                "Номер підвищеного комфорту з видом.",
                38.0,
                List.of("Wi-Fi", "Телевізор", "Кондиціонер", "Мінібар", "Сейф"),
                List.of("deluxe1.jpg", "deluxe2.jpg"),
                2600.0
        );
        RoomCategory suite = factory.getCategory(
                "Люкс",
                "Багатокімнатний номер для особливих гостей.",
                55.0,
                List.of("Wi-Fi", "Телевізор", "Кондиціонер", "Мінібар", "Сейф", "Джакузі"),
                List.of("suite1.jpg", "suite2.jpg", "suite3.jpg"),
                4200.0
        );

        RoomCategory[] categories = {standard, comfort, deluxe, suite};

        // 60 номерів, розподілені між 4 категоріями
        int roomCount = 60;
        RoomCategory[] roomCategoryByNumber = new RoomCategory[roomCount + 1];
        for (int room = 1; room <= roomCount; room++) {
            roomCategoryByNumber[room] = categories[(room - 1) % categories.length];
        }

        // Календар для 60 номерів на 365 ночей
        LocalDate start = LocalDate.of(2026, 1, 1);
        int nights = 365;
        List<OccupancyRecord> calendar = new ArrayList<>(roomCount * nights);

        for (int day = 0; day < nights; day++) {
            LocalDate date = start.plusDays(day);
            for (int room = 1; room <= roomCount; room++) {
                // Кожен запис зберігає лише номер кімнати, дату, стан і посилання на категорію
                calendar.add(new OccupancyRecord(room, date, RoomState.FREE, roomCategoryByNumber[room]));
            }
        }

        System.out.println("Кількість записів календаря: " + calendar.size());
        System.out.println("Кількість об'єктів категорій: " + factory.getCategoryCount());

        // Два записи одного номера на різні дати
        OccupancyRecord record1 = findRecord(calendar, 7, start);
        OccupancyRecord record2 = findRecord(calendar, 7, start.plusDays(120));

        System.out.println();
        System.out.println("Запис 1: " + record1);
        System.out.println("Запис 2: " + record2);
        System.out.println("Той самий об'єкт категорії (==): " + (record1.getCategory() == record2.getCategory()));
        System.out.println("identityHashCode запису 1: " + System.identityHashCode(record1.getCategory()));
        System.out.println("identityHashCode запису 2: " + System.identityHashCode(record2.getCategory()));

        // Демонстрація: повторне отримання категорії не створює нового об'єкта
        RoomCategory again = factory.getCategory(
                "Стандарт",
                "інший опис не використовується, бо об'єкт уже є в кеші",
                0.0,
                List.of(),
                List.of(),
                0.0
        );
        System.out.println();
        System.out.println("Повторне отримання 'Стандарт' повертає той самий об'єкт (==): " + (standard == again));
    }

    private static OccupancyRecord findRecord(List<OccupancyRecord> calendar, int roomNumber, LocalDate date) {
        for (OccupancyRecord record : calendar) {
            if (record.getRoomNumber() == roomNumber && record.getDate().equals(date)) {
                return record;
            }
        }
        throw new IllegalArgumentException("Запис не знайдено: кімната " + roomNumber + ", дата " + date);
    }
}
