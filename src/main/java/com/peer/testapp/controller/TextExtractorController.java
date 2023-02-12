package com.peer.testapp.controller;

import com.peer.testapp.entity.Result;
import com.peer.testapp.service.ExtractService;
import com.peer.testapp.validator.annotations.ValidUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@Slf4j
@RestController
@Validated
@RequestMapping("/api/text")
public class TextExtractorController {
    private ExtractService extractService;

    @Autowired
    public TextExtractorController(ExtractService extractService) {
        this.extractService = extractService;
    }

    @PostMapping("/extractor")
    public ResponseEntity<List<Result>> extractText(final @ValidUrl @RequestBody Set<String> urlPath) {
        log.info("Got request for url, number of URLS is {}", urlPath.size());

        List<Result> result = extractService.extractAsync(urlPath);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
