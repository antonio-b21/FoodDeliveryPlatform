package FoodDelivery;

import java.util.*;
import java.util.stream.IntStream;

public final class App {
    private static final List<Restaurant> restaurants;
    private static final Queue<Order> pendingOrders;

    static {
        restaurants = new ArrayList<>();
        pendingOrders = new PriorityQueue<>();
    }

    private App() { }

    // Restaurants

    public static void addRestaurant(String name, String address) {
        restaurants.add(new Restaurant(name, address));
    }

    public static void addDish(int restaurantId, String name, double price, String ingredients) {
        restaurants.get(restaurantId - 1).addDish(name, price, ingredients);
    }

    public static void showMenu(int restaurantId) {
        restaurants.get(restaurantId - 1).showMenu();
    }

    public static void listRestaurants() {
        System.out.println("Restaurants:");
        IntStream.range(0, restaurants.size())
                .mapToObj(i -> (i + 1) + ". " + restaurants.get(i))
                .forEach(System.out :: println);
        System.out.println("You can choose to show the menu of any restaurant or directly place an order.");
    }

    // Users

    public static RegularUser createAccount(String name, String phoneNumber, String address) {
        return new RegularUser(name, phoneNumber, address);
    }

    public static PremiumUser goPremium(User user) {
        if ("PremiumUser".equals(user.getClass().getSimpleName())) {
            System.out.println("You are already a premium user. Thank you for supporting us!");
            return (PremiumUser) user;
        } else {// RegularUser
            PremiumUser premiumUser = new PremiumUser((RegularUser) user, new Date(System.currentTimeMillis()));
            System.out.println("Successfully upgraded to premium. Thank you for supporting us!");
            return premiumUser;
        }
    }

    public static void showProfile(User user) {
        System.out.println(user);
    }

    public static void placeOrder(User user, int restaurantId, List<Integer> dishIndexes) {
        Restaurant restaurant = restaurants.get(restaurantId - 1);
        List<Dish> dishes = restaurant.getDishes(dishIndexes);
        Order order = user.addOrder(restaurant, dishes);
        pendingOrders.add(order);
    }

    public static void listOrders(User user) {
        user.listOrders();
        System.out.println("You can choose to get a detailed view of any order.");
    }

    public static void showOrder(User user, int orderId) {
        user.showOrder(orderId);
    }

    // Couriers

    public static Rider registerRider(String name, String phoneNumber) {
        return new Rider(name, phoneNumber);
    }

    public static Driver registerDriver(String name, String phoneNumber, String licencePlate) {
        return new Driver(name, phoneNumber, licencePlate);
    }

    public static void showProfile(Courier courier) {
        System.out.println(courier);
    }

    public static void work(Courier courier) {
        Order delivery = pendingOrders.poll();
        if (delivery != null) {
            delivery.assignCourier(courier);
            courier.addDelivery(delivery);
        } else {
            System.out.println("There are no unassigned orders right now.");
        }
    }

    public static void listDeliveries(Courier courier) {
        courier.listDeliveries();
        System.out.println("You can choose to get a detailed view of any delivery.");
    }

    public static void showDelivery(Courier courier, int deliveryId) {
        courier.showDelivery(deliveryId);
    }
}
