import FoodDelivery.Courier;
import FoodDelivery.User;
import FoodDelivery.App;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        App.addRestaurant("The Cellar", "St John Street, 72");
        App.addRestaurant("The Court Street Kitchen", "Stoney Lane, 105");
        App.addRestaurant("The Old Pond", "Turner Close, 50");
        App.listRestaurants();
        System.out.println();

        App.addDish(3, "Athens Salad", 27.5, "Cucumber, tomatoes, cream cheese, red onion, bell pepper, Kalamata olives and olive oil");
        App.addDish(3, "Beef Soup", 15.5, "Soured beef soup with season vegetables");
        App.showMenu(3);
        System.out.println();

        User user0 = App.createAccount("Kim", "7458", "Pine Route, 7");
        System.out.println();

        User user1 = App.createAccount("John", "0765432198", "Park Avenue, 24");
        if (user1 != null) {
            App.showProfile(user1);
            App.listOrders(user1);
            System.out.println();

            App.placeOrder(user1, 3, new ArrayList<>(Arrays.asList(1, 1)));
            System.out.println();
            App.listOrders(user1);
            System.out.println();
            App.showOrder(user1, 1);
            System.out.println();

            Courier courier1 = App.registerRider("Michael", "0734567890");
            if (courier1 != null) {
                App.showProfile(courier1);
            }
            System.out.println();

            Courier courier2 = App.registerDriver("Tom", "0123456789", "7BSH360");
            if (courier2 != null) {
                App.showProfile(courier2);
                App.listDeliveries(courier2);
                System.out.println();

                user1 = App.goPremium(user1);
                System.out.println();

                App.work(courier2);
                System.out.println();
                App.listDeliveries(courier2);
                System.out.println();
                App.showDelivery(courier2, 1);
                System.out.println();
                App.showOrder(user1, 1);
                System.out.println();
            }
        }
    }
}
