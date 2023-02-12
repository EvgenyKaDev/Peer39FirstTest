package com.peer.testapp;



import com.peer.testapp.controller.TextExtractorController;
import com.peer.testapp.entity.Result;
import com.peer.testapp.service.ExtractService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Controller layer test
 */

@WebMvcTest(TextExtractorController.class)
public class Peer39FirstTestApplicationAppTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExtractService extractService;

    private Result result;
    @BeforeEach
    void init() {
        result = new Result();
        result.setUrl("https://www.newstalkzb.co.nz/news/sport");
        result.setText("Choose Region Search Search Text Home SHOWS Back SHOWS All SHOWS ");

    }


    @Test
    void performPostRequest() throws Exception {

        when(extractService.extractAsync(anySet())).thenReturn(List.of(result));

        this.mockMvc.perform(post("/api/text/extractor")
                        .content("[\"https://www.newstalkzb.co.nz/news/sport\"]")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                     .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].url").value("https://www.newstalkzb.co.nz/news/sport"))
                .andExpect(jsonPath("$[0].text").value("Choose Region Search Search Text Home SHOWS Back SHOWS All SHOWS ")) ;
        verify(extractService,times(1)).extractAsync(anySet());

    }

    @Test
    void performPostBadRequest() throws Exception {

        this.mockMvc.perform(post("/api/text/extractor")
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(extractService,never()).extractAsync(anySet());

    }

    @Test
    void performPostWrongContentRequest() throws Exception {

        this.mockMvc.perform(post("/api/text/extractor")
                        .content("asd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(extractService,never()).extractAsync(anySet());

    }

    @Test
    void performPostNotFoundUrlRequest() throws Exception {

        this.mockMvc.perform(post("/api/text/urlXX")
                        .content("[\"asd\"]")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(extractService,never()).extractAsync(anySet());

    }

    @Test
    void performPostBadWithMessageRequest() throws Exception {

        when(extractService.extractAsync(anySet())).thenReturn(List.of(result));

        this.mockMvc.perform(post("/api/text/extractor")
                        .content("[\"aaaaa\"]")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("extractText.urlPath: The URL string does not comply with the rules. for URL aaaaa")) ;

    }
}
