package com.ktech.trail.kafka;

import com.ktech.trail.core.ports.repositories.ArtistSavedEventPublisher;
import com.ktech.trail.kafka.events.avro.ArtistSavedEvent;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
@EnableConfigurationProperties(KafkaAdapterProperties.class)
public class KafkaAdapterConfig {
  //    PRODUCER
  @Bean
  public DefaultKafkaProducerFactory<String, ArtistSavedEvent> kafkaProducerFactory(
      KafkaProperties properties) {
    Map<String, Object> producerProperties = properties.buildProducerProperties();
    producerProperties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
    producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
    return new DefaultKafkaProducerFactory(producerProperties);
  }

  @Bean
  public KafkaTemplate<String, ArtistSavedEvent> kafkaTemplate(
      ProducerFactory<String, ArtistSavedEvent> kafkaProducerFactory) {
    return new KafkaTemplate<>(kafkaProducerFactory);
  }

  @Bean
  public ArtistSavedEventPublisher artistSavedEventPublisher(
      KafkaTemplate<String, ArtistSavedEvent> kafkaTemplate, KafkaAdapterProperties props) {
    kafkaTemplate.setDefaultTopic("artist");
    return new DefaultArtistSavedEventPublisher(kafkaTemplate, props);
  }

  // CONSUMER
  @Bean
  public ConsumerFactory<String, ArtistSavedEvent> kafkaConsumerFactory(
      KafkaProperties properties) {
    Map<String, Object> consumerProperties = properties.buildConsumerProperties();
    consumerProperties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
    consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
    return new DefaultKafkaConsumerFactory(consumerProperties);
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ArtistSavedEvent>>
      kafkaListenerContainerFactory(ConsumerFactory<String, ArtistSavedEvent> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<String, ArtistSavedEvent> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);

    return factory;
  }

  @Bean
  public DefaultArtistSavedEventReceiver artistSavedEventReceiver() {
    return new DefaultArtistSavedEventReceiver();
  }
}
