package com.springbootrabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springbootrabbitmq.dto.User;

@Service
public class RabbitMqJsonConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqJsonConsumer.class);
	
	@RabbitListener(queues = "${rabbitmq.queue.jsonname1}")
	public void jsonConsume(User user) {
		LOGGER.info(String.format("Message Received -> %s", user.toString()));
	}
}
