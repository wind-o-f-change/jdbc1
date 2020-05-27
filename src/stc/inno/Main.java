package stc.inno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5433/jdbcDB",
                "postgres",
                "qwerty")) {
            DBUtil.renewDatabase(connection);
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM mobile")) {
                while (resultSet.next()) {
                    System.out.print("id=" + resultSet.getInt("id"));
                    System.out.print("; model=" + resultSet.getString("model"));
                    System.out.print("; price=" + resultSet.getInt("price"));
                    System.out.println("; manufacturer=" + resultSet.getString("manufacturer"));
                }
            }
        }
    }
}
