package databaseAppsIntroduction;

import databaseAppsIntroduction.database.DBConnector;
import databaseAppsIntroduction.database.DBQueries;

import java.sql.*;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        int minionId = Integer.parseInt(scanner.nextLine());

        Connection connection = DBConnector.getSQLConnection();

        CallableStatement callableStatement = null;

        String query = "{call usp_get_older(?)}";
        callableStatement = connection.prepareCall(query);

        callableStatement.setInt(1, minionId);
        callableStatement.execute();

        PreparedStatement stmt = connection.prepareStatement(DBQueries.GET_MINION_BY_ID);
        stmt.setInt(1, minionId);
        ResultSet rs = stmt.executeQuery();
        rs.next();

        System.out.println(rs.getString("name") + " " + rs.getInt("age"));

        /*
            DELIMITER $$

            DROP PROCEDURE IF EXISTS usp_get_older $$
            CREATE PROCEDURE usp_get_older(minion_id INT)
            BEGIN
                UPDATE minions SET age = age + 1 WHERE id = minion_id;
            END $$

            DELIMITER ;
         */
    }
}
