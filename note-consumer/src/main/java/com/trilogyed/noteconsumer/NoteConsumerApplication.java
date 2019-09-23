package com.trilogyed.noteconsumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class NoteConsumerApplication {
	// DEFINE TOPIC EXCHANGE NAME FOR COMMUNICATION
	public static final String TOPIC_EXCHANGE_NAME = "note-exchange";
	// DEFINE NAME OF QUEUE THAT THE APPLICATION WILL BE USING
	public static final String QUEUE_NAME = "note-queue";
	// DEFINE ROUTING KEY THAT IS MANDATORY FOR ALL TRANSACTIONS
	public static final String ROUTING_KEY = "note.#";

	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(TOPIC_EXCHANGE_NAME);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	public static void main(String[] args) {
		SpringApplication.run(NoteConsumerApplication.class, args);
	}

}
