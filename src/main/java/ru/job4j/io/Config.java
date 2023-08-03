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
            values.putAll(bufferedReader.lines()
                            .filter(this::patternFilter)
                    .map(s -> s.split("=", 2))
                    .collect(Collectors.toMap(k -> k[0], v -> v[1])));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean patternFilter(String str) {
        Pattern pattern = Pattern.compile("^.+=\\w.+");
        boolean rsl = !str.isBlank() && !str.startsWith("#");
        if (rsl && !pattern.matcher(str).find()) {
            String patternString = "Pattern violation: %s";
            throw new IllegalArgumentException(String.format(patternString, str));
        }
        return rsl;
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
        Config config = new Config("data/app.properties");
        config.load();
        config.values.forEach((k, v) -> System.out.println("Key: " + k + "\nValue: " + v));
    }
}
