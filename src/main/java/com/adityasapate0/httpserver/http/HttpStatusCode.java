package com.adityasapate0.httpserver.http;

public enum HttpStatusCode {

    CLIENT_400_BAD_REQUEST(400, "Bad Request"),
    CLIENT_401_METHOD_NOT_ALLOWED(401, "Method Not Allowed"),
    CLIENT_414_BAD_REQUEST(414, "URI Too Long"),
    SERVER_ERROR_500_INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    SERVER_ERROR_501_NOT_IMPLEMENTED(501, "Not Implemented");

    public final int STATUS_CODE;
    public final String MESSAGE;

    HttpStatusCode(int STATUS_CODE, String MESSAGE)
    {
        this.MESSAGE =  MESSAGE;
        this.STATUS_CODE = STATUS_CODE;
    }
}
