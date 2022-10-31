import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateMain {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

//        Student student = new Student();
//        student.setName("Mitaka");
//
//        session.persist(student);
//
//        Student fromDb = session.get(Student.class, 2);
//
//        System.out.println(fromDb.getId() + " " + fromDb.getName());

        List<Student> list = session.createQuery("FROM Student", Student.class).list();

        list.forEach(s -> System.out.println(s.getName() + " " + s.getId()));
        session.getTransaction().commit();
        session.close();


    }
}
