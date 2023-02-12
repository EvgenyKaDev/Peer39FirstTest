package com.peer.testapp.validator.annotations;

import lombok.extern.slf4j.Slf4j;
import java.net.URL;


@Slf4j
public class UrlValidator {
    public static boolean validate(String url) {
        try {
            log.info("Validation started for URL {} ", url);
            new URL(url).toURI();

        } catch (Exception e) {
            log.error("URL is not valid {} ", url);
            return false;
        }

        return true;
    }
}
