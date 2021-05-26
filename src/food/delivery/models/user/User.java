package food.delivery.models.user;

import food.delivery.models.Person;

import java.util.Objects;

public class User extends Person {

    private int id;
    private String address;

    public User() { }

    public User(String name, String phoneNumber, String address) {
        super(name, phoneNumber);
        this.address = address;
    }

    public User(int id, String name, String phoneNumber, String address) {
        super(name, phoneNumber);
        this.id = id;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getAddress(), user.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAddress());
    }
}
