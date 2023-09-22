package ru.job4j.ood.lsp.controller;

import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.service.FoodService;
import ru.job4j.ood.lsp.store.MemoryShopStore;
import ru.job4j.ood.lsp.store.ShopStore;
import ru.job4j.ood.lsp.store.Store;

import java.util.*;

public class ControlQuality {
    private ShopStore shopStore;

    public ControlQuality(ShopStore shopStore) {
        this.shopStore = shopStore;
    }

    public void init(FoodService foodService, Calendar currentDate) {
        shopStore.getStore().forEach(store -> {
            foodService.getStore().removeIf(food -> store.add(food, currentDate));
        });
    }

    public void resort(ShopStore shopStore) {
        List<Store> stores = this.shopStore.getStore();
        stores.forEach(shopStore::saveShop);
    }

    public ShopStore getStores() {
        return shopStore;
    }

    public void setStores(ShopStore shopStore) {
        this.shopStore = shopStore;
    }
}
