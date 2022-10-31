package databaseAppsIntroduction;

import databaseAppsIntroduction.database.DBConnector;
import databaseAppsIntroduction.database.DBQueries;

import java.sql.*;
import java.util.Scanner;

public class GetMinionsNames {

    public static void main(String[] args) throws SQLException {

        Connection connection = DBConnector.getSQLConnection();

        PreparedStatement stmt = connection.prepareStatement(DBQueries.GET_MINIONS_NAMES);

        Scanner scanner = new Scanner(System.in);

        int villain_id = Integer.parseInt(scanner.nextLine());

        stmt.setInt(1, villain_id);

        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            System.out.printf(Messages.NO_VILLAIN_WITH_ID_FOUND_TEXT, villain_id);
        } else {
            System.out.printf(Messages.VILLAIN_NAME, rs.getString("villain_name"));
            int minion_counter = 1;
            do {
                System.out.printf(Messages.MINION_INFO, minion_counter, rs.getString("minions_name"), rs.getInt("minions_age"));
                minion_counter++;
            } while (rs.next());
        }

    }
}
