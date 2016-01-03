package com.jamesmaupin.readdit.Utilities;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by jmsgears on 1/3/16.
 */
public class GenericJsonDeserializer<T> implements JsonDeserializer<T> {
    private String jsonName;
    private Class<T> mClass;

    public GenericJsonDeserializer(Class<T> mClass, String jsonName) {
        this.jsonName = jsonName;
        this.mClass = mClass;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement jsonElement = json.getAsJsonObject().get(jsonName);
        return new Gson().fromJson(jsonElement, mClass);
    }
}
