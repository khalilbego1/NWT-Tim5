package com.nwt.locationTransport.Repositories;

import com.nwt.locationTransport.Entities.Transportation;
import org.springframework.data.repository.CrudRepository;

public interface TransportationRepo extends CrudRepository<Transportation,Integer> {
}
