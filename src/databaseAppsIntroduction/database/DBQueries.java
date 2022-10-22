package databaseAppsIntroduction.database;

public interface DBQueries {

    // *********************>  EXERCISE - GetVillainsNames <********************* //
    String GET_VILLAINS_NAMES = "SELECT v.name as villain_name, COUNT(mv.minion_id) as minions_count FROM\n" +
            "villains v JOIN minions_villains mv on v.id = mv.villain_id\n" +
            "JOIN minions m ON mv.minion_id = m.id\n" +
            "GROUP BY v.id\n" +
            "HAVING minions_count > 15\n" +
            "ORDER BY minions_count DESC;";

    // *********************>  EXERCISE - GetMinionsNames <********************* //
    String GET_MINIONS_NAMES = "SELECT v.name as villain_name, m.name as minions_name, m.age as minions_age FROM villains v JOIN minions_villains mv on v.id = mv.villain_id JOIN minions m ON mv.minion_id = m.id WHERE v.id = ?";

    // *********************>  EXERCISE - AddMinion <********************* //
    String GET_TOWN_BY_NAME = "SELECT * FROM towns WHERE name = ?";
    String INSERT_TOWN = "INSERT INTO towns (name) VALUES(?)";
    String GET_VILLAIN_BY_NAME = "SELECT * FROM villains WHERE name = ?";
    String INSERT_VILLAIN = "INSERT INTO villains (name, evilness_factor) VALUES(?,?)";
    String INSERT_MINION = "INSERT INTO minions (name, age, town_id) VALUES (?,?,?)";
    String INSERT_MINION_TO_VILLAIN_COLLECTION = "INSERT INTO minions_villains(minion_id, villain_id) VALUES (?,?) ";
    String GET_LAST_INSERTED_MINION_ID = "SELECT MAX(id) as id from minions";

    // *********************>  EXERCISE - ChangeTownNameCasing <********************* //
    String UPDATE_TOWN_CASING_BY_COUNTRY_NAME = "UPDATE towns SET name = UPPER(name) WHERE country = ?";
    String GET_TOWNS_BY_COUNTRY_NAME = "SELECT name FROM towns WHERE country = ?";

    // *********************>  EXERCISE - Remove Villain <********************* //

    String GET_VILLAIN_BY_ID = "SELECT * FROM villains WHERE id = ?";
    String GET_VILLAIN_INFO_FOR_DELETION = "SELECT v.name as villain_name, COUNT(mv.minion_id) as minions_count FROM minions m JOIN minions_villains mv on m.id = mv.minion_id\n" +
                                "JOIN villains v on mv.villain_id = v.id\n" +
                                "WHERE v.id =?\n" +
                                "GROUP BY mv.villain_id;";

    String RELEASE_VILLAIN_MINIONS = "DELETE FROM minions_villains WHERE villain_id = ?";
    String DELETE_VILLAIN = "DELETE FROM villains WHERE id = ?";
}
