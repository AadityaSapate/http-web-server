package com.adityasapate0.httpserver.http;

public class HttpParsingException extends Exception{
    private final HttpStatusCode httpStatusCode;

    public HttpParsingException(HttpStatusCode httpStatusCode) {
        super(httpStatusCode.MESSAGE);
        this.httpStatusCode = httpStatusCode;
    }

    public int getStatusCode(){
       return httpStatusCode.STATUS_CODE;
    }

}
