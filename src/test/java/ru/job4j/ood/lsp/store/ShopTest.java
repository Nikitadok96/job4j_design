package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Food;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ShopTest {
    @Test
    public void whenCurrentAddOneFood() {
        Store store = new Shop();
        Food first = new Food("Apple",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 8),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 26),
                100, 20);
        store.add(first, new GregorianCalendar(2023, Calendar.SEPTEMBER, 17));
        List<Food> rsl = store.getListFood();
        assertThat(rsl).containsSequence(List.of(first));
    }

    @Test
    public void whenCurrentAddTwoFood() {
        Store store = new Shop();
        Food first = new Food("Apple",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 10),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 26),
                100, 20);
        Food second = new Food("Orange",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 11),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 30),
                100, 20);
        store.add(first, new GregorianCalendar(2023, Calendar.SEPTEMBER, 17));
        store.add(second, new GregorianCalendar(2023, Calendar.SEPTEMBER, 17));
        List<Food> rsl = store.getListFood();
        assertThat(rsl).containsSequence(List.of(first, second));
    }

    @Test
    public void whenCurrentNotAddFood() {
        Store store = new Shop();
        Food first = new Food("Apple",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 15),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 26),
                100, 20);
        store.add(first, new GregorianCalendar(2023, Calendar.SEPTEMBER, 17));
        List<Food> rsl = store.getListFood();
        assertThat(rsl).doesNotContain(first);
    }

    @Test
    public void whenCurrentAdd1OfThe2Food() {
        Store store = new Shop();
        Food first = new Food("Apple",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 5),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 26),
                100, 20);
        Food second = new Food("Orange",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 15),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 30),
                100, 20);
        store.add(first, new GregorianCalendar(2023, Calendar.SEPTEMBER, 17));
        store.add(second, new GregorianCalendar(2023, Calendar.SEPTEMBER, 17));
        List<Food> rsl = store.getListFood();
        assertThat(rsl).containsSequence(List.of(first));
        assertThat(rsl).hasSize(1);
    }

    @Test
    public void whenCurrentAddDiscount() {
        Store store = new Shop();
        Food first = new Food("Apple",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 8),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 19),
                100, 20);
        store.add(first, new GregorianCalendar(2023, Calendar.SEPTEMBER, 17));
        List<Food> rsl = store.getListFood();
        double expectedPrice = 80;
        double rslPrice = rsl.get(0).getPrice();
        System.out.println();
        assertThat(expectedPrice).isEqualTo(rslPrice);
    }
}