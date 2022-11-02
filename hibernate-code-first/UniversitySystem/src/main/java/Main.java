import entities.Person;
import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("university").createEntityManager();

        entityManager.getTransaction().begin();

        Teacher teacher = new Teacher();
        teacher.setFirstName("Bai Ivan");
        teacher.setEmail("bai_ivan@gmail.com");
        teacher.setPhoneNumber("123123123");

        Student student = new Student();
        student.setFirstName("Mita4eto");
        student.setLastName("Todorov");
        student.setPhoneNumber("123123");

        entityManager.persist(teacher);
        entityManager.persist(student);

        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
