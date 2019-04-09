package nwt.microservice.arrangements.Repositories;

import nwt.microservice.arrangements.Entities.ArrangementDuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrangementDurationRepo extends CrudRepository<ArrangementDuration,Integer> {
}
