package com.adityasapate0.httpserver.http;

public enum HttpMethod {
    GET,HEAD,POST,PATCH,DELETE;

    public static final int MAX_LENGTH;

   static {
        int temp = -1;
        for(HttpMethod method : HttpMethod.values())
        {
            if(method.name().length() > temp)
            {
                temp = method.name().length();
            }
        }
        MAX_LENGTH = temp;
    }
}
