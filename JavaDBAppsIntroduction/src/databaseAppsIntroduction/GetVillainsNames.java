package databaseAppsIntroduction;

import databaseAppsIntroduction.database.DBConnector;
import databaseAppsIntroduction.database.DBQueries;

import java.sql.*;

public class GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBConnector.getSQLConnection();

        PreparedStatement stmt = connection.prepareStatement(DBQueries.GET_VILLAINS_NAMES);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.printf(Messages.VILLAIN_INFO, rs.getString("villain_name"), rs.getInt("minions_count"));
        }

    }
}
