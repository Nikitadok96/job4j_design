package ru.job4j.graduation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ArgsConfiguration {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format(
                    "This key: '%s' is missing", key
            ));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String str : args) {
            if (!Pattern.compile("^-").matcher(str).find()) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not start with a '-' character", str
                ));
            }
            if (!str.contains("=")) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain an equal sign", str
                ));
            }
            String[] array = str.split("=", 2);
            String key = array[0].replaceFirst("-", "");
            if (!Pattern.compile("\\w+").matcher(key).find()) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain a key", str
                ));
            }
            String value = array[1];
            if (!Pattern.compile("^.+").matcher(value).find()) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain a value", str
                ));
            }
            values.put(key, value);
        }
    }

    public static ArgsConfiguration of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsConfiguration names = new ArgsConfiguration();
        names.parse(args);
        return names;
    }
}
