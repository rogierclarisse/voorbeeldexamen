package be.ehb.clarisse_rogier_jfe.dao;

import be.ehb.clarisse_rogier_jfe.entities.Bid;
import be.ehb.clarisse_rogier_jfe.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface BidDao extends CrudRepository<Bid, Integer> {

    Iterable<Bid> findAllByProductId(Product id);
}
