package com.liuyq.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author liuyq
 * @date 2024/03/26 13:25
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableElasticsearchRepositories(basePackages = {"com.liuyq.server.repository"})
public class EsApplication {
  public static void main(String[] args) {
    SpringApplication.run(EsApplication.class, args);
  }
}
