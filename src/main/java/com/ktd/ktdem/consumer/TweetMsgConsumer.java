package com.ktd.ktdem.consumer;

import java.util.Arrays;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.apache.kafka.streams.KeyValue;


@Component
public class TweetMsgConsumer {

    private String inputTopic = "input-topic";
    private String outputTopic = "output-topic";

    @Bean
    public KStream<String, String> kStream(StreamsBuilder streamsBuilder) {

        KStream<String, String> stream = streamsBuilder.stream(
            this.inputTopic,
            Consumed.with(Serdes.String(), Serdes.String()));
        

            // kafka-console-producer --bootstrap-server localhost:9092 --topic input-topic
            // kafka-console-consumer --bootstrap-server localhost:9092 --topic output-topic --from-beginning

            // exemple with custom serdes
            // https://developer.confluent.io/tutorials/changing-serialization-format/kstreams.html
            // https://www.geeksforgeeks.org/spring-boot-consume-json-object-from-kafka-topics/
        
        stream
            .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
            .map( (key, value) -> {
                System.out.println("KEY : " + key   + " VALUE : " + value);
                return new KeyValue<>(value, value);
            })
            .to(this.outputTopic, Produced.with(Serdes.String(), Serdes.String()));
            // .to(this.outputTopic, Produced.with(Serdes.String(), Serdes.Long()));

        return stream;

        
    }
}
