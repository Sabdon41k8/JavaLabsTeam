package lab12.src.task1;
import java.util.*;

public class CategoryFactory {
    private final Map<String, RoomCategory> categories = new HashMap<>();

    public RoomCategory getCategory(String name, String description, double area,
                                    List<String> amenities, List<String> photos, double basePrice) {
        if (categories.containsKey(name)) {
            return categories.get(name);
        }
        RoomCategory category = new RoomCategory(name, description, area, amenities, photos, basePrice);
        categories.put(name, category);
        return category;
    }

    public int getCategoryCount() {
        return categories.size();
    }
}
