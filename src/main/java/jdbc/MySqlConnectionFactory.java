package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/technobel?useSSL=false";
    private static final String USER = "COURSDB";
    private static final String PASSWORD = "COURSDB";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
