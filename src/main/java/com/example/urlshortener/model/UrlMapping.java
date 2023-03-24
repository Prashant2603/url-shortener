package com.example.urlshortener.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UrlMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String originalUrl;
    private String shortKey;

    public UrlMapping() {
    }

    public UrlMapping(String originalUrl, String shortKey) {
        this.originalUrl = originalUrl;
        this.shortKey = shortKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortKey() {
        return shortKey;
    }

    public void setShortKey(String shortKey) {
        this.shortKey = shortKey;
    }
}
