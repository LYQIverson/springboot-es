//package com.liuyq.server.config;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//
///**
// * @author liuyq
// * @date 2024/03/26 11:53
// */
//@Configuration
//public class RestClientConfig extends AbstractElasticsearchConfiguration {
//  @Override
//  @Bean
//  public RestHighLevelClient elasticsearchClient() {
//    final ClientConfiguration clientConfiguration =
//        ClientConfiguration.builder().connectedTo("localhost:9200").build();
//    return RestClients.create(clientConfiguration).rest();
//  }
//}
