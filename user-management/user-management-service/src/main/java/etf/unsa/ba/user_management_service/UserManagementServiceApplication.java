package etf.unsa.ba.user_management_service;

import etf.unsa.ba.user_management_service.model.entity.RoleEntity;
import etf.unsa.ba.user_management_service.model.entity.UserEntity;
import etf.unsa.ba.user_management_service.repository.RoleRepository;
import etf.unsa.ba.user_management_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class UserManagementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserManagementServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner insertData(UserRepository userRepository, RoleRepository roleRepository) {
        return (args) -> {
            roleRepository.save(new RoleEntity("ADMIN", "User with admin privileges"));
            roleRepository.save(new RoleEntity("EMPLOYEE", null));

            for (RoleEntity role : roleRepository.findAll()) {
                userRepository.save(new UserEntity("lejla", "solak", "lsolak", "lsolak", role));
            }
        };
    }
}