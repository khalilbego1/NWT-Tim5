package etf.unsa.ba.user_management_web;

import etf.unsa.ba.user_management_service.config.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackageClasses = ServiceConfig.class)
@EnableJpaRepositories(basePackageClasses = ServiceConfig.class)
@EntityScan(basePackageClasses = ServiceConfig.class)
public class UserManagementWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserManagementWebApplication.class, args);
    }
}