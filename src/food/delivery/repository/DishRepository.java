package food.delivery.repository;

import food.delivery.config.DatabaseConfiguration;
import food.delivery.models.restaurant.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishRepository {

    private static final DishRepository dishRepository = new DishRepository();

    private DishRepository() {
    }

    public static DishRepository getDishRepository() {
        return dishRepository;
    }

//    CREATE
    public int insert(Dish dish) {
        String sql = "INSERT INTO dishes (restaurant_id, name, price, ingredients) VALUES (?, ?, ?, ?)";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, dish.getRestaurantId());
            preparedStatement.setString(2, dish.getName());
            preparedStatement.setDouble(3, dish.getPrice());
            preparedStatement.setString(4, dish.getIngredients());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return 0;
    }

//    READ
    public Dish select(int id) {
        String sql = "SELECT * FROM dishes WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Dish(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                );
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return null;
    }

//    UPDATE
    public int updateRestaurantId(int id, int restaurantId) {
        String sql = "UPDATE dishes SET restaurant_id = ? WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, restaurantId);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return 0;
    }

    public int updateName(int id, String name) {
        String sql = "UPDATE dishes SET name = ? WHERE id = ?";

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

    public int updatePrice(int id, double price) {
        String sql = "UPDATE dishes SET price = ? WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return 0;
    }

    public int updateIngredients(int id, String ingredients) {
        String sql = "UPDATE dishes SET ingredients = ? WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setString(1, ingredients);
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
        String sql = "DELETE FROM dishes WHERE id = ?";

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
    public List<Dish> list() {
        String sql = "SELECT * FROM dishes";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Dish> dishList = new ArrayList<>();
            while(resultSet.next()) {
                dishList.add(new Dish(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                ));
            }
            return dishList;
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return null;
    }

}
