package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

public class Trash extends AbstractStore {
    @Override
    public boolean getStorageCondition(Food food) {
        return food.getRemainingExpirationDate() >= 100;
    }
}
