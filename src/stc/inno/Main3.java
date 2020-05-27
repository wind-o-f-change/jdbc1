package stc.inno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main3 {
    public static void main(String[] args) throws SQLException {
        Integer[] localArgs = new Integer[]{1, 2, 3, 4};
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5433/jdbcDB",
                "postgres",
                "qwerty")) {
            DBUtil.renewDatabase(connection);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "update mobile set price=5000 where id = ?")) {
                for (Integer arg : localArgs) {
                    preparedStatement.setInt(1, arg);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        }
    }
}
