package etf.unsa.ba.user_management;

import etf.unsa.ba.user_management.model.entity.RoleEntity;
import etf.unsa.ba.user_management.model.entity.UserEntity;
import etf.unsa.ba.user_management.repository.RoleRepository;
import etf.unsa.ba.user_management.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class UserManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner insertData(UserRepository userRepository, RoleRepository roleRepository) {
        return (args) -> {
            roleRepository.save(new RoleEntity(0, "ADMIN", "User with admin privileges"));
            roleRepository.save(new RoleEntity(0, "EMPLOYEE", null));

            for (RoleEntity role : roleRepository.findAll()) {
                userRepository.save(new UserEntity(0, "lejla", "solak", "lsolak", "lsolak", role));
            }
        };
    }
}
