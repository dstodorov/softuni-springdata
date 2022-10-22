package databaseAppsIntroduction;

import databaseAppsIntroduction.database.DBConnector;
import databaseAppsIntroduction.database.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddMinion {

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        final Connection connection = DBConnector.getSQLConnection();

        //Read minion info
        String[] minionInfo = scanner.nextLine().split("\\s+");
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String minionTown = minionInfo[3];

        //Read Villain info
        String villainName = scanner.nextLine().split("\\s+")[1];

        //Check if town exists in the database
        PreparedStatement stmt = connection.prepareStatement(DBQueries.GET_TOWN_BY_NAME);
        stmt.setString(1, minionTown);
        ResultSet rs = stmt.executeQuery();

        //If town not exists, insert it
        if (!rs.next()) {
            PreparedStatement insertTownStmt = connection.prepareStatement(DBQueries.INSERT_TOWN);
            insertTownStmt.setString(1, minionTown);
            insertTownStmt.executeUpdate();
            System.out.printf(Messages.TOWN_INSERTED_MESSAGE, minionTown);
        }

        //Get id of the town
        ResultSet townIdResultSet = stmt.executeQuery();
        townIdResultSet.next();
        int townId = townIdResultSet.getInt(Messages.DEFAULT_ID_COLUMN_TEXT);

        //Check if villain exists
        stmt = connection.prepareStatement(DBQueries.GET_VILLAIN_BY_NAME);
        stmt.setString(1, villainName);
        rs = stmt.executeQuery();

        //If villain not exists, insert it with default value of "evil" for evilness_factor column
        if (!rs.next()) {
            PreparedStatement insertVillainStmt = connection.prepareStatement(DBQueries.INSERT_VILLAIN);
            insertVillainStmt.setString(1, villainName);
            insertVillainStmt.setString(2, Messages.DEFAULT_VILLAIN_EVILNESS);
            insertVillainStmt.executeUpdate();
            System.out.printf(Messages.VILLAIN_INSERTED_MESSAGE, villainName);
        }

        //Get id of the villain
        ResultSet villainIdResultSet = stmt.executeQuery();
        villainIdResultSet.next();
        int villainId = villainIdResultSet.getInt(Messages.DEFAULT_ID_COLUMN_TEXT);

        //Insert minion
        stmt = connection.prepareStatement(DBQueries.INSERT_MINION);
        stmt.setString(1, minionName);
        stmt.setInt(2, minionAge);
        stmt.setInt(3, townId);
        stmt.executeUpdate();

        //Get id of the new minion
        stmt = connection.prepareStatement(DBQueries.GET_LAST_INSERTED_MINION_ID);
        rs = stmt.executeQuery();
        rs.next();
        int lastInsertedMinionId = rs.getInt(Messages.DEFAULT_ID_COLUMN_TEXT);

        //Add the new minion in the collection of the villain
        stmt = connection.prepareStatement(DBQueries.INSERT_MINION_TO_VILLAIN_COLLECTION);
        stmt.setInt(1, lastInsertedMinionId);
        stmt.setInt(2, villainId);
        stmt.executeUpdate();

        System.out.printf(Messages.MINION_INSERTED_IN_VILLAIN_COLLECTION, minionName, villainName);

        //Check if minions of Gru are increased
        /*
            SELECT v.name, COUNT(mv.minion_id) as minions_count FROM minions m JOIN minions_villains mv on m.id = mv.minion_id
            JOIN villains v on mv.villain_id = v.id
            WHERE v.name = "Gru"
            GROUP BY mv.villain_id;
        */
    }
}
