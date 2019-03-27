package etf.unsa.ba.user_management_service.service;

import etf.unsa.ba.user_management_service.model.Role;
import etf.unsa.ba.user_management_service.model.User;
import etf.unsa.ba.user_management_service.model.entity.RoleEntity;
import etf.unsa.ba.user_management_service.model.entity.UserEntity;
import etf.unsa.ba.user_management_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDataService {
    private UserRepository userRepository;

    @Autowired
    public UserDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Integer Id) {
        if (userRepository.findById(Id).isPresent()) {
            UserEntity userEntity = userRepository.findById(Id).get();
            return new User(
                    userEntity.getFirstName(),
                    userEntity.getLastName(),
                    userEntity.getUsername(),
                    userEntity.getPassword(),
                    new Role(
                            userEntity.getRole().getName(),
                            userEntity.getRole().getDescription()
                    ));
        }
        return null;
    }

    public void saveUser(User user) {
        userRepository.save(new UserEntity(
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getPassword(),
                new RoleEntity(
                        user.getRole().getName(),
                        user.getRole().getDescription()
                ))
        );
    }

    public static void main(String... args) {

    }
}
