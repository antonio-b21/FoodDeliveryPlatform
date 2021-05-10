package FoodDelivery;

class Dish extends CsvCompatible {
    private static int counter = 0;
    private int id;
    private int restaurantId;
    private String name;
    private double price;
    private String ingredients;

    Dish() { }

    Dish(int restaurantId, String name, double price, String ingredients) {
        this.id = ++counter;
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    protected Dish(int id, int restaurantId, String name, double price, String ingredients) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    protected Dish(Dish dish) {
        this.id = dish.id;
        this.restaurantId = dish.restaurantId;
        this.name = dish.name;
        this.price = dish.price;
        this.ingredients = dish.ingredients;
    }

    protected int getId() {
        return id;
    }

    protected int getRestaurantId() {
        return restaurantId;
    }

    protected String getName() {
        return name;
    }

    double getPrice() {
        return price;
    }

    protected String getIngredients() {
        return ingredients;
    }

    Dish copy() {
        return new Dish(this);
    }

    @Override
    public String toString() {
        return String.format("%s - ($%.2f) %s", getName(), getPrice(), getIngredients());
    }

    String csvHeader() {
        return "id,restaurantId,name,price,ingredients";
    }

    String csvString() {
        return  getId() + "," + getRestaurantId() + "," + getName() + "," + getPrice() + "," + getIngredients();
    }

    Dish csvObject(String csvString) {
        String[] args = csvString.split(",", 5);
        int id = Integer.parseInt(args[0]);
        int restaurantId = Integer.parseInt(args[1]);
        String name = args[2];
        double price = Double.parseDouble(args[3]);
        String ingredients = args[4];
        return new Dish(id, restaurantId, name, price, ingredients);
    }
}
