package nwt.microservice.arrangements.Repositories;

import nwt.microservice.arrangements.Entities.Arrangement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrangementRepo extends CrudRepository<Arrangement,Integer> {
    @Query("delete from Arrangement a where a.DestinationID=:id")
    void deleteByDestinationID(@Param("id") Integer id);
}
