package com.diligesoft.config.utils.cdi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.eclipse.microprofile.config.spi.Converter;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by dshultz on 30/08/2016.
 */
public class ConfigListConverter implements Converter<List> {

    public static Gson GSON = new Gson();

    @Override
    public List convert(String s) {
        Type type = new TypeToken<List>() {
        }.getType();
        return GSON.fromJson(s, type);
    }
}
