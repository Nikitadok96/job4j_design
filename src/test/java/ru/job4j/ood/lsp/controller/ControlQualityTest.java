package ru.job4j.ood.lsp.controller;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.store.Shop;
import ru.job4j.ood.lsp.store.Store;
import ru.job4j.ood.lsp.store.Trash;
import ru.job4j.ood.lsp.store.Warehouse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {
    @Test
    public void whenCurrentAdd3FoodAnd3Store() {
        Store warehouseStore = new Warehouse();
        Store shopStore = new Shop();
        Store trashStore = new Trash();
        Food first = new Food("Apple",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 15),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 26),
                100, 20);
        Food second = new Food("Orange",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 11),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 30),
                100, 20);
        Food third = new Food("Banana",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 8),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 16),
                100, 20);
        Food four = new Food("Fish",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 15),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 26),
                100, 20);
        ControlQuality controlQuality = new ControlQuality(List.of(warehouseStore, shopStore, trashStore));
        List<Food> foods = new ArrayList<>();
        foods.add(first);
        foods.add(second);
        foods.add(third);
        foods.add(four);
        controlQuality.init(foods, new GregorianCalendar(2023, Calendar.SEPTEMBER, 17));
        List<Food> warehouseList = warehouseStore.getListFood();
        List<Food> shopList = shopStore.getListFood();
        List<Food> trashList = trashStore.getListFood();
        assertThat(warehouseList).containsSequence(List.of(first, four));
        assertThat(shopList).containsSequence(second);
        assertThat(trashList).containsSequence(third);
    }

}