package ru.job4j.ood.lsp.controller;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.service.FoodService;
import ru.job4j.ood.lsp.service.SimpleFoodService;
import ru.job4j.ood.lsp.store.*;

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
        ShopStore shopStoreMemory = new MemoryShopStore();
        shopStoreMemory.saveShop(warehouseStore);
        shopStoreMemory.saveShop(shopStore);
        shopStoreMemory.saveShop(trashStore);
        ControlQuality controlQuality = new ControlQuality(shopStoreMemory);
        FoodService foods = new SimpleFoodService();
        foods.addFood(first);
        foods.addFood(second);
        foods.addFood(third);
        foods.addFood(four);
        controlQuality.init(foods, new GregorianCalendar(2023, Calendar.SEPTEMBER, 17));
        List<Food> warehouseList = warehouseStore.getListFood();
        List<Food> shopList = shopStore.getListFood();
        List<Food> trashList = trashStore.getListFood();
        assertThat(warehouseList).containsSequence(List.of(first, four));
        assertThat(shopList).containsSequence(second);
        assertThat(trashList).containsSequence(third);
    }

}