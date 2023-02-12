package com.peer.testapp.service.extractor;

import java.io.IOException;

public interface Extractor {
    String getText(String url) throws IOException;
}
