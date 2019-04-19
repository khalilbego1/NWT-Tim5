package com.nwt.locationTransport.Repositories;

import com.nwt.locationTransport.Entities.City;
import com.nwt.locationTransport.Entities.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CityRepo extends CrudRepository<City,Integer> {
    @Query("select c from City c where c.region=:rg")
    Iterable<City>findCitiesByRegion(@Param("rg") Region region);
}
