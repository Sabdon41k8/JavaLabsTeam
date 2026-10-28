package lab09.src.task3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class StayPackage {
    private final String packageName;
    private final String guestName;
    private final List<String> includedServices;
    private final List<String> spaProcedures;
    private int skiPassDays;
    private BigDecimal discountPercent;

    public StayPackage(
            String packageName,
            List<String> includedServices,
            List<String> spaProcedures,
            int skiPassDays,
            BigDecimal discountPercent) {
        this(packageName, "Шаблон", includedServices, spaProcedures, skiPassDays, discountPercent);
    }

    private StayPackage(
            String packageName,
            String guestName,
            List<String> includedServices,
            List<String> spaProcedures,
            int skiPassDays,
            BigDecimal discountPercent) {
        this.packageName = requireText(packageName, "packageName");
        this.guestName = requireText(guestName, "guestName");
        this.includedServices = copyTextList(includedServices, "includedServices");
        this.spaProcedures = copyTextList(spaProcedures, "spaProcedures");
        setSkiPassDays(skiPassDays);
        setDiscountPercent(discountPercent);
    }

    public StayPackage copyForGuest(String guestName) {
        return new StayPackage(
                packageName,
                guestName,
                includedServices,
                spaProcedures,
                skiPassDays,
                discountPercent);
    }

    public String getPackageName() {
        return packageName;
    }

    public String getGuestName() {
        return guestName;
    }

    public List<String> getIncludedServices() {
        return Collections.unmodifiableList(includedServices);
    }

    public List<String> getSpaProcedures() {
        return Collections.unmodifiableList(spaProcedures);
    }

    public int getSkiPassDays() {
        return skiPassDays;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void addIncludedService(String service) {
        includedServices.add(requireText(service, "service"));
    }

    public void removeIncludedService(String service) {
        includedServices.remove(Objects.requireNonNull(service, "service"));
    }

    public void addSpaProcedure(String procedure) {
        spaProcedures.add(requireText(procedure, "procedure"));
    }

    public void removeSpaProcedure(String procedure) {
        spaProcedures.remove(Objects.requireNonNull(procedure, "procedure"));
    }

    public void setSkiPassDays(int skiPassDays) {
        if (skiPassDays < 0) {
            throw new IllegalArgumentException("skiPassDays must not be negative");
        }
        this.skiPassDays = skiPassDays;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        BigDecimal value = Objects.requireNonNull(discountPercent, "discountPercent");
        if (value.compareTo(BigDecimal.ZERO) < 0 || value.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException("discountPercent must be between 0 and 100");
        }
        this.discountPercent = value;
    }

    @Override
    public String toString() {
        return "Пакет: " + packageName
                + ", гість: " + guestName
                + ", послуги: " + includedServices
                + ", SPA: " + spaProcedures
                + ", ski-pass: " + skiPassDays + " дн."
                + ", знижка: " + discountPercent.stripTrailingZeros().toPlainString() + "%";
    }

    private static String requireText(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName);
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value;
    }

    private static List<String> copyTextList(List<String> values, String fieldName) {
        Objects.requireNonNull(values, fieldName);
        List<String> copy = new ArrayList<String>(values.size());
        for (String value : values) {
            copy.add(requireText(value, fieldName + " item"));
        }
        return copy;
    }
}
