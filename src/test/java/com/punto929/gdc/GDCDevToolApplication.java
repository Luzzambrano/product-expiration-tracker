package com.punto929.gdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

@Configuration
public class GDCDevToolApplication {

  @Bean
  @RestartScope
  @ServiceConnection
  PostgreSQLContainer postresSQLContainer(){
    return new PostgreSQLContainer("postgres:16-alpine");
  }

  public static void main(String[] args) {
    SpringApplication
        .from(GDCApplication::main)
        .with(GDCDevToolApplication.class)
        .run(args);
  }
}
