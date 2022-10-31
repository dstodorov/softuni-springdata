import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.Scanner;

public class P03ContainsEmployee {
    private static final String GET_EMPLOYEES_BY_FIRST_AND_LAST_NAME = "SELECT e FROM Employee e WHERE e.firstName = :first_name AND e.lastName = :last_name";

    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        Scanner scanner = new Scanner(System.in);

        String[] fullName = scanner.nextLine().split("\\s+");
        String firstName = fullName[0];
        String lastName = fullName[1];

        try {
            entityManager.createQuery(GET_EMPLOYEES_BY_FIRST_AND_LAST_NAME, Employee.class)
                    .setParameter("first_name", firstName)
                    .setParameter("last_name", lastName)
                    .getSingleResult();
            System.out.println("Yes");
        } catch (NoResultException e) {
            System.out.println("No");
        }

        entityManager.close();

    }
}
