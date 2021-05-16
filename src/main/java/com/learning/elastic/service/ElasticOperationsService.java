package com.learning.elastic.service;

import java.util.Set;

public interface ElasticOperationsService {

    Set<String> findByCity(String city);
}
