package com.peer.testapp;

import com.peer.testapp.entity.Result;
import com.peer.testapp.service.ExtractService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

/**
 * Service layer test
 */
@ExtendWith(MockitoExtension.class)
public class Peer39FirstTestServiceAppTest {

    @Mock
    private ExtractService extractService;

    @Test
    public void extractAsyncCall(){
        Result result1 = new Result();
        result1.setUrl("www.google.com");
        result1.setText("This is google");
        Result result2 = new Result();
        result2.setUrl("www.bing.com");
        result2.setText("This is bing");
        when(extractService.extractAsync(anySet())).thenReturn(Arrays.asList(result1, result2));


        assertEquals(extractService.extractAsync(Set.of("qqq")).get(0).getUrl(), "www.google.com");
        assertEquals(extractService.extractAsync(Set.of("wwww")).get(1).getUrl(), "www.bing.com");
    }
}
