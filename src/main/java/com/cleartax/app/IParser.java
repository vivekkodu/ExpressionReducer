package com.cleartax.app;

import java.util.Map;

/**
 * Created by VIVEK VERMA on 2/18/2017.
 */
public interface IParser {
    Map<String, Object> parse(String input);
}
