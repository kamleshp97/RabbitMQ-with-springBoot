package com.springbootrabbitmq.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootrabbitmq.dto.User;
import com.springbootrabbitmq.publisher.RabbitMqJsonProducer;

@RestController
@RequestMapping(value = "/rabbitmq/v2")
public class JsonController {

	private RabbitMqJsonProducer jsonProducer;

	public JsonController(RabbitMqJsonProducer jsonProducer) {
		super();
		this.jsonProducer = jsonProducer;
	}
	
	@PostMapping("/jsonPublish")
	public ResponseEntity<String> publishJsonMessage(@RequestBody User user){
		jsonProducer.publishJson(user);
		return ResponseEntity.ok("JSON message sent successfully!!");
	}
}
