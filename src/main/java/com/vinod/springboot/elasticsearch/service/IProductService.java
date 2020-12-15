package com.vinod.springboot.elasticsearch.service;

import com.vinod.springboot.elasticsearch.document.ProductDocument;

import java.util.List;

public interface IProductService {

    /**
     * Add product to elastic search.
     *
     * @param productDocument   - Product object.
     * @return          - Product object.
     */
    ProductDocument add(ProductDocument productDocument);

    /**
     * Get products base on the title.
     *
     * @param title - Product title
     * @return      - List of product object.
     */
    List<ProductDocument> findProductByTitle(String title);
}
