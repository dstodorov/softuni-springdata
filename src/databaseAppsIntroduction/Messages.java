package databaseAppsIntroduction;

public interface Messages {
    String TOWN_INSERTED_MESSAGE = "Town %s was added to the database.%n";
    String VILLAIN_INSERTED_MESSAGE = "Villain %s was added to the database.%n";
    String MINION_INSERTED_IN_VILLAIN_COLLECTION = "Successfully added %s to be minion of %s";
    String DEFAULT_VILLAIN_EVILNESS = "evil";
    String DEFAULT_ID_COLUMN_TEXT = "id";
    String NO_VILLAIN_WITH_ID_FOUND_TEXT = "No villain with ID %d exists in the database.%n";
    String VILLAIN_NAME = "Villain: %s%n";
    String MINION_INFO = "%d. %s %d%n";
    String VILLAIN_INFO = "%s %d%n";
}
