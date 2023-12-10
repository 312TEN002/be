package com.be.poten.controller;

import com.be.poten.service.CrawlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/crawler")
public class CrawlerController {

    private final CrawlerService crawlerService;

    @GetMapping(value = "/news")
    public ResponseEntity<?> getCrawlerNewsData() {
        crawlerService.newDataCrawling();
        return ResponseEntity.ok().build();
    }
}
