package ru.job4j.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private HashMap<FileProperty, List<Path>> duplicatesMap = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        push(file);
        return super.visitFile(file, attrs);
    }

    private void push(Path file) throws IOException {
        FileProperty property = new FileProperty(Files.size(file), file.getFileName().toString());
        List<Path> paths;
        paths = duplicatesMap.containsKey(property) ? duplicatesMap.get(property) : new ArrayList<>();
        paths.add(file.toAbsolutePath());
        duplicatesMap.put(property, paths);
    }
    public HashMap<FileProperty, List<Path>> getDuplicatesMap() {
        return duplicatesMap;
    }
}
