package lab12.src.task1;
import java.util.List;
import java.util.Objects;

public class RoomCategory {
    private final String name;
    private final String description;
    private final double area;
    private final List<String> amenities;
    private final List<String> photos;
    private final double basePrice;

    public RoomCategory(String name, String description, double area,
                        List<String> amenities, List<String> photos, double basePrice) {
        this.name = name;
        this.description = description;
        this.area = area;
        this.amenities = List.copyOf(amenities);
        this.photos = List.copyOf(photos);
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getArea() {
        return area;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        return "RoomCategory{name='" + name + "', area=" + area + ", basePrice=" + basePrice + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomCategory)) return false;
        RoomCategory that = (RoomCategory) o;
        return Double.compare(that.area, area) == 0
                && Double.compare(that.basePrice, basePrice) == 0
                && Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && Objects.equals(amenities, that.amenities)
                && Objects.equals(photos, that.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, area, amenities, photos, basePrice);
    }
}
