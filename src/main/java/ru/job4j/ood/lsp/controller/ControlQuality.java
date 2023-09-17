package ru.job4j.ood.lsp.controller;

import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.store.Store;

import java.util.*;

public class ControlQuality {
    List<Store> stores = new ArrayList<>();

    public ControlQuality(List<Store> stores) {
        this.stores.addAll(stores);
    }

    public void init(List<Food> foods, Calendar currentDate) {
        stores.forEach(store -> {
            foods.removeIf(food -> store.add(food, currentDate));
        });
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}
