package FoodDelivery;

class Dish {
    private final String name;
    private final double price;
    private final String ingredients;

    Dish(String name, double price, String ingredients) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    private Dish(Dish dish) {
        this.name = dish.name;
        this.price = dish.price;
        this.ingredients = dish.ingredients;
    }

    Dish copy() {
        return new Dish(this);
    }

    double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s - ($%.2f) %s", name, price, ingredients);
    }
}
