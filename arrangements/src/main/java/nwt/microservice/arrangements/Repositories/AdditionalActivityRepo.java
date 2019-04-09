package nwt.microservice.arrangements.Repositories;

import nwt.microservice.arrangements.Entities.AdditionalActivity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalActivityRepo extends CrudRepository<AdditionalActivity,Integer> {
}
