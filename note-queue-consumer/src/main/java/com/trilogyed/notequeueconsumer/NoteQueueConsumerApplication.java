package com.trilogyed.notequeueconsumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Note-Queue-Consumer
 * Purpose: To provide configuration for the queue system.
 * Needed Annotations:
 * @EnableFeignClients - must use feign client to reach out to other service
 * @EnableDiscoveryClient - must register with eureka, spring.application.name=note-queue
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class NoteQueueConsumerApplication {
	public static final String TOPIC_EXCHANGE_NAME = "note-exchange";
	public static final String QUEUE_NAME = "note-queue";
	public static final String ROUTING_KEY = "note.#";

	// Creating a Queue Object that is needed to perform queues (will encapsulate Queue name)
	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}

	// Will instantiate the Topic Exchange Object
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(TOPIC_EXCHANGE_NAME);
	}

	// Binding the queue and exchange together  WITH the routing key for cohesive use
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

	// Finally, defining the Jackson converter we will need to convert NoteEntry object json
	// Producer(Book service) calls convertAndSend --> Consumer(NoteQueueConsumer) receives as Json for use.
	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	public static void main(String[] args) {
		SpringApplication.run(NoteQueueConsumerApplication.class, args);
	}

}
