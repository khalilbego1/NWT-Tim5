package com.nwt.locationTransport.Repositories;

import com.nwt.locationTransport.Entities.Transportation;
import com.nwt.locationTransport.Entities.TransportationType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TransportationRepo extends CrudRepository<Transportation,Integer> {
    @Query("SELECT t from Transportation t where t.transportationType =:tp")
    Iterable<Transportation> findAllByTransportationType(@Param("tp") TransportationType transportationType);
}
