package nwt.microservice.arrangements.Repositories;

import nwt.microservice.arrangements.Entities.UserArrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserArrangementRepo extends JpaRepository<UserArrangement, Integer> {
    @Transactional
    @Modifying
    void deleteByUserId(Integer userId);
}
