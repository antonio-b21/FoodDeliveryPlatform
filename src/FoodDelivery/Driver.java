package FoodDelivery;

class Driver extends Courier {
    private static int counter = 0;
    private int id;
    private String licencePlate;

    Driver() { }

    Driver(String name, String phoneNumber, String licencePlate) {
        super(name, phoneNumber);
        this.id = ++counter;
        this.licencePlate = licencePlate;
    }

    protected Driver(int id, String name, String phoneNumber, String licencePlate) {
        super(name, phoneNumber);
        this.id = id;
        this.licencePlate = licencePlate;
    }

    protected Driver(Driver driver) {
        super(driver);
        this.id = driver.id;
        this.licencePlate = driver.licencePlate;
    }

    protected int getId() {
        return id;
    }

    protected String getLicencePlate() {
        return licencePlate;
    }

    @Override
    Driver copy() {
        return new Driver(this);
    }

    @Override
    public String toString() {
        return "Driver " + super.toString() + " - " + getLicencePlate();
    }

    String csvHeader() {
        return "id,name,phoneNumber,licencePlate";
    }

    String csvString() {
        return  getId() + "," + getName() + "," + getPhoneNumber() + "," + getLicencePlate();
    }

    Driver csvObject(String csvString) {
        String[] args = csvString.split(",", 4);
        int id = Integer.parseInt(args[0]);
        String name = args[1];
        String phoneNumber = args[2];
        String licencePlate = args[3];
        return new Driver(id, name, phoneNumber, licencePlate);
    }
}
