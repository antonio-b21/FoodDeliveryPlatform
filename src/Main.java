import FoodDelivery.App;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        App.start();

        System.out.println();

        App.listRestaurants();
        System.out.println();

        App.showMenu(3);
        System.out.println();

        App.listUsers();
        System.out.println();
        App.showUser(1);
        System.out.println();
        App.listOrders(1);
        System.out.println();

        App.placeOrder(1, 3, new ArrayList<>(Arrays.asList(1, 1)));
        System.out.println();
        App.listOrders(1);
        System.out.println();
        App.showOrder(1, 1);
        System.out.println();

        App.showCourier(1);
        System.out.println();

        App.listCouriers();
        System.out.println();
        App.showCourier(2);
        App.listDeliveries(2);
        System.out.println();

        App.work(2);
        System.out.println();
        App.listDeliveries(2);
        System.out.println();
        App.showDelivery(2, 1);
        System.out.println();

        App.showOrder(1, 1);
        System.out.println();
    }
}
