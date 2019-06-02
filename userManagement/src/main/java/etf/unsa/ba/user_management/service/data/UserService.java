package etf.unsa.ba.user_management.service.data;

import etf.unsa.ba.user_management.jwt.JWTToken;
import etf.unsa.ba.user_management.jwt.exception.InvalidTokenException;
import etf.unsa.ba.user_management.jwt.impl.DefaultJWTProvider;
import etf.unsa.ba.user_management.model.entity.RoleEntity;
import etf.unsa.ba.user_management.model.entity.UserEntity;
import etf.unsa.ba.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DefaultJWTProvider jwtProvider;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, DefaultJWTProvider jwtProvider) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public UserEntity getByToken(String token) {
        try {
            JWTToken jwtToken = jwtProvider.decode(token);
            return getById(jwtToken.getId());
        } catch (InvalidTokenException e) {
            return null;
        }
    }

    public UserEntity getById(Integer Id) {
        Optional<UserEntity> userEntity = userRepository.findById(Id);
        return userEntity.orElse(null);
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
