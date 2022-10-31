import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class P10IncreaseSalaries {
    private static final String ENGINEERING_DEPT = "Engineering";
    private static final String TOOL_DESIGN_DEPT = "Tool Design";
    private static final String MARKETING_DEPT = "Marketing";
    private static final String INFORMATION_SERVICES_DEPT = "Information Services";
    private static final String GET_EMPLOYEES_FROM_DEPARTMENTS_QUERY = "SELECT e FROM Employee e WHERE e.department.name IN (:dept_engineering, :dept_tool_design, :dept_marketing, :information_servises)";

    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery(GET_EMPLOYEES_FROM_DEPARTMENTS_QUERY, Employee.class)
                .setParameter("dept_engineering", ENGINEERING_DEPT)
                .setParameter("dept_tool_design", TOOL_DESIGN_DEPT)
                .setParameter("dept_marketing", MARKETING_DEPT)
                .setParameter("information_servises", INFORMATION_SERVICES_DEPT)
                .getResultList()
                .forEach(e -> {
                    e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));
                    System.out.printf("%s %s (%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary());
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
