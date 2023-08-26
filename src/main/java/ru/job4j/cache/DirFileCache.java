package ru.job4j.cache;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        Path path = Paths.get(cachingDir, key);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            br.lines().forEach(stringBuilder::append);
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
