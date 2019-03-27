package nwt.microservice.arrangements.Repositories;

import nwt.microservice.arrangements.Entities.Arrangement;
import org.springframework.data.repository.CrudRepository;

public interface ArrangementRepo extends CrudRepository<Arrangement,Integer> {
}
