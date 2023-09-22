package ru.job4j.ood.lsp.service;

import ru.job4j.ood.lsp.model.Food;

import java.util.ArrayList;
import java.util.List;

public class SimpleFoodService implements FoodService {
    List<Food> foods = new ArrayList<>();

    @Override
    public boolean addFood(Food food) {
        return foods.add(food);
    }

    @Override
    public List<Food> getStore() {
        return foods;
    }
}
