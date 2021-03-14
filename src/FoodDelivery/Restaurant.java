package FoodDelivery;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Restaurant {
    private final String name;
    private final String address;
    private final List<Dish> dishes;

    Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        dishes = new ArrayList<>();
    }

    private Restaurant(Restaurant restaurant) {
        this.name = restaurant.name;
        this.address = restaurant.address;
        this.dishes = restaurant.dishes.stream()
                .map(Dish::copy)
                .collect(Collectors.toList());
    }

    Restaurant copy() {
        return new Restaurant(this);
    }

    String getName() {
        return name;
    }

    void addDish(String name, double price, String ingredients) {
        dishes.add(new Dish(name, price, ingredients));
    }

    List<Dish> getDishes(List<Integer> dishIndexes) {
        return dishIndexes.stream()
                .map(i -> dishes.get(i - 1))
                .collect(Collectors.toList());
    }

    void showMenu() {
        System.out.printf("%s's menu:\n", name);
        IntStream.range(0, dishes.size())
                .mapToObj(i -> (i + 1) + ". " + dishes.get(i))
                .forEach(System.out :: println);
    }

    @Override
    public String toString() {
        return name + " - " + address;
    }
}
