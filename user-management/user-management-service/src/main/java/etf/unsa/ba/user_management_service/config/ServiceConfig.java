package etf.unsa.ba.user_management_service.config;

import etf.unsa.ba.user_management_service.repository.RoleRepository;
import etf.unsa.ba.user_management_service.repository.UserRepository;
import etf.unsa.ba.user_management_service.service.RoleDataService;
import etf.unsa.ba.user_management_service.service.UserDataService;
import etf.unsa.ba.user_management_service.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "etf.unsa.ba.user_management_service.repository")
@EntityScan(basePackages = "etf.unsa.ba.user_management_service.model.entity")
public class ServiceConfig {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public ServiceConfig(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Bean
    public UserManagementService userManagementService() {
        return new UserManagementService(userDataService(), roleDataService());
    }

    @Bean
    public UserDataService userDataService() {
        return new UserDataService(userRepository);
    }

    @Bean
    public RoleDataService roleDataService() {
        return new RoleDataService(roleRepository);
    }
}
