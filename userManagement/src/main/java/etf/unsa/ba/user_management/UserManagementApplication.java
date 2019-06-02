package etf.unsa.ba.user_management;

import etf.unsa.ba.user_management.model.entity.RoleEntity;
import etf.unsa.ba.user_management.model.entity.UserEntity;
import etf.unsa.ba.user_management.service.data.RoleService;
import etf.unsa.ba.user_management.service.data.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class UserManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner insertData(UserService userService, RoleService roleService) {
        return (args) -> {
            roleService.insert(new RoleEntity(0, RoleEntity.Type.PASSENGER, null, null));
            roleService.insert(new RoleEntity(0, RoleEntity.Type.EMPLOYEE, null, null));

            List<RoleEntity> roleEntities = roleService.getAll();
            for (int i = 0; i < roleEntities.size(); i++) {
                userService.insert(new UserEntity(0,
                        "Lejla",
                        "Solak",
                        "lsolak" + i,
                        "password123",
                        "lsolak" + i + "@etf.unsa.ba",
                        LocalDate.of(1996, 9, 15),
                        roleEntities.get(i)
                ));
            }
        };
    }
}