package food.delivery.menu;

import food.delivery.service.CourierService;
import food.delivery.service.RestaurantService;
import food.delivery.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {

    private static Scanner read = new Scanner(System.in);

    private Menu() {
    }

    public static void run() {
        boolean loop = true;
        while(loop) {
            System.out.println("Menu");
            System.out.println("1. create account");
            System.out.println("2. list registered users");
            System.out.println("3. view user profile");
            System.out.println("4. list restaurants");
            System.out.println("5. view menu of restaurant");
            System.out.println("6. place order");
            System.out.println("7. list orders");
            System.out.println("8. view order");
            System.out.println("9. register as driver");
            System.out.println("10. register as rider");
            System.out.println("11. list couriers");
            System.out.println("12. view courier profile");
            System.out.println("13. make delivery");
            System.out.println("14. list deliveries");
            System.out.println("15. view delivery");
            System.out.println("16. add restaurant");
            System.out.println("17. add dish");
            System.out.println("0. Exit");
            int x = read.nextInt();
            read.nextLine();
            switch (x) {
                case 1 -> createAccount();
                case 2 -> listUsers();
                case 3 -> viewUser();
                case 4 -> listRestaurants();
                case 5 -> viewMenu();
                case 6 -> placeOrder();
                case 7 -> listOrders();
                case 8 -> viewOrder();
                case 9 -> registerDriver();
                case 10 -> registerRider();
                case 11 -> listCouriers();
                case 12 -> viewCourier();
                case 13 -> makeDelivery();
                case 14 -> listDeliveries();
                case 15 -> viewDelivery();
                case 16 -> addRestaurant();
                case 17 -> addDish();
                default -> loop = false;
            }
        }
    }

    private static void createAccount() {
        System.out.println("Enter the user's name");
        String userName = read.nextLine();
        System.out.println("Enter the user's phone number");
        String userPhoneNumber = read.nextLine();
        System.out.println("Enter the user's address");
        String userAddress = read.nextLine();

        UserService.createAccount(userName, userPhoneNumber, userAddress);
        System.out.println("User account created!");
        System.out.println();
        read.nextLine();
    }

    private static void listUsers() {
        UserService.listUsers();
        System.out.println();
        read.nextLine();
    }

    private static void viewUser() {
        System.out.println("Enter the user's id:");
        int userId = read.nextInt();
        read.nextLine();

        UserService.showUser(userId);
        System.out.println();
        read.nextLine();
    }

    private static void listRestaurants() {
        RestaurantService.listRestaurants();
        System.out.println();
        read.nextLine();
    }

    private static void viewMenu() {
        System.out.println("Enter the restaurant's id:");
        int restaurantId = read.nextInt();
        read.nextLine();

        RestaurantService.showMenu(restaurantId);
        System.out.println();
        read.nextLine();
    }

    private static void placeOrder() {
        System.out.println("Enter the user's id:");
        int userId = read.nextInt();
        System.out.println("Enter the restaurant's id:");
        int restaurantId = read.nextInt();
        read.nextLine();
        System.out.println("Enter the dishes' ids separated by a space:");
        List<Integer> dishes_ids = Arrays.stream(read.nextLine().split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        UserService.placeOrder(userId, restaurantId, dishes_ids);
        System.out.println("Order placed!");
        System.out.println();
        read.nextLine();
    }

    private static void listOrders() {
        System.out.println("Enter the user's id:");
        int userId = read.nextInt();
        read.nextLine();

        UserService.listOrders(userId);
        System.out.println();
        read.nextLine();
    }

    private static void viewOrder() {
        System.out.println("Enter the user's id:");
        int userId = read.nextInt();
        System.out.println("Enter the order's id:");
        int orderId = read.nextInt();
        read.nextLine();

        UserService.showOrder(userId, orderId);
        System.out.println();
        read.nextLine();
    }

    private static void registerDriver() {
        System.out.println("Enter the driver's name");
        String driverName = read.nextLine();
        System.out.println("Enter the driver's phone number");
        String driverPhoneNumber = read.nextLine();
        System.out.println("Enter the driver's licence plate");
        String driverLicencePlate = read.nextLine();

        CourierService.registerDriver(driverName, driverPhoneNumber, driverLicencePlate);
        System.out.println("Driver account created!");
        System.out.println();
        read.nextLine();
    }

    private static void registerRider() {
        System.out.println("Enter the rider's name");
        String riderName = read.nextLine();
        System.out.println("Enter the rider's phone number");
        String riderPhoneNumber = read.nextLine();

        CourierService.registerRider(riderName, riderPhoneNumber);
        System.out.println("Driver account created!");
        System.out.println();
        read.nextLine();
    }

    private static void listCouriers() {
        CourierService.listCouriers();
        System.out.println();
        read.nextLine();
    }

    private static void viewCourier() {
        System.out.println("Enter the courier's id:");
        int courierId = read.nextInt();
        read.nextLine();

        CourierService.showCourier(courierId);
        System.out.println();
        read.nextLine();
    }

    private static void makeDelivery() {
        System.out.println("Enter the courier's id:");
        int courierId = read.nextInt();
        read.nextLine();

        CourierService.work(courierId);
        System.out.println();
        read.nextLine();
    }

    private static void listDeliveries() {
        System.out.println("Enter the courier's id:");
        int courierId = read.nextInt();
        read.nextLine();

        CourierService.listDeliveries(courierId);
        System.out.println();
        read.nextLine();
    }

    private static void viewDelivery() {
        System.out.println("Enter the courier's id:");
        int courierId = read.nextInt();
        System.out.println("Enter the delivery's id:");
        int deliveryId = read.nextInt();
        read.nextLine();

        CourierService.showDelivery(courierId, deliveryId);
        System.out.println();
        read.nextLine();
    }

    private static void addRestaurant() {
        System.out.println("Enter the restaurant's name");
        String restaurantName = read.nextLine();
        System.out.println("Enter the restaurant's address");
        String restaurantAddress = read.nextLine();

        RestaurantService.addRestaurant(restaurantName, restaurantAddress);
        System.out.println("Restaurant added!");
        System.out.println();
        read.nextLine();
    }

    private static void addDish() {
        System.out.println("Enter the restaurant's id:");
        int restaurantId = read.nextInt();
        read.nextLine();
        System.out.println("Enter the dish's name");
        String dishName = read.nextLine();
        System.out.println("Enter the dish's price:");
        double dishPrice = read.nextDouble();
        read.nextLine();
        System.out.println("Enter the dish's ingredients");
        String dishIngredients = read.nextLine();

        RestaurantService.addDish(restaurantId, dishName, dishPrice, dishIngredients);
        System.out.println("Dish added!");
        System.out.println();
        read.nextLine();
    }

}
