package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ArgsName {
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

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
