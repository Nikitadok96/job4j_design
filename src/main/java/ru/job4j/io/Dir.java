package ru.job4j.io;

import java.io.File;
import java.nio.file.Files;

public class Dir {
    public static void main(String[] args) {
        File file = new File("C:/projects");
        for (File subFile : file.listFiles()) {
            if (subFile.isFile()) {
                System.out.printf("Name: %s. Size file: %s\n", subFile.getName(), subFile.length());
            }
        }
    }
}
