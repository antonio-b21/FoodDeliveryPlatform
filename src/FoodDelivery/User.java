package FoodDelivery;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class User extends Person {
    private static int counter = 0;
    private int id;
    private String address;
    private List<Order> orders;

    User() { }

    User(String name, String phoneNumber, String address) {
        super(name, phoneNumber);
        this.id = ++counter;
        this.address = address;
        orders = new LinkedList<>();
    }

    protected User(int id, String name, String phoneNumber, String address) {
        super(name, phoneNumber);
        this.id = ++counter;
        this.address = address;
        orders = new LinkedList<>();
    }

    protected User(User user) {
        super(user);
        this.id = user.id;
        this.address = user.address;
        this.orders = user.orders.stream()
                .map(Order::copy)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    protected int getId() {
        return id;
    }

    protected String getAddress() {
        return address;
    }

    User copy() { return new User(this); }

    void addOrder(Order newOrder)  {
        orders.add(newOrder);
    }

    void showOrder(int orderId) {
        System.out.println(orders.get(orderId - 1));
    }

    void listOrders() {
        System.out.println(getName() + "'s orders:");
        AtomicInteger i = new AtomicInteger();
        orders.forEach(d-> { i.addAndGet(1); System.out.println(i + ". " + d.toBriefString()); });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return getId() == user.getId()
                && Objects.equals(getAddress(), user.getAddress())
                && Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getAddress(), orders);
    }

    @Override
    public String toString() {
        return super.toString() + " - " + getAddress();
    }

    String csvHeader() {
        return "id,name,phoneNumber,address";
    }

    String csvString() {
        return  getId() + "," + getName() + "," + getPhoneNumber() + "," + getAddress();
    }

    User csvObject(String csvString) {
        String[] args = csvString.split(",", 4);
        int id = Integer.parseInt(args[0]);
        String name = args[1];
        String phoneNumber = args[2];
        String address = args[3];
        return new User(id, name, phoneNumber, address);
    }
}
