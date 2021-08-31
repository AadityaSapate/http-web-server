package com.adityasapate0.httpserver.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest {
    private  HttpParser httpParser;

    @BeforeAll
    public void beforeClass()
    {
        httpParser = new HttpParser();
    }

    @Test
    void parseHttpRequest() throws IOException {
        try {
           httpParser.parseHttpRequest(generateGoodStream());
        } catch (HttpParsingException e) {
           System.out.println(e.getStatusCode());
        }
    }

    private InputStream generateGoodStream(){

        String raw  = "GETE / HTTP/1.1\r\n";

        InputStream inputStream = new ByteArrayInputStream(raw.getBytes(StandardCharsets.US_ASCII));

        return inputStream;
    }
}
