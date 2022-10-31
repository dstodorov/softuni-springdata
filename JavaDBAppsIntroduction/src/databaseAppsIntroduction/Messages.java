package databaseAppsIntroduction;

public interface Messages {
    String TOWN_INSERTED_MESSAGE = "Town %s was added to the database.%n";
    String VILLAIN_INSERTED_MESSAGE = "Villain %s was added to the database.%n";
    String MINION_INSERTED_IN_VILLAIN_COLLECTION = "Successfully added %s to be minion of %s";
    String DEFAULT_VILLAIN_EVILNESS = "evil";
    String DEFAULT_ID_COLUMN_TEXT = "id";
    String NO_VILLAIN_WITH_ID_FOUND_TEXT = "No villain with ID %d exists in the database.%n";
    String NO_VILLAIN_WITH_ID_FOUND_TEXT2 = "No such villain was found%n";
    String VILLAIN_NAME = "Villain: %s%n";
    String MINION_INFO = "%d. %s %d%n";
    String VILLAIN_INFO = "%s %d%n";
    String NO_TOWNS_UPDATED_TEXT = "No town names were affected.";
    String UPDATED_TOWNS_MESSAGE = "%d town names were affected.%n";
    String SEPARATED_TOWNS_TEXT = "[%s]%n";
    String DELETED_VILLAIN_NAME_TEXT = "%s was deleted%n";
    String RELEASED_MINIONS_COUNT_TEXT = "%d minions released%n";
}
