package nwt.microservice.arrangements;

import nwt.microservice.arrangements.Entities.Arrangement;
import nwt.microservice.arrangements.Repositories.ArrangementRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ArrangementsApplication {

    private static final Logger log = LoggerFactory.getLogger(ArrangementsApplication.class);

//    public static void main(String[] args) {
//        SpringApplication.run(ArrangementsApplication.class, args);
//    }

    public static void main(String[] args) {
        SpringApplication.run(ArrangementsApplication.class);
    }

    @Bean
    public CommandLineRunner demo(ArrangementRepo repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Arrangement());

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Arrangement arrangement : repository.findAll()) {
                log.info(arrangement.toString());
            }
            log.info("");

        };
    }
}
