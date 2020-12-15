package com.vinod.springboot.elasticsearch.config;

import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.vinod.springboot.elasticsearch.repository")
public class AWSCredentialsConfiguration {

    private String endpoint = "https://search-myelasticsearch-l54ondofpvrvtamfzojz3g4gzi.ap-south-1.es.amazonaws.com";
    private String region = "ap-south-1";
    @Autowired
    private Environment environment;

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        AWSStaticCredentialsProvider credentialsProvider=new AWSStaticCredentialsProvider(new BasicAWSCredentials(environment.getProperty("ACCESS_KEY"), environment.getProperty("SECRET_KEY")));
        AWS4Signer signer = new AWS4Signer();
        String serviceName = "es";
        signer.setServiceName(serviceName);
        signer.setRegionName(region);
        HttpRequestInterceptor interceptor = new AWSRequestSigningApacheInterceptor(serviceName, signer, credentialsProvider);
        return new RestHighLevelClient(RestClient.builder(HttpHost.create(endpoint)).setHttpClientConfigCallback(e -> e.addInterceptorLast(interceptor)));
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }
}
