package com.example.urlshortener.controller;

import com.example.urlshortener.service.UrlShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
public class UrlShorteningController {

    @Autowired
    private UrlShorteningService urlShorteningService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl) {
        try {
            String shortKey = urlShorteningService.shortenUrl(originalUrl);
            return new ResponseEntity<>(shortKey, HttpStatus.OK);
        } catch (NoSuchAlgorithmException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{shortKey}")
    public RedirectView redirectToOriginalUrl(@PathVariable String shortKey) {
        String originalUrl = urlShorteningService.getOriginalUrl(shortKey);
        if (originalUrl != null) {
            return new RedirectView(originalUrl);
        } else {
            return new RedirectView("/error");
        }
    }
}
