package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        validationArgs(argsName);
        Path sourceFolder = Paths.get(argsName.get("d"));
        if (!Files.exists(sourceFolder)) {
            throw new IllegalArgumentException(String.format(
                    "Source folder: %s is not exist", sourceFolder.toAbsolutePath()
            ));
        }
        List<Path> pathList = Search.search(sourceFolder, path -> !path.toFile().getName().endsWith(argsName.get("e")));
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        zip.packFiles(pathList, new File(argsName.get("o")));
    }

    private static void validationArgs(ArgsName argsName) {
        if (!Files.isDirectory(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException("The first argument must be a directory.");
        }
        Pattern pattern = Pattern.compile("(\\.[^.]+)$");
        if (!pattern.matcher(argsName.get("e")).find()) {
            throw new IllegalArgumentException("The second argument must be the file extension.");
        }
        if (!Files.exists(Paths.get(argsName.get("o")))) {
            throw new IllegalArgumentException("The third argument must be a file.");
        }
    }


}
