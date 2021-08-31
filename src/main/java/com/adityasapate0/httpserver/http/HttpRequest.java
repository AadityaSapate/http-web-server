package com.adityasapate0.httpserver.http;

public class HttpRequest extends HttpMessage {
    private HttpMethod method;
    private String requestTarget;
    private String httpVersion;

    HttpRequest(){
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(String method) throws HttpParsingException {
        for (HttpMethod method1 : HttpMethod.values())
        {
            if(method.equals(method1.name())){
                this.method = HttpMethod.valueOf(method);
                return;
            }
        }
        throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
    }

    public String getRequestTarget() {
        return requestTarget;
    }

    public void setRequestTarget(String requestTarget) {
        this.requestTarget = requestTarget;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }
}
