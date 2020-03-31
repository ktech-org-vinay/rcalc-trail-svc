package com.ktech.trail.couchdb;

import java.net.URL;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

public class CouchDbFactory {
  private CouchDbFactory() {

  }

  public enum DbCreationMode { CREATE_IF_NOT_EXIST, DEFAULT }

  public static CouchDbConnector createConnector(
      URL url, String databaseName, DbCreationMode mode) {
    HttpClient httpClient = new StdHttpClient.Builder().url(url).build();
    CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
    CouchDbConnector db = new StdCouchDbConnector(databaseName, dbInstance);

    conditionallyCreateDB(mode, db);

    return db;
  }

  private static void conditionallyCreateDB(DbCreationMode mode, CouchDbConnector db) {
    if (mode == DbCreationMode.CREATE_IF_NOT_EXIST) {
      db.createDatabaseIfNotExists();
    }
  }
}
