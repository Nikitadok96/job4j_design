package ru.job4j.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> duplicatesMap = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(Files.size(file), file.getFileName().toString());
        duplicatesMap.computeIfAbsent(property, s -> new ArrayList<>()).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }
    public Map<FileProperty, List<Path>> getDuplicatesMap() {
        return duplicatesMap;
    }
}
