package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Food;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {

    @Test
    public void whenCurrentAddOneFood() {
        Store warehouse = new Warehouse();
        Food first = new Food("Apple",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 15),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 26),
                100, 20);
        warehouse.add(first, new GregorianCalendar(2023, Calendar.SEPTEMBER, 17));
        List<Food> rsl = warehouse.getListFood();
        assertThat(rsl).containsSequence(List.of(first));
    }

    @Test
    public void whenCurrentAddTwoFood() {
        Store warehouse = new Warehouse();
        Food first = new Food("Apple",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 15),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 26),
                100, 20);
        Food second = new Food("Orange",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 16),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 30),
                100, 20);
        warehouse.add(first, new GregorianCalendar(2023, Calendar.SEPTEMBER, 17));
        warehouse.add(second, new GregorianCalendar(2023, Calendar.SEPTEMBER, 17));
        List<Food> rsl = warehouse.getListFood();
        assertThat(rsl).containsSequence(List.of(first, second));
    }

    @Test
    public void whenCurrentNotAddFood() {
        Store warehouse = new Warehouse();
        Food first = new Food("Apple",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 5),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 26),
                100, 20);
        warehouse.add(first, new GregorianCalendar(2023, Calendar.SEPTEMBER, 17));
        List<Food> rsl = warehouse.getListFood();
        assertThat(rsl).doesNotContain(first);
    }

    @Test
    public void whenCurrentAdd1OfThe2Food() {
        Store warehouse = new Warehouse();
        Food first = new Food("Apple",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 5),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 26),
                100, 20);
        Food second = new Food("Orange",
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 15),
                new GregorianCalendar(2023, Calendar.SEPTEMBER, 30),
                100, 20);
        warehouse.add(first, new GregorianCalendar(2023, Calendar.SEPTEMBER, 17));
        warehouse.add(second, new GregorianCalendar(2023, Calendar.SEPTEMBER, 17));
        List<Food> rsl = warehouse.getListFood();
        assertThat(rsl).containsSequence(List.of(second));
        assertThat(rsl).hasSize(1);
    }
}