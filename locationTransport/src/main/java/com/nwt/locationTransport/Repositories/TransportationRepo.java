package com.nwt.locationTransport.Repositories;

import com.nwt.locationTransport.Entities.Transportation;
import com.nwt.locationTransport.Entities.TransportationType;
import org.springframework.data.repository.CrudRepository;

public interface TransportationRepo extends CrudRepository<Transportation,Integer> {
    Iterable<Transportation> findAllByTransportationType(TransportationType transportationType);
}
