package parking.app.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String POSTGRESQL_URL = "jdbc:postgresql://dpg-cq7o8k6ehbks7392njc0-a.oregon-postgres.render.com:5432/parking01";
    private static final String POSTGRESQL_USER = "user01";
    private static final String POSTGRESQL_PASSWORD = "D6njCqzOdQWNk37KLDvTtYjrdf3fsV5W";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(POSTGRESQL_URL, POSTGRESQL_USER, POSTGRESQL_PASSWORD);
    }
}
