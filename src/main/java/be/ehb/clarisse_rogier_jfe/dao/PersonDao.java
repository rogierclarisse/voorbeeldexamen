package be.ehb.clarisse_rogier_jfe.dao;

import be.ehb.clarisse_rogier_jfe.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonDao extends CrudRepository<Person, Integer> {

}
