package com.springbootrabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);

	@Value("${rabbitmq.exchange.name1}")
	private String exchange;
	
	@Value("${rabbitmq.routingkey.name1}")
	private String routingkey;
	
	private RabbitTemplate rabbitTemplate;

	
	
	public RabbitMqProducer(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}



	public void sendMessage(String message) {
		LOGGER.info(String.format("Message sent -> %s", message));
		rabbitTemplate.convertAndSend(exchange, routingkey, message);
	}
}
