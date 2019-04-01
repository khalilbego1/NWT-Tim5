package etf.unsa.ba.user_management;

import etf.unsa.ba.user_management.model.entity.RoleEntity;
import etf.unsa.ba.user_management.model.entity.UserEntity;
import etf.unsa.ba.user_management.repository.RoleRepository;
import etf.unsa.ba.user_management.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

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
//            roleRepository.save(new RoleEntity(0, RoleEntity.Type.ADMIN, "User with admin privileges", null));
//            roleRepository.save(new RoleEntity(0, RoleEntity.Type.EMPLOYEE, null, null));
//
//            for (RoleEntity role : roleRepository.findAll()) {
//                userRepository.save(new UserEntity(0, "Lejla", "Solak", "lsolak", "lsolak123", role));
//            }
        };
    }

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource);
        return validatorFactoryBean;
    }
}
