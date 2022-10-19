package databaseAppsIntroduction;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();

        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement smtp = connection.prepareStatement("SELECT \n" +
                "\tfirst_name, \n" +
                "\tlast_name, \n" +
                "\tCOUNT(ug.user_id) games_count \n" +
                "FROM \n" +
                "\tusers u JOIN users_games ug ON u.id = ug.user_id\n" +
                "WHERE\n" +
                "\tlast_name = ?" +
                "GROUP BY \n" +
                "\tug.user_id");

        String nickname = scanner.nextLine();

        smtp.setString(1, nickname);

        ResultSet rs = smtp.executeQuery();

        if (!rs.next()) {
            System.out.println("No such user exists");
        } else {
            do {
                System.out.println("User: " + nickname);
                System.out.printf("%s %s has played %d games%n", rs.getString("first_name"), rs.getString("last_name"), Integer.parseInt(rs.getString("games_count")));
            } while (rs.next());
        }


    }
}
