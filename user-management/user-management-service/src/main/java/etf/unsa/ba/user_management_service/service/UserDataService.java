package etf.unsa.ba.user_management_service.service;

import etf.unsa.ba.user_management_service.model.entity.UserEntity;
import etf.unsa.ba.user_management_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataService {
    private UserRepository userRepository;

    @Autowired
    public UserDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    UserEntity getById(Integer Id) {
        if (userRepository.findById(Id).isPresent()) {
            return userRepository.findById(Id).get();
        }
        return null;
    }

    List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    UserEntity getForLogin(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    UserEntity insert(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity update(UserEntity user) {
        return userRepository.save(user);
    }

    void delete(UserEntity user) {
        userRepository.deleteById(user.getId());
    }
}
