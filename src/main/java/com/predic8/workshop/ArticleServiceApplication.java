package com.predic8.workshop;

import com.predic8.workshop.event.ArticleEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@EnableKafka
@SpringBootApplication
public class ArticleServiceApplication {
	private final KafkaProperties kafkaProperties;

	@Bean
	public Map<String, Object> configs() {
		HashMap<String, Object> configs = new HashMap<>();

		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "articles");
		configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		return configs;
	}

	@Bean
	public ConsumerFactory<String, ArticleEvent> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(configs(), null, new JsonDeserializer<>(ArticleEvent.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ArticleEvent> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, ArticleEvent> factory =
			new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());

		return factory;
	}

	public static void main(String[] args) {
		SpringApplication.run(ArticleServiceApplication.class, args);
	}
}