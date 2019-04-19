package com.nwt.locationTransport.Repositories;

import com.nwt.locationTransport.Entities.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepo extends CrudRepository<Country,Integer> {
}
