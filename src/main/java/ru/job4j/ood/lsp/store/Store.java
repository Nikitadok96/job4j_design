package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

import java.util.List;

public interface Store {
    void add(Food food);

    List<Food> getListFood();
}
