package com.vinod.springboot.elasticsearch.service.impl;

import com.vinod.springboot.elasticsearch.document.ProductDocument;
import com.vinod.springboot.elasticsearch.repository.ProductRepository;
import com.vinod.springboot.elasticsearch.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    /**
     * Add product to elastic search.
     *
     * @param productDocument   - Product object.
     * @return          - Product object.
     */
    @Override
    public ProductDocument add(ProductDocument productDocument) {
        log.trace("Requested to add product information to elastic search");
        return productRepository.save(productDocument);
    }

    /**
     * Get products base on the title.
     *
     * @param title - Product title
     * @return      - List of product object.
     */
    @Override
    public List<ProductDocument> findProductByTitle(String title) {
        log.trace("Requested to fetch all the product with title: {}",title);
        List<ProductDocument> productDocumentList =productRepository.findByTitle(title);
        //List<ProductDocument> productDocumentList =productRepository.findByStatus(true);
        if(!CollectionUtils.isEmpty(productDocumentList)) {
            log.trace("Total :{} products found having title as :{}", productDocumentList.size(), title);
        }
        return productDocumentList;
    }
}
