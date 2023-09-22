package ru.job4j.ood.lsp.store;

import java.util.List;

public interface ShopStore {
    boolean saveShop(Store shop);
    List<Store> getStore();
}
