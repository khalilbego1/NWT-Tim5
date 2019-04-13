package etf.unsa.ba.user_management.repository;

import etf.unsa.ba.user_management.model.entity.RoleEntity;
import etf.unsa.ba.user_management.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findUsersByRole(RoleEntity role);

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);
}
