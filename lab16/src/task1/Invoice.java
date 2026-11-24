package lab16.src.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Invoice {
    private final String guestName;
    private final List<Charge> charges = new ArrayList<>();

    public Invoice(String guestName) {
        this.guestName = guestName;
    }

    public void addCharge(Charge charge) {
        charges.add(charge);
    }

    public String getGuestName() {
        return guestName;
    }

    public List<Charge> getCharges() {
        return Collections.unmodifiableList(charges);
    }

    public void apply(ChargeVisitor visitor) {
        for (Charge charge : charges) {
            charge.accept(visitor);
        }
    }
}
