package FoodDelivery;

import java.util.Objects;
import java.util.regex.PatternSyntaxException;

abstract class Person {
    private final String name;
    private final String phoneNumber;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(phoneNumber, person.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber);
    }

    @Override
    public String toString() {
        return name + " - " + phoneNumber;
    }
}
