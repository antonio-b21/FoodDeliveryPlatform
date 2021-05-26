package food.delivery.repository;

import food.delivery.config.DatabaseConfiguration;
import food.delivery.models.courier.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverRepository {

    private static final DriverRepository driverRepository = new DriverRepository();

    private DriverRepository() {
    }

    public static DriverRepository getDriverRepository() {
        return driverRepository;
    }

    //    CREATE
    public int insert(Driver driver) {
        String sql = "INSERT INTO drivers (name, phone_number, licence_plate) VALUES (?, ?, ?)";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setString(2, driver.getPhoneNumber());
            preparedStatement.setString(3, driver.getLicencePlate());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return 0;
    }

    //    READ
    public Driver select(int id) {
        String sql = "SELECT * FROM drivers WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Driver(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
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
        String sql = "UPDATE drivers SET name = ? WHERE id = ?";

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

    public int updatePhoneNumber(int id, String phoneNumber) {
        String sql = "UPDATE drivers SET phone_number = ? WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return 0;
    }

    public int updateLicencePlate(int id, String licencePlate) {
        String sql = "UPDATE drivers SET licence_plate = ? WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setString(1, licencePlate);
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
        String sql = "DELETE FROM drivers WHERE id = ?";

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
    public List<Driver> list() {
        String sql = "SELECT * FROM drivers";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Driver> driverList = new ArrayList<>();
            while(resultSet.next()) {
                driverList.add(new Driver(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
            return driverList;
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return null;
    }

}
