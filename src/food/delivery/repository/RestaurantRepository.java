package food.delivery.repository;

import food.delivery.config.DatabaseConfiguration;
import food.delivery.models.restaurant.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {

    private static RestaurantRepository restaurantRepository = new RestaurantRepository();

    private RestaurantRepository() {
    }

    public static RestaurantRepository getRestaurantRepository() {
        return restaurantRepository;
    }

//    CREATE
    public int insert(Restaurant restaurant) {
        String sql = "INSERT INTO restaurants (name, address) VALUES (?, ?)";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setString(1, restaurant.getName());
            preparedStatement.setString(2, restaurant.getAddress());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return 0;
    }

//    READ
    public Restaurant select(int id) {
        String sql = "SELECT * FROM restaurants WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Restaurant(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return null;
    }

//    UPDATE
    public int updateName(int id, String name) {
        String sql = "UPDATE restaurants SET name = ? WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return 0;
    }

    public int updateAddress(int id, String address) {
        String sql = "UPDATE restaurants SET address = ? WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setString(1, address);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return 0;
    }

//    DELETE
    public int delete(int id) {
        String sql = "DELETE FROM restaurants WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return 0;
    }

//    LIST
    public List<Restaurant> list() {
        String sql = "SELECT * FROM restaurants";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Restaurant> restaurantList = new ArrayList<>();
            while(resultSet.next()) {
                restaurantList.add(new Restaurant(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }
            return restaurantList;
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return null;
    }

}
