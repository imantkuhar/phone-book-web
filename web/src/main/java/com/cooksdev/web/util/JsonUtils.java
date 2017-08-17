package com.cooksdev.web.util;

import com.cooksdev.service.exception.ErrorReason;
import com.cooksdev.service.exception.model.InternalServerErrorException;
import com.cooksdev.service.util.ExceptionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class JsonUtils {

    private static final JsonNodeFactory nodeFactory = new JsonNodeFactory(false);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static final String EMPTY_JSON = "{}";

    public static String toSimpleJson(String key, Object value) {
        if (key == null) {
            return EMPTY_JSON;
        }
        ObjectNode json = nodeFactory.objectNode();
        if (value instanceof Boolean) {
            json.put(key, (Boolean) value);
        } else if (value instanceof Long) {
            json.put(key, (Long) value);
        } else if (value instanceof Integer) {
            json.put(key, (Integer) value);
        } else {
            json.put(key, value.toString());
        }

        return json.toString();
    }

    public static <T> T fromJson(String value, Class<T> valueType) {
        if (value == null) {
            return null;
        }

        try {
            return objectMapper.readValue(value, valueType);
        } catch (IOException e) {
            throw new InternalServerErrorException(ErrorReason.JSON_MARSHALLING_ERROR, value, e);
        }
    }
}
