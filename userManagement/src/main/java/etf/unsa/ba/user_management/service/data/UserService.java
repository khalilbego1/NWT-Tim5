package etf.unsa.ba.user_management.service.data;

import etf.unsa.ba.user_management.model.entity.RoleEntity;
import etf.unsa.ba.user_management.model.entity.UserEntity;
import etf.unsa.ba.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserEntity getById(Integer Id) {
        if (userRepository.findById(Id).isPresent()) {
            return userRepository.findById(Id).get();
        }
        return null;
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserEntity> getUsersForRoleId(RoleEntity role) {
        return userRepository.findUsersByRole(role);
    }

    public UserEntity insert(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserEntity update(UserEntity user) {
        return userRepository.save(user);
    }

    public void delete(UserEntity user) {
        userRepository.deleteById(user.getId());
    }
}
