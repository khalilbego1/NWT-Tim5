package nwt.microservice.arrangements.Repositories;

import nwt.microservice.arrangements.Entities.ArrangementType;
import org.springframework.data.repository.CrudRepository;

public interface ArrangementTypeRepo extends CrudRepository<ArrangementType,Integer> {
}
