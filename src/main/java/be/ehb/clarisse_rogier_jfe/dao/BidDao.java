package be.ehb.clarisse_rogier_jfe.dao;

import be.ehb.clarisse_rogier_jfe.entities.Bid;
import org.springframework.data.repository.CrudRepository;

public interface BidDao extends CrudRepository<Bid, Integer> {
}
