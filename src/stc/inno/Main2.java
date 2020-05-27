package stc.inno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main2 {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5433/jdbcDB",
                "postgres",
                "qwerty")) {
            DBUtil.renewDatabase(connection);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM mobile WHERE model = ? and price < ?")) {
                preparedStatement.setString(1, "FRY1");
                preparedStatement.setFloat(2, 2000);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.print("id=" + resultSet.getInt("id"));
                        System.out.print("; model=" + resultSet.getString("model"));
                        System.out.print("; price=" + resultSet.getInt("price"));
                        System.out.println("; manufacturer=" + resultSet.getString(
                                "manufacturer"));
                    }
                }
            }
        }
    }
}
