import entities.Product;
import entities.Sale;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws ParseException {
        EntityManager entityManager = Persistence.createEntityManagerFactory("sales").createEntityManager();

        entityManager.getTransaction().begin();

//        Product product = new Product();
//        product.setCustomer("Mitko");
//        product.setPrice(BigDecimal.valueOf(250));
//
//        Sale sale = new Sale();
//        sale.setDate(new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ));
//        sale.setProduct(product);
//
//        entityManager.persist(product);
//        entityManager.persist(sale);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
