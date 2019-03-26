package etf.unsa.ba.user_management_service;

import etf.unsa.ba.user_management_service.model.Role;
import etf.unsa.ba.user_management_service.model.User;
import etf.unsa.ba.user_management_service.service.RoleDataService;
import etf.unsa.ba.user_management_service.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class UserManagementServiceApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(UserManagementServiceApplication.class, args);
    }

    @Autowired
    RoleDataService roleDataService;
    @Autowired
    UserDataService userDataService;

    @Override
    public void run(String... args) throws Exception {
        roleDataService.saveRole(new Role(
                "ADMIN",
                "User with admin privileges"
        ));
        userDataService.saveUser(new User(
                "test",
                "test",
                "test",
                "test",
                1
        ));
    }
}
