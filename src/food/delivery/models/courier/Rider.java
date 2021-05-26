package food.delivery.models.courier;

import java.util.Objects;

public class Rider extends Courier {

    private int id;

    public Rider() {
    }

    public Rider(String name, String phoneNumber) {
        super(name, phoneNumber);
    }

    public Rider(int id, String name, String phoneNumber) {
        super(name, phoneNumber);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rider rider = (Rider) o;
        return getId() == rider.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }
}
