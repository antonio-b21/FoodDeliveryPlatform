package FoodDelivery;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.PatternSyntaxException;
import java.util.stream.IntStream;

public final class App {
    private static final List<Restaurant> restaurants;
    private static final List<User> users;
    private static final List<Courier> couriers;
    private static final Queue<Order> pendingOrders;

    static {
        restaurants = new ArrayList<>();
        users = new ArrayList<>();
        couriers = new ArrayList<>();
        pendingOrders = new PriorityQueue<>();
    }

    private App() { }

    public static void start() {
        CsvReader reader = CsvReader.getReader();
        restaurants.addAll(reader.read(new Restaurant()));

        reader.read(new Dish()).forEach(d -> restaurants.stream()
                                                .filter(r -> r.getId() == d.getRestaurantId())
                                                .findFirst()
                                                .ifPresent(r -> r.addDish(d)));

        users.addAll(reader.read(new User()));
        couriers.addAll(reader.read(new Rider()));
        couriers.addAll(reader.read(new Driver()));

        if (restaurants.size() == 0) {
            App.addRestaurant("The Cellar", "St John Street 72");
            App.addRestaurant("The Court Street Kitchen", "Stoney Lane 105");
            App.addRestaurant("The Old Pond", "Turner Close 50");

            App.addDish(3, "Athens Salad", 27.5, "Cucumber, tomatoes, cream cheese, red onion, bell pepper, Kalamata olives and olive oil");
            App.addDish(3, "Beef Soup", 15.5, "Soured beef soup with season vegetables");

            App.createAccount("Kim", "7458", "Pine Route 7");
            App.createAccount("John", "0765432198", "Park Avenue 24");

            App.registerRider("Michael", "0734567890");
            App.registerDriver("Tom", "0123456789", "7BSH360");
        }
    }

    // Restaurants

    public static void addRestaurant(String name, String address) {
        Restaurant newRestaurant = new Restaurant(name, address);
        restaurants.add(newRestaurant);
        CsvWriter writer = CsvWriter.getWriter();
        writer.write(newRestaurant);
        AuditService.log("addRestaurant");
    }

    public static void listRestaurants() {
        System.out.println("Restaurants:");
        IntStream.range(0, restaurants.size())
                .mapToObj(i -> (i + 1) + ". " + restaurants.get(i))
                .forEach(System.out :: println);
        System.out.println("You can choose to show the menu of any restaurant or directly place an order.");
        AuditService.log("listRestaurants");
    }

    public static void addDish(int restaurantId, String name, double price, String ingredients) {
        Restaurant restaurant = restaurants.get(restaurantId - 1);
        Dish newDish = new Dish(restaurant.getId(), name, price, ingredients);
        restaurant.addDish(newDish);
        CsvWriter writer = CsvWriter.getWriter();
        writer.write(newDish);
        AuditService.log("addDish");
    }

    public static void showMenu(int restaurantId) {
        restaurants.get(restaurantId - 1).showMenu();
        AuditService.log("showMenu");
    }

    // Users

    public static void createAccount(String name, String phoneNumber, String address) {
        try {
            User newUser = new User(name, phoneNumber, address);
            users.add(newUser);
            CsvWriter writer = CsvWriter.getWriter();
            writer.write(newUser);
            AuditService.log("createAccount");
        } catch (PatternSyntaxException e) {
            System.out.println(e.getDescription());
        }
    }

    public static void listUsers() {
        System.out.println("Users:");
        IntStream.range(0, users.size())
                .mapToObj(i -> (i + 1) + ". " + users.get(i))
                .forEach(System.out :: println);
        AuditService.log("listUsers");
    }

    public static void showUser(int userId) {
        System.out.println(users.get(userId - 1));
        AuditService.log("showUser");
    }

    public static void listOrders(int userId) {
        users.get(userId - 1).listOrders();
        System.out.println("You can choose to get a detailed view of any order.");
        AuditService.log("listOrders");
    }

    public static void placeOrder(int userId, int restaurantId, List<Integer> dishIndexes) {
        Restaurant restaurant = restaurants.get(restaurantId - 1);
        List<Dish> dishes = restaurant.getDishes(dishIndexes);
        User user = users.get(userId - 1);
        Order newOrder = new Order(user, restaurant, dishes);
        user.addOrder(newOrder);
        pendingOrders.add(newOrder);
        System.out.println(newOrder);
        AuditService.log("placeOrder");
    }

    public static void showOrder(int userId, int orderId) {
        users.get(userId - 1).showOrder(orderId);
        AuditService.log("showOrder");
    }

    // Couriers

    public static void registerRider(String name, String phoneNumber) {
        try {
            Rider newRider = new Rider(name, phoneNumber);
            couriers.add(newRider);
            CsvWriter writer = CsvWriter.getWriter();
            writer.write(newRider);
            AuditService.log("registerRider");
        } catch (PatternSyntaxException e) {
            System.out.println(e.getDescription());
        }
    }

    public static void registerDriver(String name, String phoneNumber, String licencePlate) {
        try {
            Driver newDriver =  new Driver(name, phoneNumber, licencePlate);
            couriers.add(newDriver);
            CsvWriter writer = CsvWriter.getWriter();
            writer.write(newDriver);
            AuditService.log("registerDriver");
        } catch (PatternSyntaxException e) {
            System.out.println(e.getDescription());
        }
    }

    public static void listCouriers() {
        System.out.println("Couriers:");
        IntStream.range(0, couriers.size())
                .mapToObj(i -> (i + 1) + ". " + couriers.get(i))
                .forEach(System.out :: println);
        AuditService.log("listCouriers");
    }

    public static void showCourier(int courierId) {
        System.out.println(couriers.get(courierId - 1));
        AuditService.log("showCourier");
    }

    public static void listDeliveries(int courierId) {
        couriers.get(courierId - 1).listDeliveries();
        System.out.println("You can choose to get a detailed view of any delivery.");
        AuditService.log("listDeliveries");
    }

    public static void work(int courierId) {
        Order delivery = pendingOrders.poll();
        Courier courier = couriers.get(courierId - 1);
        if (delivery != null) {
            delivery.assignCourier(courier);
            courier.addDelivery(delivery);
            AuditService.log("work");
        } else {
            System.out.println("There are no unassigned orders right now.");
        }
    }

    public static void showDelivery(int courierId, int deliveryId) {
        couriers.get(courierId - 1).showDelivery(deliveryId);
        AuditService.log("showDelivery");
    }
}
