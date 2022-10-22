package databaseAppsIntroduction;

import databaseAppsIntroduction.database.DBConnector;
import databaseAppsIntroduction.database.DBQueries;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        int[] minionIds = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        StringBuilder paramsNumber = new StringBuilder();
        for (int minionId : minionIds) {
            paramsNumber.append("?,");
        }

        paramsNumber.deleteCharAt(paramsNumber.length() - 1);

        String updateQuery = String.format(DBQueries.UPDATE_MINIONS_NAMES_AND_AGE, paramsNumber);

        Connection connection = DBConnector.getSQLConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        int paramNumber = 1;
        for (int minionId : minionIds) {
            preparedStatement.setInt(paramNumber, minionId);
            paramNumber++;
        }
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement(DBQueries.GET_ALL_MINIONS_INFO);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getInt("age"));
        }
    }
}
