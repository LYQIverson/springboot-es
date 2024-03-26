package com.liuyq.server.repository;

import com.liuyq.server.domain.Book;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author liuyq
 * @date 2024/03/26 13:10
 */
@Repository
public interface ESBookRepository extends ElasticsearchRepository<Book, String> {

  List<Book> findByTitleOrAuthor(String title, String author);
}
