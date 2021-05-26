package food.delivery.service;

import food.delivery.models.restaurant.Dish;
import food.delivery.models.restaurant.Restaurant;
import food.delivery.repository.DishRepository;
import food.delivery.repository.RestaurantRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RestaurantService {

    private RestaurantService() {
    }

    public static void addRestaurant(String name, String address) {
        RestaurantRepository restaurantRepository = RestaurantRepository.getRestaurantRepository();

        Restaurant newRestaurant = new Restaurant(name, address);
        restaurantRepository.insert(newRestaurant);

        AuditService.log("addRestaurant");
    }

    public static void showRestaurant(Restaurant restaurant) {
        System.out.println(restaurant.getName() + " - " + restaurant.getAddress());
    }

    public static void listRestaurants() {
        RestaurantRepository restaurantRepository = RestaurantRepository.getRestaurantRepository();
        List<Restaurant> restaurantList = restaurantRepository.list();

        System.out.println("Restaurants:");
        AtomicInteger i = new AtomicInteger();
        restaurantList.forEach(restaurant -> {
            i.addAndGet(1);
            System.out.print(i + ". ");
            showRestaurant(restaurant);
        });
        System.out.println("You can choose to show the menu of any restaurant or directly place an order.");

        AuditService.log("listRestaurants");
    }

    public static void addDish(int restaurantId, String name, double price, String ingredients) {
        RestaurantRepository restaurantRepository = RestaurantRepository.getRestaurantRepository();
        DishRepository dishRepository = DishRepository.getDishRepository();

        Restaurant restaurant = restaurantRepository.list().get(restaurantId - 1);
        Dish newDish = new Dish(restaurant.getId(), name, price, ingredients);
        dishRepository.insert(newDish);

        AuditService.log("addDish");
    }

    public static void showDish(Dish dish) {
        System.out.println(dish.getName() + " - ($" + String.format("%.2f", dish.getPrice()) + ") " + dish.getIngredients());
    }

    public static void showMenu(int restaurantId) {
        RestaurantRepository restaurantRepository = RestaurantRepository.getRestaurantRepository();
        DishRepository dishRepository = DishRepository.getDishRepository();
        Restaurant restaurant = restaurantRepository.list().get(restaurantId - 1);
        List<Dish> dishList = dishRepository.list();

        System.out.println(restaurant.getName() + "'s menu:");
        AtomicInteger i = new AtomicInteger();
        dishList.stream()
                .filter(dish -> dish.getRestaurantId() == restaurant.getId())
                .forEach(dish -> {
                    i.addAndGet(1);
                    System.out.print(i + ". ");
                    showDish(dish);
                });

        AuditService.log("showMenu");
    }

}
