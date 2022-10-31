import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class P04EmployeesWithSalaryOver50000 {

    private static final BigDecimal MIN_SALARY = new BigDecimal(50000);
    private static final String GET_EMPLOYEES_WITH_MIN_50000_SALARY = "SELECT e FROM Employee e WHERE e.salary > :min_salary";
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        entityManager.createQuery(GET_EMPLOYEES_WITH_MIN_50000_SALARY, Employee.class)
                .setParameter("min_salary", MIN_SALARY)
                .getResultList()
                .stream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);

        entityManager.close();
    }
}
