package food.delivery.service;

import food.delivery.models.order.Order;
import food.delivery.models.courier.Driver;
import food.delivery.models.courier.Rider;
import food.delivery.models.order.OrderedDish;
import food.delivery.models.restaurant.Dish;
import food.delivery.models.restaurant.Restaurant;
import food.delivery.models.user.User;
import food.delivery.repository.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class UserService {

    private UserService() {
    }

    public static void createAccount(String name, String phoneNumber, String address) {
        UserRepository userRepository = UserRepository.getUserRepository();

        User newUser = new User(name, phoneNumber, address);
        userRepository.insert(newUser);

        AuditService.log("createAccount");
    }

    public static void showUser(int id) {
        UserRepository userRepository = UserRepository.getUserRepository();
        User user = userRepository.list().get(id - 1);

        System.out.println(user.getName() + " - " + user.getPhoneNumber() + " - " + user.getAddress());

        AuditService.log("showUser");
    }

    public static void showUser(User user) {
        System.out.println(user.getName() + " - " + user.getPhoneNumber() + " - " + user.getAddress());
    }

    public static void listUsers() {
        UserRepository userRepository = UserRepository.getUserRepository();
        List<User> userList = userRepository.list();

        System.out.println("Users:");
        AtomicInteger i = new AtomicInteger();
        userList.forEach(user -> {
            i.addAndGet(1);
            System.out.print(i + ". ");
            showUser(user);
        });

        AuditService.log("listUsers");
    }

    public static void placeOrder(int userId, int restaurantId, List<Integer> dishIndexes) {
        OrderRepository orderRepository = OrderRepository.getOrderRepository();
        UserRepository userRepository = UserRepository.getUserRepository();
        RestaurantRepository restaurantRepository = RestaurantRepository.getRestaurantRepository();
        DishRepository dishRepository = DishRepository.getDishRepository();
        OrderedDishRepository orderedDishRepository = OrderedDishRepository.getOrderedDishRepository();

        User user = userRepository.list().get(userId - 1);
        Restaurant restaurant = restaurantRepository.list().get(restaurantId - 1);
        List<Dish> dishList = dishRepository.list()
                .stream()
                .filter(dish -> dish.getRestaurantId() == restaurant.getId())
                .collect(Collectors.toList());
        List<Dish> dishes = dishIndexes
                .stream()
                .map(i -> dishList.get(i - 1))
                .collect(Collectors.toList());
        double total = dishes.stream().mapToDouble(Dish::getPrice).sum();
        Order newOrder = new Order(user.getId(), restaurant.getId(), new Date(System.currentTimeMillis()), total);
        orderRepository.insert(newOrder);

        Order order = orderRepository.list()
                .stream()
                .filter(o ->
                    o.getUserId() == user.getId() && o.getRestaurantId() == restaurant.getId() && o.getTotal() == total
                )
                .findFirst()
                .get();

        dishes.forEach(dish -> {
            OrderedDish newOrderedDish = new OrderedDish(order.getId(), dish.getId());
            orderedDishRepository.insert(newOrderedDish);
        });

        AuditService.log("placeOrder");
    }

    public static void showOrder(int userId, int orderId) {
        UserRepository userRepository = UserRepository.getUserRepository();
        OrderRepository orderRepository = OrderRepository.getOrderRepository();
        DriverRepository driverRepository = DriverRepository.getDriverRepository();
        RiderRepository riderRepository = RiderRepository.getRiderRepository();
        RestaurantRepository restaurantRepository = RestaurantRepository.getRestaurantRepository();
        OrderedDishRepository orderedDishRepository = OrderedDishRepository.getOrderedDishRepository();
        DishRepository dishRepository = DishRepository.getDishRepository();

        User user = userRepository.list().get(userId - 1);
        Order order = orderRepository.list()
                .stream()
                .filter(o -> o.getUserId() == user.getId())
                .collect(Collectors.toList())
                .get(orderId - 1);
        List<Driver> driverList = driverRepository.list();
        Driver lastDriver = driverList.get(driverList.size() - 1);
        List<Rider> riderList = riderRepository.list();
        Rider lastRider = riderList.get(riderList.size() - 1);
        Restaurant restaurant = restaurantRepository.select(order.getRestaurantId());
        List<OrderedDish> orderedDishList = orderedDishRepository.list()
                .stream()
                .filter(orderedDish -> orderedDish.getOrderId() == order.getId())
                .collect(Collectors.toList());

        SimpleDateFormat timeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        System.out.println(restaurant.getName() + " order from " + timeFormat.format(order.getPlacementDate()));
        System.out.print("User ");
        UserService.showUser(user);
        if (order.getCourierId() != 0 && order.getCourierId() <= lastDriver.getId()) {
            Driver driver = driverRepository.select(order.getCourierId());
            CourierService.showDriver(driver);
        } else if (order.getCourierId() != 0 && order.getCourierId() <= lastRider.getId()) {
            Rider rider = riderRepository.select(order.getCourierId());
            CourierService.showRider(rider);
        } else {
            System.out.println("No courier has been assigned yet.");
        }
        AtomicInteger i = new AtomicInteger();
        orderedDishList
                .forEach(orderedDish -> {
                    i.addAndGet(1);
                    System.out.print(i + ". ");
                    Dish dish = dishRepository.select(orderedDish.getDishId());
                    RestaurantService.showDish(dish);

                });
        System.out.println("\t Total: $" + String.format("%.2f", order.getTotal()));

        AuditService.log("showOrder");
    }

    public static void showOrder(Order order) {
        RestaurantRepository restaurantRepository = RestaurantRepository.getRestaurantRepository();
        Restaurant restaurant = restaurantRepository.select(order.getRestaurantId());

        SimpleDateFormat timeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        System.out.println(restaurant.getName() + " order from " + timeFormat.format(order.getPlacementDate()));
        System.out.println("\t Total: $" + String.format("%.2f", order.getTotal()));
    }

    public static void listOrders(int userId) {
        UserRepository userRepository = UserRepository.getUserRepository();
        OrderRepository orderRepository = OrderRepository.getOrderRepository();
        User user = userRepository.list().get(userId - 1);
        List<Order> orderList = orderRepository.list();

        System.out.println(user.getName() + "'s orders:");
        AtomicInteger i = new AtomicInteger();
        orderList.stream()
                .filter(order -> order.getUserId() == user.getId())
                .forEach(order -> {
                    i.addAndGet(1);
                    System.out.print(i + ". ");
                    showOrder(order);
                });
        System.out.println("You can choose to get a detailed view of any order.");

        AuditService.log("listOrders");
    }

}
