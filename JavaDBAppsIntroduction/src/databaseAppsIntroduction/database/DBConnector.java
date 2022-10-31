package databaseAppsIntroduction.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {

    public static Connection getSQLConnection() throws SQLException {

        final Properties properties = new Properties();
        properties.setProperty("user", DBParams.username);
        properties.setProperty("password", DBParams.password);

        return DriverManager.getConnection(DBParams.connectionString, properties);
    }
}
