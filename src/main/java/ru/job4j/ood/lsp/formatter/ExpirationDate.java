package ru.job4j.ood.lsp.formatter;

import ru.job4j.ood.lsp.model.Food;

import java.time.Duration;
import java.util.Calendar;

public class ExpirationDate {
    private final double remainingDays;

    public ExpirationDate(Food food, Calendar currentDate) {
        double lifeDuration = Duration.between(food.getCreateDate().toInstant(),
                food.getExpiryDate().toInstant()).toHours();
        double currentDuration = Duration.between(currentDate.toInstant(),
                food.getExpiryDate().toInstant()).toHours();
        remainingDays = 100 - currentDuration * 100 / lifeDuration;
    }

    public double getRemainingDays() {
        return remainingDays;
    }
}
