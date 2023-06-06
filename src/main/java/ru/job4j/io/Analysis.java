package ru.job4j.io;

import java.io.*;
import java.util.regex.Pattern;

public class Analysis {
    public void unavailable(String source, String target) {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(target))) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(source));
            Pattern pattern = Pattern.compile("^[4, 5]");
            boolean isWorking = true;
            while (bufferedReader.ready()) {
                String str = bufferedReader.readLine();
                String message = pattern.matcher(str).find()
                        ? String.format("%s;", str.split(" ")[1])
                        : String.format("%s;\n", str.split(" ")[1]);
                if (pattern.matcher(str).find()) {
                    if (isWorking) {
                        printWriter.write(message);
                        isWorking = false;
                    }
                } else {
                    if (!isWorking) {
                        printWriter.write(message);
                        isWorking = true;
                    }
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

