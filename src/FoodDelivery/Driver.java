package FoodDelivery;

class Driver extends Courier {
    private final String licencePlate;

    Driver(String name, String phoneNumber, String licencePlate) {
        super(name, phoneNumber);
        this.licencePlate = licencePlate;
    }

    private Driver(Driver driver) {
        super(driver);
        this.licencePlate = driver.licencePlate;
    }

    @Override
    Driver copy() {
        return new Driver(this);
    }

    @Override
    public String toString() {
        return "Driver " + super.toString() + " - " + licencePlate;
    }
}
