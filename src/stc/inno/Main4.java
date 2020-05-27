package stc.inno;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class Main4 {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5433/jdbcDB",
                "postgres",
                "qwerty")) {
            DBUtil.renewDatabase(connection);
            try (CallableStatement callableStatement = connection.prepareCall("{call multiply(?)}")) {
                callableStatement.setInt(1, 5);
                callableStatement.registerOutParameter(1, Types.INTEGER);
                callableStatement.execute();
                System.out.println(callableStatement.getInt(1));
            }
        }
    }
}
