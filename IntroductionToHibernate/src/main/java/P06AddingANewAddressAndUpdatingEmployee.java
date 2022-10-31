import entities.Address;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class P06AddingANewAddressAndUpdatingEmployee {
    private static final String UPDATE_EMPLOYEES_ADDRESS_BY_LAST_NAME = "UPDATE Employee SET address = :employee_address WHERE lastName = :last_name";
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();
        entityManager.getTransaction().begin();

        Address address = new Address();
        address.setText("Vitoshka 15");

        entityManager.persist(address);

        Scanner scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();

        int countOfUpdatedRecords = entityManager.createQuery(UPDATE_EMPLOYEES_ADDRESS_BY_LAST_NAME)
                .setParameter("employee_address", address)
                .setParameter("last_name", lastName)
                .executeUpdate();

        System.out.println(countOfUpdatedRecords);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
