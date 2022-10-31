import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("gringotts").createEntityManager();

        entityManager.getTransaction().begin();

        WizardDeposit wizard = new WizardDeposit(
                "Dimitar",
                "Todorov",
                "Simple Text",
                34,
                "Unknown",
                25,
                "Cash",
                LocalDateTime.now(),
                BigDecimal.valueOf(250.25),
                BigDecimal.valueOf(1500.25),
                BigDecimal.valueOf(999),
                LocalDateTime.now().plusDays(10),
                false
        );

        entityManager.persist(wizard);

        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
