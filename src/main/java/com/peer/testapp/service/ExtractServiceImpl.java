package com.peer.testapp.service;

import com.peer.testapp.controller.exception.Error;
import com.peer.testapp.entity.Result;
import com.peer.testapp.service.extractor.Extractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import java.util.stream.Collectors;

@Slf4j
@Service
public class ExtractServiceImpl implements ExtractService{
    private Extractor extractor;

    @Autowired
    public ExtractServiceImpl(Extractor extractor) {
        this.extractor = extractor;
    }

    @Override
    public List<Result> extractAsync(Set<String> urls) {

        List<CompletableFuture<Result>> results = urls.stream()
                .map(url -> CompletableFuture.supplyAsync(
                        () ->
                                //EK: it is better to add here the cache not to extract same URL few times BUT we need to discuss how to update it as content
                                // could be changed for same url (example: News, wiki ect)
                             getResult(url)
                        ))
                .collect(Collectors.toList());

        return results.stream().map(CompletableFuture::join).collect(Collectors.toList());

    }

    private Result getResult(String url) {
        Result.ResultBuilder resultBuilder = Result
                .builder()
                .url(url);

        try {
            String text  = extractor.getText(url);
            return resultBuilder
                    .text(text)
                    .build();

        } catch (IOException e) {

            return resultBuilder
                    //EK: we need to validate status not to hardcode
                    .error(new Error(HttpStatus.NOT_FOUND, e.getMessage()))
                    .build();

        }
    }
}
