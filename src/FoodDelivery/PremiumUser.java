package FoodDelivery;

import java.util.Date;
import java.util.List;

class PremiumUser extends User {
    private final Date lastPayment;

    PremiumUser(RegularUser user, Date firstPayment) {
        super(user, true);
        this.lastPayment = firstPayment;
    }

    private PremiumUser(PremiumUser user) {
        super(user);
        this.lastPayment = user.lastPayment;
    }

    @Override
    PremiumUser copy() {
        return new PremiumUser(this);
    }

    @Override
    Order addOrder(Restaurant restaurant, List<Dish> dishes) {
        double total = dishes.stream()
                .mapToDouble(Dish::getPrice)
                .sum();
        Order order = new Order(this, restaurant, dishes, total);
        orders.add(order);
        System.out.println("The delivery is free. Thank you for supporting us!");
        return order;
    }
}
