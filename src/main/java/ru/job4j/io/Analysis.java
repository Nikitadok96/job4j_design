package ru.job4j.io;

import java.io.*;
import java.util.regex.Pattern;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            boolean isWorking = true;
            try (FileWriter fileWriter = new FileWriter(target)) {
                while (bufferedReader.ready()) {
                    String str = bufferedReader.readLine();
                    if (errorServer(str) && isWorking) {
                        fileWriter.write(str.split(" ")[1]);
                        fileWriter.write(";");
                        isWorking = false;
                    }
                    if (!errorServer(str) && !isWorking) {
                        fileWriter.write(str.split(" ")[1]);
                        fileWriter.write(";");
                        fileWriter.write("\n");
                        isWorking = true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean errorServer(String str) {
        Pattern pattern = Pattern.compile("^[4, 5]");
        return pattern.matcher(str).find();
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}

