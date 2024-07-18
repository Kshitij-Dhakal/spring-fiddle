package com.example.fiddle.logger;

import org.hibernate.Interceptor;
import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomSqlLogger implements Interceptor, StatementInspector {

  private static final Logger log = LoggerFactory.getLogger(CustomSqlLogger.class);

  private final String tableToLog = "charity_external_review";

  public void postCreate() {
    log.info("Will log queries for : {}", tableToLog);
  }

  @Override
  public String inspect(String sql) {
    if (sql.toLowerCase()
        .contains(tableToLog)) { // Replace "your_table_name" with the actual table name
      log.info("SQL : {}", sql);
    }
    return sql;
  }
}
