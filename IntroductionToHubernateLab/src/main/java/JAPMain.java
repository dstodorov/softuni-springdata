import entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JAPMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("school-db");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();


////        Student student = new Student("Mitko");
////        entityManager.persist(student);
//
//        Student st = entityManager.find(Student.class, 2);
//
//        System.out.println(st.getName());
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
