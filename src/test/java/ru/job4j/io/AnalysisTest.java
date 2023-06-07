package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


import java.io.*;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {
    @Test
    void whenOnePeriod(@TempDir Path tempPath) throws IOException {
        File source = tempPath.resolve("sourcePath.txt").toFile();
        try (PrintWriter printWriter = new PrintWriter(source)) {
            printWriter.println("200 10:56:01");
            printWriter.println("500 10:57:01");
            printWriter.println("400 10:58:01");
            printWriter.println("300 10:59:01");
        }
        File target = tempPath.resolve("targetPath.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(target))) {
            bufferedReader.lines().forEach(stringBuilder::append);
        }
        assertThat("10:57:01;10:59:01;").hasToString(stringBuilder.toString());
    }

    @Test
    void whenTwoPeriods(@TempDir Path tempPath) throws IOException {
        File source = tempPath.resolve("sourcePath.txt").toFile();
        try (PrintWriter printWriter = new PrintWriter(source)) {
            printWriter.println("200 10:56:01");
            printWriter.println("500 10:57:01");
            printWriter.println("400 10:58:01");
            printWriter.println("300 10:59:01");
            printWriter.println("400 11:01:01");
            printWriter.println("300 11:04:22");
        }
        File target = tempPath.resolve("targetPath.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(target))) {
            bufferedReader.lines().forEach(stringBuilder::append);
        }
        assertThat("10:57:01;10:59:01;11:01:01;11:04:22;").hasToString(stringBuilder.toString());
    }
}