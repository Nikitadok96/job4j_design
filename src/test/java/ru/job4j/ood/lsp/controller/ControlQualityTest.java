package ru.job4j.ood.lsp.controller;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.store.Shop;
import ru.job4j.ood.lsp.store.Store;
import ru.job4j.ood.lsp.store.Trash;
import ru.job4j.ood.lsp.store.Warehouse;

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
                new GregorianCalendar(2023, 8, 14),
                new GregorianCalendar(2023, 8, 26),
                100, 20);
        Food second = new Food("Orange",
                new GregorianCalendar(2023, 8, 11),
                new GregorianCalendar(2023, 8, 30),
                100, 20);
        Food third = new Food("Banana",
                new GregorianCalendar(2023, 8, 8),
                new GregorianCalendar(2023, 8, 16),
                100, 20);
        ControlQuality controlQuality = new ControlQuality(List.of(warehouseStore, shopStore, trashStore));
        controlQuality.init(List.of(first, second, third));
        List<Food> warehouseList = warehouseStore.getListFood();
        List<Food> shopList = shopStore.getListFood();
        List<Food> trashList = trashStore.getListFood();
        assertThat(warehouseList).containsSequence(first);
        assertThat(shopList).containsSequence(second);
        assertThat(trashList).containsSequence(third);
    }

}