package food.delivery.repository;

import food.delivery.config.DatabaseConfiguration;
import food.delivery.models.order.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private static final OrderRepository orderRepository = new OrderRepository();

    private OrderRepository() {
    }

    public static OrderRepository getOrderRepository() {
        return orderRepository;
    }

    //    CREATE
    public int insert(Order order) {
        String sql = "INSERT INTO orders (user_id, restaurant_id, placement_date, total) VALUES (?, ?, ?, ?)";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getRestaurantId());
            preparedStatement.setDate(3, new Date(order.getPlacementDate().getTime()));
            preparedStatement.setDouble(4, order.getTotal());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            e.printStackTrace();
            System.exit(1);
        }
        return 0;
    }

    //    READ
    public Order select(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Order(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getDate(5),
                        resultSet.getDouble(6)
                );
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return null;
    }

    //    UPDATE
    public int updateUserId(int id, int userId) {
        String sql = "UPDATE orders SET user_id = ? WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return 0;
    }

    public int updateCourierId(int id, int courierId) {
        String sql = "UPDATE orders SET courier_id = ? WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, courierId);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return 0;
    }

    public int updateRestaurantId(int id, int restaurantId) {
        String sql = "UPDATE orders SET restaurant_id = ? WHERE id = ?";

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

    public int updateDate(int id, java.util.Date placementDate) {
        String sql = "UPDATE orders SET placement_date = ? WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setDate(1, (Date) placementDate);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return 0;
    }

    public int updateTotal(int id, double total) {
        String sql = "UPDATE orders SET total = ? WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setDouble(1, total);
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
        String sql = "DELETE FROM orders WHERE id = ?";

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
    public List<Order> list() {
        String sql = "SELECT * FROM orders";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Order> orderList = new ArrayList<>();
            while(resultSet.next()) {
                orderList.add(new Order(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getDate(5),
                        resultSet.getDouble(6)
                ));
            }
            return orderList;
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return null;
    }

}
