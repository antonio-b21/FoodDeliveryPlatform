package FoodDelivery;

import java.util.Objects;
import java.util.regex.PatternSyntaxException;

abstract class Person extends CsvCompatible {
    private String name;
    private String phoneNumber;

    Person() { }

    Person(String name, String phoneNumber) {
        this.name = name;
        if (!phoneNumber.matches("^0[0-9]{9}$")) {
            throw new PatternSyntaxException("Invalid phone number", "^0[0-9]{9}$", -1);
        }
        this.phoneNumber = phoneNumber;
    }

    protected Person(Person person) {
        this.name = person.name;
        this.phoneNumber = person.phoneNumber;
    }

    String getName() {
        return name;
    }

    protected String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getName(), person.getName())
                && Objects.equals(getPhoneNumber(), person.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhoneNumber());
    }

    @Override
    public String toString() {
        return getName() + " - " + getPhoneNumber();
    }
}
