package com.adityasapate0.httpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpWorkerThread implements Runnable {

    private Socket socket;
    private static final Logger logger = LoggerFactory.getLogger(HttpWorkerThread.class);
    public HttpWorkerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;


        try {
            logger.info("Started worker thread " + Thread.currentThread().getName());
            logger.info("Processing Request from " + socket.getInetAddress());
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            String html = "<html> <head>  </head> <body> Hi  </body>  </html>";
            final String CRLF = "\n\r";
            String response = "HTTP/1.1 200 OK" + CRLF
                    + "Content-Length: " + html.getBytes().length + CRLF
                    + CRLF
                    + html
                    + CRLF + CRLF;
            outputStream.write(response.getBytes());

        }
        catch (IOException e) {
        }
        finally {
            try {
                outputStream.close();
            } catch (IOException ioException) {

            }
            try {
                inputStream.close();
            } catch (IOException ioException) {

            }
            try {
                socket.close();
            } catch (IOException ioException) {
            }
        }
    }
}

