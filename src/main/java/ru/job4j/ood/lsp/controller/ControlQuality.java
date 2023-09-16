package ru.job4j.ood.lsp.controller;

import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.store.Shop;
import ru.job4j.ood.lsp.store.Store;
import ru.job4j.ood.lsp.store.Trash;
import ru.job4j.ood.lsp.store.Warehouse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ControlQuality {
    List<Store> stores = new ArrayList<>();

    public ControlQuality(List<Store> stores) {
        this.stores.addAll(stores);
    }

    public void init(Food food) {
        this.stores.forEach(s -> s.add(food));
    }

    public void init(List<Food> foods) {
        foods.forEach(food -> stores.forEach(store -> store.add(food)));
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}
