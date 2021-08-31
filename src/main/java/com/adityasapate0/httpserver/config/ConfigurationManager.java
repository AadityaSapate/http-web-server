package com.adityasapate0.httpserver.config;

import com.adityasapate0.httpserver.util.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {

    private static ConfigurationManager configurationManager;

    private static Configuration configuration;

    private ConfigurationManager()
    {

    }

    public static ConfigurationManager getConfigurationManager()
    {
        if(configurationManager != null)
            return configurationManager;
        return new ConfigurationManager();
    }

    public void loadConfigurationFile(String fileSrc)
    {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileSrc);
        }catch (FileNotFoundException e)
        {
            throw new RuntimeException("HttpConfigurationException",e);
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                stringBuffer.append((char) i);
            }
        }catch (IOException e)
        {
            throw new RuntimeException("HttpConfigurationException",e);
        }

        try {
            JsonNode jsonNode = Json.parse(stringBuffer.toString());
            configuration = Json.objectFromJson(jsonNode, Configuration.class);
        }catch (JsonProcessingException e)
        {
            throw new RuntimeException("HttpConfigurationException",e);
        }
    }

    public Configuration getConfiguration()
    {
       if(configuration == null)
       {
           throw new RuntimeException("HttpConfigurationException");
       }
       return configuration;
    }
}
