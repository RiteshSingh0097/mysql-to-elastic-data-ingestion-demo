package com.learning.elastic.repository;

import com.learning.elastic.model.PincodeEsMaster;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PincodeEsRepository extends ElasticsearchRepository<PincodeEsMaster, String> {
}
