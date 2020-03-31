package com.ktech.trail.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("kafka-adapter")
public class KafkaAdapterProperties {
  private Publisher publisher;

  @Data
  public static class Publisher {
    private long syncSendTimeoutms;
  }
}
