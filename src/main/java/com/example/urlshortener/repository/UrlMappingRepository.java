package com.example.urlshortener.repository;

import com.example.urlshortener.model.UrlMapping;
import org.springframework.data.repository.CrudRepository;

public interface UrlMappingRepository extends CrudRepository<UrlMapping, Long> {
    UrlMapping findByShortKey(String shortKey);
}
