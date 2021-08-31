package com.adityasapate0.httpserver;

import com.adityasapate0.httpserver.config.Configuration;
import com.adityasapate0.httpserver.config.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class HttpServer {

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);
    public static void main(String args[])
    {
        ConfigurationManager configurationManager = ConfigurationManager.getConfigurationManager();
        configurationManager.loadConfigurationFile("src/main/resources/http-config.json");
        Configuration configuration = configurationManager.getConfiguration();
        logger.info("Starting server on port "+ configuration.getPort());
        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(configuration.getPort());
            Thread thread = new Thread(serverListenerThread);
            thread.start();
        }catch (IOException ioException)
        {

        }



    }

}
