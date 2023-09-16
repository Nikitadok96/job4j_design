package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private List<Food> foods = new ArrayList<>();

    public List<Food> getListFood() {
        return foods;
    }

    abstract boolean getStorageCondition(Food food);

    @Override
    public void add(Food food) {
        if (getStorageCondition(food)) {
            foods.add(food);
        }
    }
}
