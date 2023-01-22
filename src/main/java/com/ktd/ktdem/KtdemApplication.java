package com.ktd.ktdem;

// https://medium.com/lydtech-consulting/kafka-streams-spring-boot-demo-ff0e74e08c9c

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;
@SpringBootApplication
@EnableKafkaStreams
public class KtdemApplication {

	public static void main(String[] args) {
		SpringApplication.run(KtdemApplication.class, args);
	}

}
