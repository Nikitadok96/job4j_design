package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.formatter.ExpirationDate;
import ru.job4j.ood.lsp.model.Food;

import java.util.Calendar;

public class Warehouse extends AbstractStore {
    @Override
    public boolean getStorageCondition(Food food, Calendar calendar) {
        ExpirationDate expirationDate = new ExpirationDate(food, calendar);
        double currentLifeHours = expirationDate.getRemainingDays();
        return currentLifeHours <= 25;
    }
}
