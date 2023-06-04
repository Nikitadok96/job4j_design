package ru.job4j.io;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path))) {
            Pattern pattern = Pattern.compile("^.+=\\w.+");
            values.putAll(bufferedReader.lines()
                            .filter(s -> {
                                boolean rsl = s.length() > 0 && !s.startsWith("#");
                                if (rsl && !pattern.matcher(s).find()) {
                                    throw new IllegalArgumentException("Pattern violation: " + s);
                                }
                                return rsl;
                            })
                    .map(s -> s.split("=", 2))
                    .collect(Collectors.toMap(k -> k[0], v -> v[1])));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
