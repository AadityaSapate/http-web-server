package com.adityasapate0.httpserver.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class HttpParser {
    private static final Logger logger = LoggerFactory.getLogger(HttpParser.class);
    private static final int SP = 0x20;
    private static final int CR = 0x0D;
    private static final int LF = 0x0A;

    public HttpRequest parseHttpRequest(InputStream inputStream) throws IOException, HttpParsingException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        
        HttpRequest httpRequest = new HttpRequest();
        
        parseRequestLine(inputStreamReader, httpRequest);
        return httpRequest;
    }

    private void parseRequestLine(InputStreamReader inputStreamReader, HttpRequest httpRequest) throws IOException, HttpParsingException {
        int _byte;
        boolean isMethodSet = false;
        boolean isTargetSet = false;
        StringBuffer stringBuffer = new StringBuffer();
        while ((_byte = inputStreamReader.read()) >= 0)
        {
            if(_byte == CR)
            {
                _byte = inputStreamReader.read();
                if(_byte == LF){
                    //empty line
                    if(!isMethodSet || !isTargetSet)
                    {
                        throw new HttpParsingException(HttpStatusCode.CLIENT_400_BAD_REQUEST);
                    }
                    logger.info("End of request line "+stringBuffer.toString());
                    return;
                }else {
                    throw new HttpParsingException(HttpStatusCode.CLIENT_400_BAD_REQUEST);
                }
            }
            if(_byte == SP)
            {
                if(!isMethodSet)
                {
                    logger.info("End of method "+stringBuffer.toString());
                    httpRequest.setMethod(stringBuffer.toString());
                    isMethodSet = true;
                }else if(!isTargetSet)
                {
                    logger.info("End of target "+stringBuffer.toString());
                    isTargetSet = true;
                }else { // more sp detected

                }
                stringBuffer.delete(0, stringBuffer.length());
            }else{
                stringBuffer.append((char)_byte);
                //check the method length
                if(!isMethodSet) {
                    if (stringBuffer.length() > HttpMethod.MAX_LENGTH)
                    {
                        throw new HttpParsingException(HttpStatusCode.CLIENT_414_BAD_REQUEST);
                    }
                }
             }
        }
    }

}
