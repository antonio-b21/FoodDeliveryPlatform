package food.delivery.models.courier;

import food.delivery.models.Person;

import java.util.LinkedList;

public abstract class Courier extends Person {

    Courier() {
    }

    Courier(String name, String phoneNumber) {
        super(name, phoneNumber);
    }

}
