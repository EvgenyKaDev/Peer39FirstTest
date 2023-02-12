package com.peer.testapp;

import com.peer.testapp.controller.TextExtractorController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *  test
 */
@SpringBootTest
class Peer39FirstTestApplicationTests {
    @Autowired
    private TextExtractorController textExtractor;

    @Test
    public void contextLoads() throws Exception {
        assertThat(textExtractor).isNotNull();
    }

}
