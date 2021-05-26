package food.delivery.models.restaurant;

import java.util.Objects;

public class Dish {

    private int id;
    private int restaurantId;
    private String name;
    private double price;
    private String ingredients;

    public Dish() {
    }

    public Dish(int restaurantId, String name, double price, String ingredients) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public Dish(int id, int restaurantId, String name, double price, String ingredients) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return getId() == dish.getId() && getRestaurantId() == dish.getRestaurantId() && Double.compare(dish.getPrice(), getPrice()) == 0 && Objects.equals(getName(), dish.getName()) && Objects.equals(getIngredients(), dish.getIngredients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRestaurantId(), getName(), getPrice(), getIngredients());
    }

}
