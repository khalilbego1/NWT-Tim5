package nwt.microservice.arrangements.Repositories;

import nwt.microservice.arrangements.Entities.UserArrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserArrangementRepo extends JpaRepository<UserArrangement, Integer> {
    void deleteByUserId(Integer userId);
}
