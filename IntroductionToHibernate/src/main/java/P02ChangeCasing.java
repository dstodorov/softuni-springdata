import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.stream.Collectors;

public class P02ChangeCasing {
    private static final String GET_TOWNS_WITH_NAME_BIGGER_THAT_5_CHARS = "SELECT t FROM Town t WHERE LENGTH(t.name) > 5";
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery(GET_TOWNS_WITH_NAME_BIGGER_THAT_5_CHARS, Town.class)
                .getResultList()
                .forEach(t -> t.setName(t.getName().toUpperCase()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
