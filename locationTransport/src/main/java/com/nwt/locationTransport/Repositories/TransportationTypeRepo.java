package com.nwt.locationTransport.Repositories;

import com.nwt.locationTransport.Entities.TransportationType;
import org.springframework.data.repository.CrudRepository;

public interface TransportationTypeRepo extends CrudRepository<TransportationType,Integer> {
    TransportationType getById(Integer id);
}
