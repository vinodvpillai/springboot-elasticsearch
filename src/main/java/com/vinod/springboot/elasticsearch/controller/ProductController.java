package com.vinod.springboot.elasticsearch.controller;

import com.vinod.springboot.elasticsearch.document.ProductDocument;
import com.vinod.springboot.elasticsearch.service.IProductService;
import com.vinod.springboot.elasticsearch.util.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.vinod.springboot.elasticsearch.util.GlobalUtility.buildResponseForError;
import static com.vinod.springboot.elasticsearch.util.GlobalUtility.buildResponseForSuccess;

@RestController
@RequestMapping("/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping
    public ResponseEntity<Response> addNewProduct(@RequestBody ProductDocument productDocument) {
        log.trace("Request came to add new product with following details: {}", productDocument);
        ProductDocument persistedProductDocument =productService.add(productDocument);
        if(null!= persistedProductDocument) {
            return buildResponseForSuccess(HttpStatus.SC_OK,"Successfully added new product", persistedProductDocument);
        }
        return buildResponseForError(HttpStatus.SC_INTERNAL_SERVER_ERROR, String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR),"Unable to add the product.",null);
    }

    @GetMapping("/{title}")
    public ResponseEntity<Response> getProductsByTitle(@PathVariable("title") String title) {
        log.trace("Request came to get the product details having the title: {}", title);
        List<ProductDocument> productDocumentList =productService.findProductByTitle(title);
        return buildResponseForSuccess(HttpStatus.SC_OK,"Successfully fetched products", productDocumentList);
    }
}
