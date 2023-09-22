package ru.job4j.ood.lsp.service;

import ru.job4j.ood.lsp.model.Food;

import java.util.List;

public interface FoodService {
    boolean addFood(Food food);
    List<Food> getStore();
}
