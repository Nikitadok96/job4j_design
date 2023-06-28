package ru.job4j.graduation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        ArgsConfiguration argsConfiguration = ArgsConfiguration.of(args);
        File outFile = new File(argsConfiguration.get("o"));
        outFile.createNewFile();
        validateArgs(argsConfiguration);
        Path sourceFolder = Paths.get(argsConfiguration.get("d"));
        if (!Files.exists(sourceFolder)) {
            throw new IllegalArgumentException(String.format(
                    "Source folder: %s is not exist", sourceFolder.toAbsolutePath()
            ));
        }
        List<Path> pathList;
        String fileName = argsConfiguration.get("n");
        if ("name".equals(argsConfiguration.get("t"))) {
            pathList = SearchEngine.search(sourceFolder, path -> fileName.equals(path.toFile().getName()));
        } else if ("mask".equals(argsConfiguration.get("t"))) {
            String temp = fileName;
            temp = temp.replace(".", "\\.")
                    .replace("?", ".")
                    .replace("*", ".+");
            Pattern pattern = Pattern.compile(temp);
            pathList = SearchEngine.search(sourceFolder, path -> pattern.matcher(path.toFile().getName()).find());
        } else {
            Pattern pattern = Pattern.compile(fileName);
            pathList = SearchEngine.search(sourceFolder, path -> pattern.matcher(path.toFile().getName()).find());
        }
        try (PrintWriter printWriter = new PrintWriter(outFile)) {
            pathList.forEach(printWriter::println);
        }
    }

    private static void validateArgs(ArgsConfiguration argsConfiguration) {
        if (!Files.isDirectory(Paths.get(argsConfiguration.get("d")))) {
            throw new IllegalArgumentException("The first argument must be a directory.");
        }
        if (!List.of("mask", "name", "regex").contains(argsConfiguration.get("t"))) {
            throw new IllegalArgumentException("The third argument should be mask or name or regex");
        }
        if (List.of("mask", "regex").contains(argsConfiguration.get("t"))) {
            Pattern pattern = Pattern.compile(".+\\.[^.]+$");
            if (!pattern.matcher(argsConfiguration.get("n")).find()) {
                throw new IllegalArgumentException("The second argument must be in the form of a file extension");
            }
        } else if (!new File(argsConfiguration.get("n")).isFile()) {
            throw new IllegalArgumentException("The second argument must be a file");
        }
        if (!Files.exists(Paths.get(argsConfiguration.get("o")))) {
            throw new IllegalArgumentException("The fourth argument must be a file.");
        }
    }
}
