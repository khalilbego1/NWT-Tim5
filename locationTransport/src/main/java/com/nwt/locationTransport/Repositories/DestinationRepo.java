package com.nwt.locationTransport.Repositories;

import com.nwt.locationTransport.Entities.City;
import com.nwt.locationTransport.Entities.Destination;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sun.security.krb5.internal.crypto.Des;

public interface DestinationRepo extends CrudRepository<Destination,Integer> {
    @Query("SELECT d from Destination d where d.city =:ct")
    Iterable<Destination>findDestinationsByCity(@Param("ct") City city);
}
