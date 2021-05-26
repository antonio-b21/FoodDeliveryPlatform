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
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CourierService {

    private CourierService() {
    }

    public static void registerDriver(String name, String phoneNumber, String licencePlate) {
        DriverRepository driverRepository = DriverRepository.getDriverRepository();

        Driver newDriver =  new Driver(name, phoneNumber, licencePlate);
        driverRepository.insert(newDriver);

        AuditService.log("registerDriver");
    }

    public static void registerRider(String name, String phoneNumber) {
        RiderRepository riderRepository = RiderRepository.getRiderRepository();

        Rider newRider = new Rider(name, phoneNumber);
        riderRepository.insert(newRider);

        AuditService.log("registerRider");
    }

    public static void showCourier(int id) {
        DriverRepository driverRepository = DriverRepository.getDriverRepository();
        RiderRepository riderRepository = RiderRepository.getRiderRepository();
        List<Driver> driverList = driverRepository.list();
        List<Rider> riderList = riderRepository.list();

        if (id <= driverList.size()) {
            Driver driver = driverList.get(id - 1);
            System.out.println("Driver " + driver.getName() + " - " + driver.getPhoneNumber() + " - " + driver.getLicencePlate());
        } else {
            Rider rider = riderList.get((id - driverList.size()) - 1);
            System.out.println("Rider " + rider.getName() + " - " + rider.getPhoneNumber());
        }

        AuditService.log("showCourier");
    }

    public static void showDriver(Driver driver) {
        System.out.println("Driver " + driver.getName() + " - " + driver.getPhoneNumber() + " - " + driver.getLicencePlate());

    }

    public static void showRider(Rider rider) {
        System.out.println("Rider " + rider.getName() + " - " + rider.getPhoneNumber());

    }

    public static void listCouriers() {
        DriverRepository driverRepository = DriverRepository.getDriverRepository();
        RiderRepository riderRepository = RiderRepository.getRiderRepository();
        List<Driver> driverList = driverRepository.list();
        List<Rider> riderList = riderRepository.list();

        System.out.println("Couriers:");
        AtomicInteger i = new AtomicInteger();
        driverList.forEach(driver -> {
            i.addAndGet(1);
            System.out.print(i + ". ");
            showDriver(driver);
        });
        riderList.forEach(rider -> {
            i.addAndGet(1);
            System.out.print(i + ". ");
            showRider(rider);
        });

        AuditService.log("listCouriers");
    }

    public static void work(int courierId) {
        DriverRepository driverRepository = DriverRepository.getDriverRepository();
        RiderRepository riderRepository = RiderRepository.getRiderRepository();
        OrderRepository orderRepository = OrderRepository.getOrderRepository();

        List<Driver> driverList = driverRepository.list();
        List<Rider> riderList = riderRepository.list();
        Optional<Order> optionalOrder = orderRepository.list()
                .stream()
                .filter(order -> order.getCourierId() == 0)
                .findFirst();

        if (optionalOrder.isEmpty()) {
            System.out.println("There are no unassigned orders right now.");
        } else {
            if (courierId <= driverList.size()) {
                Driver driver = driverList.get(courierId - 1);
                orderRepository.updateCourierId(optionalOrder.get().getId(), driver.getId());
            } else if (courierId <= driverList.size() + riderList.size()) {
                Rider rider = riderList.get((courierId - driverList.size()) - 1);
                orderRepository.updateCourierId(optionalOrder.get().getId(), rider.getId());
            }
            System.out.println("Working...");

            AuditService.log("work");
        }
    }

    public static void showDelivery(int courierId, int deliveryId) {
        DriverRepository driverRepository = DriverRepository.getDriverRepository();
        RiderRepository riderRepository = RiderRepository.getRiderRepository();
        OrderRepository orderRepository = OrderRepository.getOrderRepository();
        UserRepository userRepository = UserRepository.getUserRepository();
        RestaurantRepository restaurantRepository = RestaurantRepository.getRestaurantRepository();
        OrderedDishRepository orderedDishRepository = OrderedDishRepository.getOrderedDishRepository();
        DishRepository dishRepository = DishRepository.getDishRepository();

        List<Driver> driverList = driverRepository.list();
        List<Rider> riderList = riderRepository.list();

        if (courierId <= driverList.size()) {
            Driver driver = driverList.get(courierId - 1);
            Order order = orderRepository.list()
                    .stream()
                    .filter(o -> o.getCourierId() == driver.getId())
                    .collect(Collectors.toList())
                    .get(deliveryId - 1);
            User user = userRepository.select(order.getUserId());
            Restaurant restaurant = restaurantRepository.select(order.getRestaurantId());
            List<OrderedDish> orderedDishList = orderedDishRepository.list()
                    .stream()
                    .filter(orderedDish -> orderedDish.getOrderId() == order.getId())
                    .collect(Collectors.toList());

            SimpleDateFormat timeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            System.out.println(restaurant.getName() + " order from " + timeFormat.format(order.getPlacementDate()));
            System.out.print("User ");
            UserService.showUser(user);
            showDriver(driver);
            AtomicInteger i = new AtomicInteger();
            orderedDishList
                    .forEach(orderedDish -> {
                        i.addAndGet(1);
                        System.out.print(i + ". ");
                        Dish dish = dishRepository.select(orderedDish.getDishId());
                        RestaurantService.showDish(dish);

                    });
            System.out.println("\t Total: $" + String.format("%.2f", order.getTotal()));

        } else if (courierId <= driverList.size() + riderList.size()) {
            Rider rider = riderList.get((courierId - driverList.size()) - 1);
            Order order = orderRepository.list()
                    .stream()
                    .filter(o -> o.getCourierId() == rider.getId())
                    .collect(Collectors.toList())
                    .get(deliveryId - 1);
            User user = userRepository.select(order.getUserId());
            Restaurant restaurant = restaurantRepository.select(order.getRestaurantId());
            List<OrderedDish> orderedDishList = orderedDishRepository.list()
                    .stream()
                    .filter(orderedDish -> orderedDish.getOrderId() == order.getId())
                    .collect(Collectors.toList());

            SimpleDateFormat timeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            System.out.println(restaurant.getName() + " order from " + timeFormat.format(order.getPlacementDate()));
            System.out.print("User ");
            UserService.showUser(user);
            showRider(rider);
            AtomicInteger i = new AtomicInteger();
            orderedDishList
                    .forEach(orderedDish -> {
                        i.addAndGet(1);
                        System.out.print(i + ". ");
                        Dish dish = dishRepository.select(orderedDish.getDishId());
                        RestaurantService.showDish(dish);

                    });
            System.out.println("\t Total: $" + String.format("%.2f", order.getTotal()));
        }

        AuditService.log("showDelivery");
    }

    public static void showDelivery(Order delivery) {
        RestaurantRepository restaurantRepository = RestaurantRepository.getRestaurantRepository();
        Restaurant restaurant = restaurantRepository.select(delivery.getRestaurantId());

        SimpleDateFormat timeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        System.out.println(restaurant.getName() + " order from " + timeFormat.format(delivery.getPlacementDate()));
        System.out.println("\t Total: $" + String.format("%.2f", delivery.getTotal()));


    }

    public static void listDeliveries(int courierId) {
        DriverRepository driverRepository = DriverRepository.getDriverRepository();
        RiderRepository riderRepository = RiderRepository.getRiderRepository();
        OrderRepository orderRepository = OrderRepository.getOrderRepository();
        List<Driver> driverList = driverRepository.list();
        List<Rider> riderList = riderRepository.list();
        List<Order> orderList = orderRepository.list();

        if (courierId <= driverList.size()) {
            Driver driver = driverList.get(courierId - 1);

            System.out.println(driver.getName() + "'s deliveries:");
            AtomicInteger i = new AtomicInteger();
            orderList.stream()
                    .filter(order -> order.getCourierId() == driver.getId())
                    .forEach(order -> {
                        i.addAndGet(1);
                        System.out.print(i + ". ");
                        showDelivery(order);
                    });
        } else if (courierId <= driverList.size() + riderList.size()) {
            Rider rider = riderList.get((courierId - driverList.size()) - 1);

            System.out.println(rider.getName() + "'s orders:");
            AtomicInteger i = new AtomicInteger();
            orderList.stream()
                    .filter(order -> order.getCourierId() == rider.getId())
                    .forEach(order -> {
                        i.addAndGet(1);
                        System.out.print(i + ". ");
                        showDelivery(order);
                    });
        }
        System.out.println("You can choose to get a detailed view of any delivery.");

        AuditService.log("listDeliveries");
    }

}
