package com.vinod.springboot.elasticsearch.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products")
@Setting(settingPath = "/es/analyser.json")
public class ProductDocument {
    @Id
    private String productId;
    @Field(type = FieldType.Text, analyzer = "autocomplete")
    private String title;
    private String description;
    private Integer quantity;
    private BigDecimal price;
    private Boolean status;
}
