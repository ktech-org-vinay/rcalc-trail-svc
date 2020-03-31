package com.ktech.trail.couchdb;

import static com.ktech.trail.couchdb.CouchDbFactory.DbCreationMode.CREATE_IF_NOT_EXIST;

import java.net.MalformedURLException;
import java.net.URL;
import org.assertj.core.api.Assertions;
import org.ektorp.CouchDbConnector;
import org.junit.jupiter.api.Test;

public class CouchDbFactoryIT {

  private static final String DB_URL = "http://localhost:5984";
  private static final String DB_NAME = "rcalc-trail-svcdb";

  @Test
  void givenValidParameters_whenFactoryIsInvoked_shouldCreateConnector()
      throws MalformedURLException {
    CouchDbConnector connector = CouchDbFactory.createConnector(new URL(DB_URL), DB_NAME, CREATE_IF_NOT_EXIST);

    Assertions.assertThat(connector).isNotNull();
  }
}
