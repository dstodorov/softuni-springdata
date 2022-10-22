package databaseAppsIntroduction;

import databaseAppsIntroduction.database.DBConnector;
import databaseAppsIntroduction.database.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNameCasing {

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        String countryName = scanner.nextLine();

        Connection connection = DBConnector.getSQLConnection();

        PreparedStatement updateTownNamesStatement = connection.prepareStatement(DBQueries.UPDATE_TOWN_CASING_BY_COUNTRY_NAME);
        updateTownNamesStatement.setString(1, countryName);
        int countOfUpdatedTownNames = updateTownNamesStatement.executeUpdate();

        if (countOfUpdatedTownNames == 0) {
            System.out.println(Messages.NO_TOWNS_UPDATED_TEXT);
            return;
        }

        PreparedStatement getUpdatedTownsStatement = connection.prepareStatement(DBQueries.GET_TOWNS_BY_COUNTRY_NAME);
        getUpdatedTownsStatement.setString(1, countryName);
        ResultSet rs = getUpdatedTownsStatement.executeQuery();

        List<String> updatedTowns = new ArrayList<>();

        while (rs.next()) {
            updatedTowns.add(rs.getString("name"));
        }

        System.out.printf(Messages.UPDATED_TOWNS_MESSAGE, countOfUpdatedTownNames);
        String separatedTowns = String.join(", ", updatedTowns);
        System.out.printf(Messages.SEPARATED_TOWNS_TEXT, separatedTowns);
    }
}
