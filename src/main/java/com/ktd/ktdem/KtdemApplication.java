package com.ktd.ktdem;

// https://medium.com/lydtech-consulting/kafka-streams-spring-boot-demo-ff0e74e08c9c

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
@SpringBootApplication
public class KtdemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KtdemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		final Serde<String> stringSerde = Serdes.String();
		final Serde<Long> longSerde = Serdes.Long();
		final StreamsBuilder builder = new StreamsBuilder();

		KStream<String, String> views = builder.stream(
			"purchases",
			Consumed.with(stringSerde, stringSerde)
		); 

		
	}

}
