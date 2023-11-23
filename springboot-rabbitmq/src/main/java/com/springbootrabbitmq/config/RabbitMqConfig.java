package com.springbootrabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
	@Value("${rabbitmq.exchange.name1}")
	private String exchange;
	
	@Value("${rabbitmq.queue.name1}")
	private String queue;
	
	@Value("${rabbitmq.routingkey.name1}")
	private String routingkey;
	
	@Value("${rabbitmq.queue.jsonname1}")
	private String jsonQueue;
	
	@Value("${rabbitmq.routingkey.jsonname1}")
	private String jsonRoutingkey;


	@Bean
	public Queue queue1() {
		return new Queue(queue);
	}
	
	@Bean
	public TopicExchange exchange1() {
		return new TopicExchange(exchange);
	}
	
	@Bean
	public Binding binding() {
		return BindingBuilder
				.bind(queue1())
				.to(exchange1())
				.with(routingkey);
				
	}
	
	//connectionFactory, RabbitTemplate and RabbitAdmin beans are auto-configured by spring boot.
	
	//handling json message
	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonQueue);
	}
	
	@Bean
	public Binding jsonBinding() {
		return BindingBuilder
				.bind(jsonQueue())
				.to(exchange1())
				.with(jsonRoutingkey);			
	}
	
	//required converter for json message
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate template(ConnectionFactory factory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
}
