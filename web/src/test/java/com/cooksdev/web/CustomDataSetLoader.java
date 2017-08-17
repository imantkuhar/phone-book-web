package com.cooksdev.web;

import com.github.springtestdbunit.dataset.FlatXmlDataSetLoader;
import com.github.springtestdbunit.dataset.ReplacementDataSetLoader;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class CustomDataSetLoader extends ReplacementDataSetLoader {

    private static final Map<String, Object> replaceMap = new HashMap<String, Object>() {

        {
            put("[login]", TestDataHelper.USER_LOGIN);
            put("[password:123456]", "$2a$10$CzcJ/v/e0/O/uY/s.rGLf.8I3cQ82RseCBbivxPQzqx9WXqCKYz5e");
        }
    };

    public CustomDataSetLoader() {
        super(new FlatXmlDataSetLoader(), Collections.unmodifiableMap(replaceMap));
    }
}
