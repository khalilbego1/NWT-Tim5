package etf.unsa.ba.user_management.configuration;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Value("${fanout.exchange}")
    private String fanoutExchange;

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(fanoutExchange);
    }
}
