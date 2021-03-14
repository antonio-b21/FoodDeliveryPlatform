package FoodDelivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class Courier extends Person {
    private final List<Order> deliveries;

    Courier(String name, String phoneNumber) {
        super(name, phoneNumber);
        deliveries = new ArrayList<>();
    }

    protected Courier(Courier courier) {
        super(courier);
        this.deliveries = courier.deliveries.stream().map(Order::copy).collect(Collectors.toList());
    }

    abstract Courier copy();

    void addDelivery(Order delivery) {
        deliveries.add(delivery);
    }

    void showDelivery(int deliveryId) {
        System.out.println(deliveries.get(deliveryId - 1));
    }

    void listDeliveries() {
        System.out.println(super.getName() + "'s deliveries:");
        IntStream.range(0, deliveries.size())
                .mapToObj(i -> (i + 1) + ". " + deliveries.get(i).toBriefString())
                .forEach(System.out :: println);
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
