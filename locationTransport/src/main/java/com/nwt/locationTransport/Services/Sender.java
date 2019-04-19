package com.nwt.locationTransport.Services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    @Value("${fanout.exchange}")
    private String fanoutExchange;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public Sender(RabbitTemplate rabbitTemplate) {
        super();
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String routingKey, String message) {
        rabbitTemplate.convertAndSend(fanoutExchange, routingKey, message);
    }
}