package be.ehb.clarisse_rogier_jfe.dao;

import be.ehb.clarisse_rogier_jfe.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product, Integer> {
}
