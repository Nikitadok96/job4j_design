package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.formatter.ExpirationDate;
import ru.job4j.ood.lsp.model.Food;

import java.util.Calendar;

public class Shop extends AbstractStore {

    @Override
    public boolean getStorageCondition(Food food, Calendar calendar) {
        boolean rls = false;
        ExpirationDate expirationDate = new ExpirationDate(food, calendar);
        double currentLifeHours = expirationDate.getRemainingDays();
        if (currentLifeHours > 25 && currentLifeHours < 100) {
            if (currentLifeHours > 75) {
                double currentPrice = food.getPrice() - food.getDiscount();
                food.setPrice(currentPrice);
            }
            rls = true;
        }
        return rls;
    }
}
