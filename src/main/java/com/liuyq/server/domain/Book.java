package com.liuyq.server.domain;

import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author liuyq
 * @date 2024/03/26 13:08
 */

/*Spring Data通过注解来声明字段的映射属性，有下面的三个注解：
​
        - `@Document` 作用在类，标记实体类为文档对象，一般有四个属性
        - indexName：对应索引库名称
        - type：对应在索引库中的类型   在ElasticSearch7.x中取消了type的概念
        - shards：分片数量，默认5
        - replicas：副本数量，默认1
        - `@Id` 作用在成员变量，标记一个字段作为id主键
        - `@Field` 作用在成员变量，标记为文档的字段，并指定字段映射属性：
        - type：字段类型，取值是枚举：FieldType
        - index：是否设置分词，布尔类型，默认是true
        - store：是否存储，布尔类型，默认是false
        - analyzer：分词器名称：ik_max_word
        - createIndex 不创建默认是standard标准分词器索引库，否则会出现异常*/
@Data
@Document(indexName = "book")
@Builder
public class Book {
  @Id
  @Field(type = FieldType.Text)
  private String id;

  @Field(analyzer = "ik_max_word")
  private String title;

  @Field(analyzer = "ik_max_word")
  private String author;

  @Field(type = FieldType.Double)
  private Double price;

  @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
  private Date createTime;

  @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
  private Date updateTime;
}
