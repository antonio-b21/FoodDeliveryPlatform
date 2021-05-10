package FoodDelivery;

class Rider extends Courier {
    private static int counter = 0;
    private int id;

    Rider() { }

    Rider(String name, String phoneNumber) {
        super(name, phoneNumber);
        this.id = ++counter;
    }

    protected Rider(int id, String name, String phoneNumber) {
        super(name, phoneNumber);
        this.id = id;
    }

    protected Rider(Rider rider) {
        super(rider);
        this.id = rider.id;
    }

    protected int getId() {
        return id;
    }

    @Override
    Courier copy() {
        return new Rider(this);
    }

    @Override
    public String toString() {
        return "Rider " + super.toString();
    }

    String csvHeader() {
        return "id,name,phoneNumber";
    }

    String csvString() {
        return  getId() + "," + getName() + "," + getPhoneNumber();
    }

    Rider csvObject(String csvString) {
        String[] args = csvString.split(",", 3);
        int id = Integer.parseInt(args[0]);
        String name = args[1];
        String phoneNumber = args[2];
        return new Rider(id, name, phoneNumber);
    }
}
