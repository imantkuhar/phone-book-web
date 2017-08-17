package com.cooksdev.web.util;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtils {

    private static final JsonNodeFactory nodeFactory = new JsonNodeFactory(false);
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
}
