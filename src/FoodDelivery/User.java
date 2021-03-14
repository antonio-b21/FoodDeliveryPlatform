package FoodDelivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class User extends Person {
    private final String address;
    protected List<Order> orders;

    User(String name, String phoneNumber, String address) {
        super(name, phoneNumber);
        this.address = address;
        orders = new ArrayList<>();
    }

    protected User(User user) {
        super(user);
        this.address = user.address;
        this.orders = user.orders.stream().map(Order::copy).collect(Collectors.toList());
    }

    protected User(User user, boolean upgrade) {
        super(user);
        this.address = user.address;
        this.orders = user.orders;
    }

    abstract User copy();

    abstract Order addOrder(Restaurant restaurant, List<Dish> dishes);

    void showOrder(int orderId) {
        System.out.println(orders.get(orderId - 1));
    }

    void listOrders() {
        System.out.println(super.getName() + "'s orders:");
        IntStream.range(0, orders.size())
                .mapToObj(i -> (i + 1) + ". " + orders.get(i).toBriefString())
                .forEach(System.out :: println);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(address, user.address) &&
                Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, orders);
    }

    @Override
    public String toString() {
        return "User " + super.toString() + " - " + address;
    }
}
