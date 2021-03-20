package FoodDelivery;

import java.util.List;

class RegularUser extends User {
    private static final double DELIVERY_FEE = 10;

    RegularUser(String name, String phoneNumber, String address) {
        super(name, phoneNumber, address);
    }

    private RegularUser(RegularUser user) {
        super(user);
    }

    @Override
    RegularUser copy() {
        return new RegularUser(this);
    }

    @Override
    Order addOrder(Restaurant restaurant, List<Dish> dishes) {
        dishes.add(new Dish("Delivery fee", DELIVERY_FEE, ""));
        double total = dishes.stream()
                .mapToDouble(Dish::getPrice)
                .sum();
        Order order = new Order(this, restaurant, dishes, total);
        orders.add(order);
        System.out.println(order);
        System.out.println("The delivery fee is $10. Go premium and you will get free delivery!");
        return order;
    }
}
