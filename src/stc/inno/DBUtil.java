package stc.inno;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    private DBUtil() {
    }

    public static void renewDatabase(Connection connection) throws SQLException {
        /*
           Ранее перед созданием подключения к базе (connection) требовалось вручную загружать драйвер,
           Class.forName("org.postgresql.Driver")
           но с приходом JBDC 4.0 драйверов это более не требуется, так как драйвер загружается автоматически.
        */

        try (Statement statement = connection.createStatement();
        ) {
            statement.execute("-- Database: jdbcDB\n"
                              + "DROP TABLE IF EXISTS mobile;"
                              + "\n"
                              + "CREATE TABLE mobile (\n"
                              + "    id bigserial primary key,\n"
                              + "    model varchar(100) NOT NULL,\n"
                              + "    price integer NOT NULL,\n"
                              + "    manufacturer varchar(100) NOT NULL);"
                              + "\n"
                              + "INSERT INTO mobile (model, price, manufacturer)\n"
                              + "VALUES\n"
                              + "   ('P1', 100, 'Xiaomi'),\n"
                              + "   ('EDGE', 1, 'Micro'),\n"
                              + "   ('FRY1', 1001, 'Apple'),\n"
                              + "   ('FRY1', 1002, 'Apple'),\n"
                              + "   ('OGO', 10000, 'SAMSUNG');"
                              + "\n"
                              + "DROP FUNCTION IF EXISTS multiply(integer);"
                              + "\n"
                              + "CREATE OR REPLACE FUNCTION multiply(a INT) RETURNS INT AS $$\n"
                              + "BEGIN\n"
                              + "    RETURN a * 2;\n"
                              + "END\n"
                              + "$$ LANGUAGE 'plpgsql';");
        }
    }
}
