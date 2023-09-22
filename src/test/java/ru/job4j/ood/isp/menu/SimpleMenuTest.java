package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenAddAndSelectChildAndTitle() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        Menu.MenuItemInfo menuItemInfo = menu.select("Сходить в магазин").get();
        List<String> expectChildrenList = new ArrayList<>();
        expectChildrenList.add("Купить продукты");
        assertThat(expectChildrenList).isEqualTo(menuItemInfo.getChildren());
        String expectedTitle = "1.Сходить в магазин";
        assertThat(expectedTitle).isEqualTo(menuItemInfo.getNumber() + menuItemInfo.getName());
    }

    @Test
    public void whenAddAndConclusion() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        List<String> rls = new ArrayList<>();
        menu.forEach(i -> {
            rls.add(i.getNumber() + i.getName());
        });
        List<String> expected = new ArrayList<>();
        expected.add("1.Сходить в магазин");
        expected.add("1.1.Купить продукты");
        expected.add("2.Покормить собаку");
        assertThat(expected).isEqualTo(rls);
    }
}