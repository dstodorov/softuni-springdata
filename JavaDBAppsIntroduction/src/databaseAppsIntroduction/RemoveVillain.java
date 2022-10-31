package databaseAppsIntroduction;

import databaseAppsIntroduction.database.DBConnector;
import databaseAppsIntroduction.database.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        int villainId = Integer.parseInt(scanner.nextLine());

        Connection connection = DBConnector.getSQLConnection();

        PreparedStatement getVillainByIdStatement = connection.prepareStatement(DBQueries.GET_VILLAIN_BY_ID);
        getVillainByIdStatement.setInt(1, villainId);

        ResultSet villainIdResultSet = getVillainByIdStatement.executeQuery();

        if (!villainIdResultSet.next()) {
            System.out.printf(Messages.NO_VILLAIN_WITH_ID_FOUND_TEXT2);
            return;
        }

        PreparedStatement getVillainInfoStatement = connection.prepareStatement(DBQueries.GET_VILLAIN_INFO_FOR_DELETION);
        getVillainInfoStatement.setInt(1, villainId);

        ResultSet villainInfoResultSet = getVillainInfoStatement.executeQuery();
        villainInfoResultSet.next();

        String villainName = villainInfoResultSet.getString("villain_name");
        int releasedMinionsCount = villainInfoResultSet.getInt("minions_count");

        PreparedStatement releaseMinionsStatement = connection.prepareStatement(DBQueries.RELEASE_VILLAIN_MINIONS);
        releaseMinionsStatement.setInt(1, villainId);
        releaseMinionsStatement.executeUpdate();

        PreparedStatement deleteVillainStatement = connection.prepareStatement(DBQueries.DELETE_VILLAIN);
        deleteVillainStatement.setInt(1, villainId);
        deleteVillainStatement.executeUpdate();

        System.out.printf(Messages.DELETED_VILLAIN_NAME_TEXT, villainName);
        System.out.printf(Messages.RELEASED_MINIONS_COUNT_TEXT, releasedMinionsCount);
    }
}
