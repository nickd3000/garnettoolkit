package com.physmo.garnettoolkit;

import java.util.HashMap;
import java.util.Map;


public enum StringIdBroker {

    INSTANCE;

    private int nextId = 1;
    private final Map<String, Integer> idMap;

    StringIdBroker() {
        idMap = new HashMap<>();
    }

    public static StringIdBroker getInstance() {
        return INSTANCE;
    }

    public int getId(String key) {
        if (!idMap.containsKey(key)) {
            idMap.put(key, nextId++);
        }

        return idMap.get(key);
    }

}