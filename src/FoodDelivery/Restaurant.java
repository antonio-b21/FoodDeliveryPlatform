package FoodDelivery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class Restaurant extends CsvCompatible {
    private static int counter = 0;
    private int id;
    private String name;
    private String address;
    private List<Dish> dishes;

    Restaurant() { }

    Restaurant(String name, String address) {
        this.id = ++counter;
        this.name = name;
        this.address = address;
        dishes = new ArrayList<>();
    }

    protected Restaurant(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        dishes = new ArrayList<>();
    }

    protected Restaurant(Restaurant restaurant) {
        this.id = restaurant.id;
        this.name = restaurant.name;
        this.address = restaurant.address;
        this.dishes = restaurant.dishes.stream()
                .map(Dish::copy)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    protected int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    protected String getAddress() {
        return address;
    }

    Restaurant copy() {
        return new Restaurant(this);
    }

    void addDish(Dish newDish) {
        dishes.add(newDish);
    }

    List<Dish> getDishes(List<Integer> dishIndexes) {
        return dishIndexes.stream()
                .map(i -> dishes.get(i - 1))
                .collect(Collectors.toList());
    }

    void showMenu() {
        System.out.printf("%s's menu:\n", getName());
        AtomicInteger i = new AtomicInteger();
        dishes.forEach(d-> { i.addAndGet(1); System.out.println(i + ". " + d); });
    }

    @Override
    public String toString() {
        return getName() + " - " + getAddress();
    }

    String csvHeader() {
        return "id,name,address";
    }

    String csvString() {
        return  getId() + "," + getName() + "," + getAddress();
    }

    Restaurant csvObject(String csvString) {
        String[] args = csvString.split(",", 3);
        int id = Integer.parseInt(args[0]);
        String name = args[1];
        String address = args[2];
        return new Restaurant(id, name, address);
    }
}
