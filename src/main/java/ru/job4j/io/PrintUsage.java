package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintUsage {
    public static void main(String[] args) {
        try (PrintStream stream = new PrintStream(new FileOutputStream("data/print.txt"))) {
            stream.println("Новая строка");
            stream.write("Byte".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
