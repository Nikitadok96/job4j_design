package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;

public class Emulator {
    private String cacheDir;
    private final AbstractCache<String, String> abstractCache;

    public Emulator(String cacheDir, AbstractCache<String, String> abstractCache) {
        this.cacheDir = cacheDir;
        this.abstractCache = abstractCache;
    }

    public void setCachedDir(String dir) {
        this.cacheDir = dir;
    }

    public String getCacheData(String key) {
        return abstractCache.get(key);
    }

    public void putCacheData(String key, String value) {
        abstractCache.put(key, value);
    }
}