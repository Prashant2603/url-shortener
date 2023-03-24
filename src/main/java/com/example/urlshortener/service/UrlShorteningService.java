package com.example.urlshortener.service;

import com.example.urlshortener.model.UrlMapping;
import com.example.urlshortener.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UrlShorteningService {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    public String shortenUrl(String originalUrl) throws NoSuchAlgorithmException {
        String shortKey = generateShortKey(originalUrl);
        UrlMapping urlMapping = new UrlMapping(originalUrl, shortKey);
        urlMappingRepository.save(urlMapping);
        return shortKey;
    }

    public String getOriginalUrl(String shortKey) {
        UrlMapping urlMapping = urlMappingRepository.findByShortKey(shortKey);
        return (urlMapping != null) ? urlMapping.getOriginalUrl() : null;
    }

    private String generateShortKey(String originalUrl) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(originalUrl.getBytes(StandardCharsets.UTF_8));
        StringBuilder shortKeyBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            shortKeyBuilder.append(Integer.toHexString(0xff & hash[i]));
        }
        return shortKeyBuilder.toString();
    }
}
