package com.javaweb.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Utility class for handling and converting request parameters.
 */
public class MapUtil {
    private static final Map<Class<?>, Function<String, ?>> typeConverters = new HashMap<>();

    static {
        typeConverters.put(Integer.class, s -> s.trim().isEmpty() ? null : Integer.valueOf(s.trim()));
        typeConverters.put(String.class, s -> s.trim().isEmpty() ? null : s.trim());
        typeConverters.put(Boolean.class, s -> s.trim().isEmpty() ? null : Boolean.valueOf(s.trim()));
        typeConverters.put(Double.class, s -> s.trim().isEmpty() ? null : Double.valueOf(s.trim()));
        typeConverters.put(Long.class, s -> s.trim().isEmpty() ? null : Long.valueOf(s.trim()));
        // Add more type converters as needed
    }

    /**
     * Gets an object of the specified type from the request parameters.
     *
     * @param <T> The type of the object to return.
     * @param requestParams The map of request parameters.
     * @param key The key of the parameter to retrieve.
     * @param tClass The class of the type to convert to.
     * @return The converted object, or null if conversion fails or the key does not exist.
     */
    public static <T> T getObject(Map<String, Object> requestParams, String key, Class<T> tClass) {
        if (requestParams == null) {
            return null;
        }

        Object obj = requestParams.get(key);
        if (obj == null) {
            return null;
        }

        Function<String, ?> converter = typeConverters.get(tClass);
        if (converter != null) {
            try {
                obj = converter.apply(obj.toString());
            } catch (Exception e) {
                // Log or handle the exception as needed
                return null;
            }
        }

        return tClass.cast(obj);
    }
}
