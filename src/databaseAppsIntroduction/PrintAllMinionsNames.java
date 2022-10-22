package databaseAppsIntroduction;

import databaseAppsIntroduction.database.DBConnector;
import databaseAppsIntroduction.database.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrintAllMinionsNames {
    public static void main(String[] args) throws SQLException {


        Connection connection = DBConnector.getSQLConnection();
        PreparedStatement stmt = connection.prepareStatement(DBQueries.GET_ALL_MINIONS_NAMES);
        ResultSet minionsResultSet = stmt.executeQuery();

        List<String> minions = new ArrayList<>();

        while (minionsResultSet.next()) {
            minions.add(minionsResultSet.getString("name"));
        }

        for (int first = 0, last = minions.size() - 1; first < minions.size() / 2; first++, last--) {
            System.out.println(minions.get(first));
            System.out.println(minions.get(last));
        }
    }
}
