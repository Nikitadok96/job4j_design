package ru.job4j.ood.isp.menu;

import java.util.Optional;
import java.util.Scanner;

public class TodoApp {
    private static final String MENU_TEXT = """
            Меню
            1. Добавить элемент в корень меню
            2. Добавить элемент к родительскому элементу
            3. Вызвать действие
            4. Вывести меню в консоль
            5. Выход
            """;
    private final Menu menu;
    private final MenuPrinter menuPrinter;
    private final ActionDelegate actionDelegate = () -> System.out.println("Some action");

    public TodoApp(Menu menu, MenuPrinter menuPrinter) {
        this.menu = menu;
        this.menuPrinter = menuPrinter;
    }

    public static void main(String[] args) {
        boolean run = true;
        TodoApp todoApp = new TodoApp(new SimpleMenu(), new SimpleMenuPrinter());
        Scanner in = new Scanner(System.in);
        while (run) {
            System.out.println(MENU_TEXT);
            System.out.println("Выберите пункт: ");
            int select = Integer.parseInt(in.next());
            if (select < 1 || select > 5) {
                System.out.println("Ошибка ввода. Вы можете ввести от 1 до 5");
                continue;
            }
            if (select == 1) {
                todoApp.addRootTask();
            } else if (select == 2) {
                todoApp.addChildTask();
            } else if (select == 3) {
                todoApp.getAction();
            } else if (select == 4) {
                todoApp.getTaskMenu();
            } else if (select == 5) {
                run = false;
            }
            System.out.println();
        }
    }

    private boolean addRootTask() {
        boolean rsl = false;
        System.out.println("Введите имя:");
        String name = input();
        if (menu.add(null, name, actionDelegate)) {
            rsl = true;
        }
        System.out.println(rsl ? "Задача успешно добавлена" : "Ошибка при добавлении");
        return rsl;
    }

    private boolean addChildTask() {
        boolean rsl = false;
        System.out.println("Введите название родителя-задачи:");
        String parentName = input();
        if (menu.select(parentName).isPresent()) {
            System.out.println("Введите название родителя-задачи:");
            String childName = input();
            menu.add(parentName, childName, actionDelegate);
            rsl = true;

        }
        System.out.println(rsl ? "Задача успешно добавлена" : "Ошибка при добавлении");
        return rsl;
    }

    private void getAction() {
        System.out.println("Введите название задачи: ");
        String name = input();
        Optional<Menu.MenuItemInfo> menuItemInfo = menu.select(name);
        menuItemInfo.ifPresent(itemInfo -> itemInfo.getActionDelegate().delegate());
    }

    private void getTaskMenu() {
        menuPrinter.print(menu);
    }

    private String input() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
