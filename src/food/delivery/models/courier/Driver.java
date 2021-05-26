package food.delivery.models.courier;

import java.util.Objects;

public class Driver extends Courier {

    private int id;
    private String licencePlate;

    public Driver() {
    }

    public Driver(String name, String phoneNumber, String licencePlate) {
        super(name, phoneNumber);
        this.licencePlate = licencePlate;
    }

    public Driver(int id, String name, String phoneNumber, String licencePlate) {
        super(name, phoneNumber);
        this.id = id;
        this.licencePlate = licencePlate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Driver driver = (Driver) o;
        return getId() == driver.getId() && Objects.equals(getLicencePlate(), driver.getLicencePlate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getLicencePlate());
    }
}
