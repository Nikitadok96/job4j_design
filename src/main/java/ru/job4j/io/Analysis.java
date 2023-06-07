package ru.job4j.io;

import java.io.*;
import java.util.regex.Pattern;

public class Analysis {
    public void unavailable(String source, String target) {
        try (PrintWriter printWriter = new PrintWriter(target);
             BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            Pattern pattern = Pattern.compile("^[4, 5]");
            boolean isWorking = true;
            while (bufferedReader.ready()) {
                String str = bufferedReader.readLine();
                boolean status = pattern.matcher(str).find();
                if (status == isWorking) {
                    printWriter.append(str.split(" ")[1])
                            .append(";")
                            .append(status ? "" : System.lineSeparator());
                    isWorking = !isWorking;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}

