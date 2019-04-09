package nwt.microservice.arrangements.Repositories;

import nwt.microservice.arrangements.Entities.ArrangementType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrangementTypeRepo extends CrudRepository<ArrangementType,Integer> {
}
