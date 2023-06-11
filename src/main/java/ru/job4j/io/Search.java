package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validateArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(String.format(
                    "The required number of arguments is 2. But passed %s", args.length));
        }
        if (!Files.isDirectory(Paths.get(args[0]))) {
            throw new IllegalArgumentException("The first argument must be a directory.");
        }
        Pattern pattern = Pattern.compile("(\\.[^.]+)$");
        if (!pattern.matcher(args[1]).find()) {
            throw new IllegalArgumentException("The second argument must be the file extension.");
        }

    }

}
