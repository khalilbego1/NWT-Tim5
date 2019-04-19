package com.nwt.locationTransport.Repositories;

import com.nwt.locationTransport.Entities.Country;
import com.nwt.locationTransport.Entities.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RegionRepo extends CrudRepository<Region,Integer> {
    @Query("SELECT r from Region r where r.country=:ct")
    Iterable<Region>findRegionsByCountry(@Param("ct") Country country);
}
