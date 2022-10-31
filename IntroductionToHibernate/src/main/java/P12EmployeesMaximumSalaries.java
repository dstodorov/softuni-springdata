import javax.persistence.EntityManager;
import javax.persistence.Persistence;
public class P12EmployeesMaximumSalaries {
    private static final String GET_DEPARTMENTS_WITH_MAX_SALARIES_QUERY = "SELECT e.department.name, MAX(e.salary) FROM Employee e GROUP BY e.department.name HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000";
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        entityManager
                .createQuery(GET_DEPARTMENTS_WITH_MAX_SALARIES_QUERY, Object[].class)
                .getResultList()
                .forEach( departmentInfo ->
                    System.out.printf("%s %s%n", departmentInfo[0], departmentInfo[1])
                );
    }
}
