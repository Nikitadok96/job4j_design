package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

public class Shop extends AbstractStore {

    @Override
    public boolean getStorageCondition(Food food) {
        boolean rls = false;
        double currentLifeHours = food.getRemainingExpirationDate();
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
