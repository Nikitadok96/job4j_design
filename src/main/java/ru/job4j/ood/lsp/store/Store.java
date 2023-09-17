package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

import java.util.Calendar;
import java.util.List;

public interface Store {
    boolean add(Food food, Calendar calendar);
    List<Food> getListFood();
}
