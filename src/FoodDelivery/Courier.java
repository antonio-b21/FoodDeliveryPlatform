package FoodDelivery;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public abstract class Courier extends Person {
    private final List<Order> deliveries;

    Courier(String name, String phoneNumber) {
        super(name, phoneNumber);
        deliveries = new LinkedList<>();
    }

    protected Courier(Courier courier) {
        super(courier);
        this.deliveries = courier.deliveries.stream()
                .map(Order::copy)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    abstract Courier copy();

    void addDelivery(Order delivery) {
        deliveries.add(delivery);
        System.out.println(delivery);
    }

    void showDelivery(int deliveryId) {
        System.out.println(deliveries.get(deliveryId - 1));
    }

    void listDeliveries() {
        System.out.println(super.getName() + "'s deliveries:");
        AtomicInteger i = new AtomicInteger();
        deliveries.forEach(d-> { i.addAndGet(1); System.out.println(i + ". " + d.toBriefString()); });
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Courier courier = (Courier) o;
        return Objects.equals(deliveries, courier.deliveries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deliveries);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
