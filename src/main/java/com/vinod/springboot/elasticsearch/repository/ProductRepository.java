package com.vinod.springboot.elasticsearch.repository;

import com.vinod.springboot.elasticsearch.document.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<ProductDocument, String> {

    List<ProductDocument> findByTitle(String title);

    List<ProductDocument> findByStatus(Boolean status);
}
