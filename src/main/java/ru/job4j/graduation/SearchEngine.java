package ru.job4j.graduation;

import ru.job4j.io.SearchFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class SearchEngine {
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        FileSearchEngine searcher = new FileSearchEngine(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
