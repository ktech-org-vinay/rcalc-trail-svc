package com.ktech.trail.api.configurations;

import static com.ktech.trail.couchdb.CouchDbFactory.DbCreationMode.CREATE_IF_NOT_EXIST;

import com.ktech.trail.couchdb.CouchDbFactory;
import java.net.MalformedURLException;
import java.net.URL;
import org.ektorp.CouchDbConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class DataSourceConfig {

  @Value("${db.url}")
  private String url;

  @Value("${db.name}")
  private String dbName;

  @Bean
  public CouchDbConnector couchDbConnector() throws MalformedURLException {
    return CouchDbFactory.createConnector(new URL(url), dbName, CREATE_IF_NOT_EXIST);
  }
}
