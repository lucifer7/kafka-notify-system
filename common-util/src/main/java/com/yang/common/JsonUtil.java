package com.yang.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Usage: <b> </b>
 *
 * @author Jingyi.Yang
 * Date 2018/7/5
 **/
public class JsonUtil {
    private static Gson gson = new GsonBuilder().create();

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

}
