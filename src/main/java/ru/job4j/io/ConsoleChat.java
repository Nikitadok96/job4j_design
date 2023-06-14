package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> logList = new ArrayList<>();
        List<String> phrasesList = readPhrases();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean isRunning = true;
            System.out.print("Введите слово: ");
            String answer = bufferedReader.readLine();
            logList.add(answer);
            while (!OUT.equals(answer)) {
                if (!STOP.equals(answer) && isRunning) {
                    String botText = phrasesList.get(new Random().nextInt(phrasesList.size()));
                    System.out.printf("Бот ответил: %s\n", botText);
                    logList.add(botText);
                }
                System.out.print("Введите слово: ");
                answer = bufferedReader.readLine();
                logList.add(answer);
                isRunning = isRunning ? !answer.equals(STOP) : answer.equals(CONTINUE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveLog(logList);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(botAnswers))) {
            while (bufferedReader.ready()) {
                list.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(path))) {
            for (String s : log) {
                bufferedWriter.append(s).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/botLog.txt", "./data/botAnswer.txt");
        cc.run();
    }
}
