package food.delivery.repository;

import food.delivery.config.DatabaseConfiguration;
import food.delivery.models.courier.Rider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RiderRepository {

    private static final RiderRepository riderRepository = new RiderRepository();

    private RiderRepository() {
    }

    public static RiderRepository getRiderRepository() {
        return riderRepository;
    }

    //    CREATE
    public int insert(Rider rider) {
        String sql = "INSERT INTO riders (name, phone_number) VALUES (?, ?)";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setString(1, rider.getName());
            preparedStatement.setString(2, rider.getPhoneNumber());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return 0;
    }

    //    READ
    public Rider select(int id) {
        String sql = "SELECT * FROM riders WHERE id = ?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Rider(
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
        String sql = "UPDATE riders SET name = ? WHERE id = ?";

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
        String sql = "UPDATE riders SET phone_number = ? WHERE id = ?";

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

    //    DELETE
    public int delete(int id) {
        String sql = "DELETE FROM riders WHERE id = ?";

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
    public List<Rider> list() {
        String sql = "SELECT * FROM riders";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Rider> riderList = new ArrayList<>();
            while(resultSet.next()) {
                riderList.add(new Rider(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }
            return riderList;
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to access the database");
            System.exit(1);
        }
        return null;
    }

}
