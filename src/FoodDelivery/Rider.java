package FoodDelivery;

class Rider extends Courier {
    Rider(String name, String phoneNumber) {
        super(name, phoneNumber);
    }

    private Rider(Rider rider) {
        super(rider);
    }

    @Override
    Courier copy() {
        return new Rider(this);
    }

    @Override
    public String toString() {
        return "Rider " + super.toString();
    }
}
