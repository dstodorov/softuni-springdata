package databaseAppsIntroduction.database;

interface DBParams {

    String hostname = "localhost";
    int port = 3306;

    String username = "root";
    String password = "Chupqrebra87";

    String database = "minions_db";

    String connectionString = String.format("jdbc:mysql://%s:%d/%s", hostname, port, database);
}
