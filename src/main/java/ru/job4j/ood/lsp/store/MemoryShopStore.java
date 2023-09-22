package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;

public class MemoryShopStore implements ShopStore {

    List<Store> stores = new ArrayList<>();

    @Override
    public boolean saveShop(Store store) {
        return stores.add(store);
    }

    @Override
    public List<Store> getStore() {
        return stores;
    }
}
