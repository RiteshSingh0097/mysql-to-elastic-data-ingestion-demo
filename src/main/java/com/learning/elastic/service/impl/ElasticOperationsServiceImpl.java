package com.learning.elastic.service.impl;

import com.learning.elastic.service.ElasticOperationsService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ElasticOperationsServiceImpl implements ElasticOperationsService {

    @Autowired
    private RestHighLevelClient client;

    @Override
    public Set<String> findByCity(String city) {
        log.info("city Name :: " + city);
        try {
            SearchHits searchHits = getSearchHits(QueryBuilders.termQuery("zipcode.keyword", city),
                    "zipcode", null, "pincode_master");
            return Arrays.stream(searchHits.getHits()).map(searchHit ->
                    searchHit.getSourceAsMap().get("zipcode").toString()).collect(Collectors.toSet());
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>>", e);
        }
        return Collections.emptySet();
    }

    private SearchHits getSearchHits(QueryBuilder query, String includeFields, String excludeFields, String indexName) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder
                .query(QueryBuilders.matchAllQuery())
                //.aggregation(AggregationBuilders.cardinality("state"))
                .fetchSource("state", excludeFields)
                .size(2000);
        searchRequest.source(searchSourceBuilder);
        return client.search(searchRequest, RequestOptions.DEFAULT).getHits();
    }
}
