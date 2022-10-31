import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class P13RemoveTowns {
    private static final String GET_ADDRESSES_FOR_DELETION_QUERY = "SELECT a FROM Address a WHERE a.town.name = :town_name";
    private static final String GET_TOWN_FOR_DELETION_QUERY = "SELECT t FROM Town t WHERE t.name = :town_name";

    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();
        entityManager.getTransaction().begin();

        String townName = new Scanner(System.in).nextLine();

        List<Address> addresses = entityManager.createQuery(GET_ADDRESSES_FOR_DELETION_QUERY, Address.class)
                .setParameter("town_name", townName)
                .getResultList();

        int countOfDeletedTowns = addresses.size();

        addresses.forEach(a -> a.getEmployees().forEach(e -> e.setAddress(null)));
        addresses.forEach(entityManager::remove);


        Town townForRemoving = entityManager.createQuery(GET_TOWN_FOR_DELETION_QUERY, Town.class)
                .setParameter("town_name", townName)
                .getSingleResult();

        entityManager.remove(townForRemoving);
        entityManager.getTransaction().commit();

        System.out.printf("%d %s in %s deleted%n", countOfDeletedTowns, countOfDeletedTowns == 1 ? "address" : "addresses", townName);

        entityManager.close();
    }
}
