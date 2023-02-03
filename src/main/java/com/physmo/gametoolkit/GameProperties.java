package com.physmo.gametoolkit;

import java.util.HashMap;
import java.util.Map;

public class GameProperties {
    private final Map<String, Object> properties;

    public GameProperties() {
        properties = new HashMap<>();
    }

    public void addProperty(String name, Object value) {
        properties.put(name, value);
    }

    public boolean hasProperty(String name) {
        return properties.containsKey(name);
    }

    /**
     * return false if property does not exist
     *
     * @param key
     * @return bool value
     */
    public boolean getPropertyBool(String key) {
        if (!properties.containsKey(key)) return false;
        return (boolean) properties.get(key);
    }

    public boolean propertyExists(String key) {
        return properties.containsKey(key);
    }

    public Object getProperty(String key) {
//        if (!properties.containsKey(key)) {
//            String name = (String) properties.get(EProp.NAME);
//            throw new RuntimeException("Property not found: " + key + " in " + name);
//        }
        return properties.get(key);
    }

    public double getPropertyDouble(String key) {
//        if (!properties.containsKey(key)) {
//            String name = (String) properties.get(EProp.NAME);
//            throw new RuntimeException("Property not found: " + key + " in " + name);
//        }

        return (double) properties.get(key);
    }

    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

}
