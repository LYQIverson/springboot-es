package com.liuyq;

import com.liuyq.server.EsApplication;
import com.liuyq.server.domain.Book;
import com.liuyq.server.repository.ESBookRepository;
import java.util.Date;
import java.util.List;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liuyq
 * @date 2024/03/26 13:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsApplication.class)
public class TestEsApplication {

  @Autowired private ElasticsearchRestTemplate elasticsearchRestTemplate;
  @Autowired private ESBookRepository repository;

  @Test
  public void test() {
    // 创建单个索引
    IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(Book.class);
    indexOperations.create();
    indexOperations.putMapping(indexOperations.createMapping());
  }

  @Test
  public void test1() {
    // 3、创建单个索引库,并往里面来一条文档数据
    Book book =
        Book.builder()
            .id("2")
            .author("liuyq")
            .price(1.23)
            .title("第一个")
            .createTime(new Date())
            .updateTime(new Date())
            .build();
    IndexQuery indexQuery =
        new IndexQueryBuilder().withId(book.getId() + "").withObject(book).build();
    elasticsearchRestTemplate.index(indexQuery, IndexCoordinates.of("blog2", "blog3"));
  }

  @Test
  public void test2() {
    Book book =
        Book.builder()
            .id("2")
            .author("liuyq")
            .price(1.23)
            .title("第一个")
            .createTime(new Date())
            .updateTime(new Date())
            .build();
    repository.save(book);
  }

  @Test
  public void test7() {
    long startTime = System.currentTimeMillis();
    // 查询条件
    MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "111");
    // 字段排序
    FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("id");
    // 分页查询
    Pageable pageable = PageRequest.of(0, 10);
    // 高亮显示
    HighlightBuilder.Field field =
        new HighlightBuilder.Field("title").preTags("<span style='color:red'>").postTags("</span>");
    NativeSearchQuery nativeSearchQuery =
        new NativeSearchQueryBuilder()
            .withQuery(matchQueryBuilder)
            .withSort(fieldSortBuilder)
            .withPageable(pageable)
            .withHighlightFields(field)
            .build(); // 可以指定多个字段高亮
    SearchHits<Book> hits =
        elasticsearchRestTemplate.search(
            nativeSearchQuery, Book.class, IndexCoordinates.of("blog1"));
    if (hits.getTotalHits() > 0) {
      List<SearchHit<Book>> searchHits = hits.getSearchHits();
      for (SearchHit<Book> searchHit : searchHits) {
        System.out.println(searchHit.getContent());
        System.out.println(searchHit.getHighlightFields());
      }
    }
    long endTime = System.currentTimeMillis();
    System.out.println("总耗时：" + (endTime - startTime));
  }
}
