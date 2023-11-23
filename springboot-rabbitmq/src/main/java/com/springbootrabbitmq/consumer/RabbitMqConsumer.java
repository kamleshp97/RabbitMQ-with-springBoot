package com.springbootrabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConsumer.class);
	
  @RabbitListener(queues = {"${rabbitmq.queue.name1}"}) 
  public void consume(String message) {
	  LOGGER.info(String.format("Message received -> %s",message));
  }
	 

}
