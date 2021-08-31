package com.adityasapate0.httpserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Json {

    private static ObjectMapper objectMapper = getObjectMapper();

    private static ObjectMapper getObjectMapper()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public static JsonNode parse(String jsonSrc) throws JsonProcessingException {
        return objectMapper.readTree(jsonSrc);
    }

    public static <T> T objectFromJson(JsonNode jsonNode, Class<T> tClass) throws JsonProcessingException {
        return objectMapper.treeToValue(jsonNode, tClass);
    }

    public static JsonNode objectToJson(Object o)
    {
        return objectMapper.valueToTree(o);
    }

    public static String stringify(JsonNode jsonNode) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        return objectWriter.writeValueAsString(jsonNode);
    }

}
