package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private final List<Food> foods = new ArrayList<>();

    public List<Food> getListFood() {
        return foods;
    }

    abstract boolean getStorageCondition(Food food, Calendar calendar);

    @Override
    public boolean add(Food food, Calendar calendar) {
        boolean rls = false;
        if (getStorageCondition(food, calendar)) {
            foods.add(food);
            rls = true;
        }
        return rls;
    }
}
