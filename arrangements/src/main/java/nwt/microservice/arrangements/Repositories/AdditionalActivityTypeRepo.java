package nwt.microservice.arrangements.Repositories;

import nwt.microservice.arrangements.Entities.AdditionalActivityType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalActivityTypeRepo extends CrudRepository<AdditionalActivityType,Integer> {
}
