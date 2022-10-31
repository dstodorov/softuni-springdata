import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class P11FindEmployeesByFirstName {
    private static final String GET_EMPLOYEES_BY_PART_OF_THE_FIRST_NAME = "SELECT e FROM Employee e WHERE SUBSTRING(e.firstName, 1, LENGTH(:str)) = :str";
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();
        entityManager.getTransaction().begin();

        String startPattern = new Scanner(System.in).nextLine();

        entityManager.createQuery(GET_EMPLOYEES_BY_PART_OF_THE_FIRST_NAME, Employee.class)
                .setParameter("str", startPattern)
                .getResultList()
                .forEach(e -> System.out.printf("%s %s - %s - (%.2f)%n", e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));

        entityManager.close();

    }
}
