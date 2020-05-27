package stc.inno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class Main5 {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5433/jdbcDB",
                "postgres",
                "qwerty")) {
            DBUtil.renewDatabase(connection);
            try (Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);
                for (int i = 0; i < 4; i++) {
                    statement.executeUpdate(
                            "INSERT INTO mobile (model, price, manufacturer)\n"
                            + "VALUES\n"
                            + "   ('G" + i + "', " + i + ", 'SAMSUNG');"
                    );
                }
                Savepoint savepoint = connection.setSavepoint();
                for (int i = 4; i < 8; i++) {
                    statement.executeUpdate(
                            "INSERT INTO mobile (model, price, manufacturer)\n"
                            + "VALUES\n"
                            + "   ('G" + i + "', " + i + ", 'SAMSUNG');"
                    );
                }
                connection.rollback(savepoint);
                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        }
    }
}
