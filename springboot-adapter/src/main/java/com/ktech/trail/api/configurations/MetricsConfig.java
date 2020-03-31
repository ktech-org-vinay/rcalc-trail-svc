package com.ktech.trail.api.configurations;

import io.micrometer.core.instrument.MeterRegistry;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class MetricsConfig {

  private static final Logger LOGGER = LoggerFactory.getLogger(MetricsConfig.class);

  @Value("${app.systemCode}")
  private String systemCode;

  @Value("${app.environment}")
  private String environment;

  @Value("${app.awsRegion}")
  private String awsRegion;

  @Bean
  MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
    return registry -> registry.config().commonTags(
        "service", systemCode,
        "env", environment,
        "region", awsRegion,
        "host", getHost());
  }

  private String getHost() {
    try {
      return InetAddress.getLocalHost()
          .getHostName();
    } catch (UnknownHostException e) {
      LOGGER.warn("Unable to get machine hostname", e);
      return "Unknown Host";
    }
  }
}
