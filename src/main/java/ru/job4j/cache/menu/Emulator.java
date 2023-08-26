package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Emulator {

    private static String cacheDir = null;
    public static final String MENU = """
                Введите 1, чтобы указать кэшируемую директорию.
                Введите 2, чтобы загрузить содержимое файла в кэш.
                Введите 3, получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;
    public static final String SELECT = "Выберите меню: ";

    public static void main(String[] args) {
        boolean run = true;
        AbstractCache<String, String> abstractCache = null;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(new Scanner(System.in).nextLine());
            if (1 == userChoice) {
                System.out.print("Укажите директорию: ");
                cacheDir = new Scanner(System.in).nextLine();
                abstractCache = new DirFileCache(cacheDir);
            } else if (2 == userChoice) {
                if (dirIsReady()) {
                    System.out.print("Введите наименование файла: ");
                    String key = new Scanner(System.in).nextLine();
                    System.out.print("Введите содержание файла: ");
                    String value = new Scanner(System.in).nextLine();
                    abstractCache.put(key, value);
                }
            } else if (3 == userChoice) {
                if (dirIsReady()) {
                    System.out.print("Введите наименование файла: ");
                    String file = new Scanner(System.in).nextLine();
                    System.out.println(abstractCache.get(file));
                }
            } else {
                run = false;
                System.out.println("EXIT");
            }
        }
    }

    private static boolean dirIsReady() {
        boolean rsl = false;
        if (cacheDir != null) {
            rsl = true;
        } else {
            System.out.println("Необходимо указать директорию.");
        }
        return rsl;
    }
}