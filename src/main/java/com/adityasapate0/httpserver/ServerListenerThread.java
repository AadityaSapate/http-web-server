package com.adityasapate0.httpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class ServerListenerThread implements Runnable{

    private int port;
    private ServerSocket serverSocket;
    private static final Logger logger = LoggerFactory.getLogger(ServerListenerThread.class);
    private Executor executor;
    public ServerListenerThread(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.executor = Executors.newFixedThreadPool(3);
    }

    public void run() {
        try{
            while(serverSocket.isBound() && !serverSocket.isClosed()) {
                final Socket socket = serverSocket.accept();
                executor.execute(new HttpWorkerThread(socket));
            }
        }catch(IOException ioException)
        {

        }finally {
            if(serverSocket != null)
            {
                try {
                    serverSocket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
