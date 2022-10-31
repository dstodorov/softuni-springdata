import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class P09FindLatest10Projects {
    private static final String GET_LAST_10_PROJECTS_QUERY = "SELECT p FROM Project p ORDER BY p.name";
    private static final String PROJECT_INFO_FORMAT =
            "Project name: %s%n " +
                    "\tProject description: %s%n" +
                    "\tProject Start Date: %s%n" +
                    "\tProject End Date: %s%n";

    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        entityManager.createQuery(GET_LAST_10_PROJECTS_QUERY, Project.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(p -> System.out.printf(PROJECT_INFO_FORMAT,
                        p.getName(), p.getDescription(), p.getStartDate().toString(), p.getEndDate() == null ? "null" : p.getEndDate().toString()));
    }
}
