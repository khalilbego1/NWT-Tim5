package nwt.microservice.arrangements.configuration;

import nwt.microservice.arrangements.Services.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    private static final String LISTENER_METHOD1 = "receiveMessage1";
    private static final String LISTENER_METHOD2 = "receiveMessage2";
    private final String userQueueName = "user-queue";
    private final String locationQueueName = "location-queue";
    @Value("${fanout.exchange1}")
    private String fanoutExchange1;
    @Value("${fanout.exchange2}")
    private String fanoutExchange2;

    @Bean
    Queue queue1() {
        return new Queue(userQueueName, true);
    }

    @Bean
    FanoutExchange exchange1() {
        return new FanoutExchange(fanoutExchange1);
    }

    @Bean
    Binding binding1(Queue queue1, FanoutExchange exchange1) {
        return BindingBuilder
                .bind(queue1)
                .to(exchange1);
    }

    @Bean
    SimpleMessageListenerContainer container1(ConnectionFactory connectionFactory,
                                              MessageListenerAdapter listenerAdapter1) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(userQueueName);
        container.setMessageListener(listenerAdapter1);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter1(Receiver receiver) {
        return new MessageListenerAdapter(receiver, LISTENER_METHOD1);
    }

    @Bean
    Queue queue2() {
        return new Queue(locationQueueName, true);
    }

    @Bean
    FanoutExchange exchange2() {
        return new FanoutExchange(fanoutExchange1);
    }

    @Bean
    Binding binding2(Queue queue2, FanoutExchange exchange2) {
        return BindingBuilder
                .bind(queue2)
                .to(exchange2);
    }

    @Bean
    SimpleMessageListenerContainer container2(ConnectionFactory connectionFactory,
                                              MessageListenerAdapter listenerAdapter2) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(locationQueueName);
        container.setMessageListener(listenerAdapter2);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter2(Receiver receiver) {
        return new MessageListenerAdapter(receiver, LISTENER_METHOD2);
    }

}
