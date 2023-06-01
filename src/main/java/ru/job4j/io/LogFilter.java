package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LogFilter {

    public static void save(List<String> log, String file) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String s : log) {
                printWriter.write(s);
                printWriter.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            list = in.lines().filter(l -> {
                String[] arr = l.split(" ");
                return "404".equals(arr[arr.length - 2]);
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        log.forEach(System.out::println);
        save(log, "data/404.txt");
    }
}
