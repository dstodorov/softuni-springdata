import entities.Diagnose;
import entities.Medication;
import entities.Patient;
import entities.VisitationHistory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Arrays;

public class HospitalManager {

    private EntityManager entityManager;

    public HospitalManager(String databaseName) {
        entityManager = Persistence.createEntityManagerFactory(databaseName).createEntityManager();
    }


    public String saveVisitation (Patient patient, Diagnose diagnose, String[] medications, VisitationHistory visitation) {
        entityManager.getTransaction().begin();

        Arrays.stream(medications).forEach(m -> entityManager.persist(new Medication(m, diagnose)));
        entityManager.persist(diagnose);
        entityManager.persist(patient);
        visitation.getPatients().add(patient);
        entityManager.persist(visitation);

        entityManager.getTransaction().commit();
        entityManager.close();

        return "Visitation Saved";
    }
}
