import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class P07AddressesWithEmployeeCount {
    private static final String GET_ADDRESSES_QUERY = "SELECT a FROM Address a ORDER BY a.employees.size DESC";
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        entityManager.createQuery(GET_ADDRESSES_QUERY, Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(System.out::println);

        entityManager.close();
    }
}
