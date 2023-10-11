package org.zhumagulova.gymcrmsystem.dao;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.zhumagulova.gymcrmsystem.bpp.InitializeStorage;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Component

public class Storage {
    @InitializeStorage
    private Map<String, Object> storageMap = new HashMap<>();

    public void put(String key, Object object) {
        storageMap.put(key, object);
    }

    public Object get(String key) throws ClassNotFoundException {
        return storageMap.get(key);
    }

    public void delete(String key) {
        storageMap.remove(key);
    }
}