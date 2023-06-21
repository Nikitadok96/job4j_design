package ru.job4j.io;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path")))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine(), argsName.get("delimiter")));
            }
        }
        List<Integer> indexCol = new ArrayList<>();
        for (String s : argsName.get("filter").split(",")) {
            for (int j = 0; j < records.get(0).size(); j++) {
                if (s.equals(records.get(0).get(j))) {
                    indexCol.add(j);
                }
            }
        }
        List<List<String>> finalList = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            finalList.add(new ArrayList<>());
            for (Integer integer : indexCol) {
                finalList.get(i).add(records.get(i).get(integer));
            }
        }
        if ("stdout".equals(argsName.get("out"))) {
            finalList.forEach(s -> {
                String commaSeparatedString = String.join(argsName.get("delimiter"), s);
                System.out.println(commaSeparatedString);
            });

        } else {
            try (PrintWriter printWriter = new PrintWriter(new FileWriter(argsName.get("out")))) {
                finalList.forEach(s -> {
                    String commaSeparatedString = String.join(argsName.get("delimiter"), s);
                    printWriter.println(commaSeparatedString);
                });
            }
        }

    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validateArgs(argsName);
        handle(argsName);
    }

    private static void validateArgs(ArgsName args) {
        Pattern pattern = Pattern.compile("\\.csv$");
        if (!pattern.matcher(args.get("path")).find()) {
            throw new IllegalArgumentException("The first argument must be a file.");
        }
        if (!";".equals(args.get("delimiter"))) {
            throw new IllegalArgumentException("The second argument should be a delimiter ;");
        }
        if (!"stdout".equals(args.get("out")) && !pattern.matcher(args.get("out")).find()) {
            throw new IllegalArgumentException("The third argument must be stdout or a file");
        }
        if (args.get("filter").isEmpty()) {
            throw new IllegalArgumentException("The fourth argument cannot be empty");
        }
    }

    private static List<String> getRecordFromLine(String line, String delimiter) {
        List<String> values = new ArrayList<>();
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter(delimiter);
            while (scanner.hasNext()) {
                values.add(scanner.next());
            }
        }
        return values;
    }
}
