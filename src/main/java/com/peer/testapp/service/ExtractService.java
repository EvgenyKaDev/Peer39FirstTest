package com.peer.testapp.service;

import com.peer.testapp.entity.Result;

import java.util.List;
import java.util.Set;

public interface ExtractService {
    List<Result> extractAsync(Set<String> url);
}
