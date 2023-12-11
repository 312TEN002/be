package com.be.poten.controller;

import com.be.poten.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/test")
public class TestController {

    private final TestService testService;

    @GetMapping(value = "/data")
    public ResponseEntity<?> getTestData() {
        HashMap<String, String> result = new HashMap<>();
        result.put("result", testService.getTestData());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/clova")
    public ResponseEntity<?> getClovaData() {
        HashMap<String, String> result = new HashMap<>();
        result.put("result", testService.postTest());
        return ResponseEntity.ok().body(result);
    }
}