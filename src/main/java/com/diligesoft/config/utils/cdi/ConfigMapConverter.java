package com.diligesoft.config.utils.cdi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.eclipse.microprofile.config.spi.Converter;

import java.lang.reflect.Type;
import java.util.Map;

public class ConfigMapConverter implements Converter<Map> {

    public static Gson GSON = new Gson();

    @Override
    public Map convert(String s) {
        Type type = new TypeToken<Map>() {
        }.getType();
        return GSON.fromJson(s, type);
    }
}

