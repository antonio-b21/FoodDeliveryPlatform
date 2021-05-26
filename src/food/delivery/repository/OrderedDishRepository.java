package food.delivery.repository;

import food.delivery.config.DatabaseConfiguration;
import food.delivery.models.order.OrderedDish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderedDishRepository {

    private static final OrderedDishRepository orderedDishRepository = new OrderedDishRepository();

    private OrderedDishRepository() {
    }

    public static OrderedDishRepository getOrderedDishRepository() {
        return orderedDishRepository;
    }

    //    CREATE
    public int insert(OrderedDish orderedDish) {
        String sql = "INSERT INTO ordered_dishes (order_id, dish_id) VALUES (?, ?)";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, orderedDish.getOrderId());
            preparedStatement.setInt(2, orderedDish.getDishId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            e.printStackTrace();
            System.exit(1);
        }
        return 0;
    }

    //    READ
    public OrderedDish select(int id) {
        String sql = "SELECT * FROM ordered_dishes WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new OrderedDish(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3)
                );
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return null;
    }

    //    UPDATE
    public int updateOrderId(int id, int orderId) {
        String sql = "UPDATE ordered_dishes SET order_id = ? WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return 0;
    }

    public int updateDishId(int id, int dishId) {
        String sql = "UPDATE ordered_dishes SET dish_id = ? WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, dishId);
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
        String sql = "DELETE FROM ordered_dishes WHERE id = ?";

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
    public List<OrderedDish> list() {
        String sql = "SELECT * FROM ordered_dishes";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<OrderedDish> orderedDishList = new ArrayList<>();
            while(resultSet.next()) {
                orderedDishList.add(new OrderedDish(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3)
                ));
            }
            return orderedDishList;
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return null;
    }

}
