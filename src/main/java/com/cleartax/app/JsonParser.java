package com.cleartax.app;

import com.google.gson.Gson;
import java.util.Map;

/**
 * Created by VIVEK VERMA on 2/18/2017.
 */
public class JsonParser implements IParser {
    public Map<String, Object> parse(String input) {
        Gson gson = new Gson();
        return gson.fromJson(input, Map.class);
    }
}
