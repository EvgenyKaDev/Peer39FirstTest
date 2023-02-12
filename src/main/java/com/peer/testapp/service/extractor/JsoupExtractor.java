package com.peer.testapp.service.extractor;


import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Optional;


@Slf4j
@Service
public class JsoupExtractor implements Extractor {
    @Override
    public String getText(String url) throws IOException {
        log.info("Extracting  from URL {}", url);

        Connection conn = Jsoup.connect(url);
        Optional<Connection> connectionOptional = Optional.of(conn);
        try {
            return getTextFromDocument(connectionOptional.get().get());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw e;
        }
    }
    private String getTextFromDocument(Document document){

        return Optional.of(document)
                .map(value -> value.body().text())
                    .orElse("");
    }
}
