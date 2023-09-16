package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Food;

import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {

    @Test
    public void whenCurrentAddOneFood() {
        Store warehouse = new Warehouse();
        Food first = new Food("Apple",
                new GregorianCalendar(2023, 8, 14),
                new GregorianCalendar(2023, 8, 26),
                100, 20);
        warehouse.add(first);
        List<Food> rsl = warehouse.getListFood();
        assertThat(rsl).containsSequence(List.of(first));
    }

    @Test
    public void whenCurrentAddTwoFood() {
        Store warehouse = new Warehouse();
        Food first = new Food("Apple",
                new GregorianCalendar(2023, 8, 14),
                new GregorianCalendar(2023, 8, 26),
                100, 20);
        Food second = new Food("Orange",
                new GregorianCalendar(2023, 8, 15),
                new GregorianCalendar(2023, 8, 30),
                100, 20);
        warehouse.add(first);
        warehouse.add(second);
        List<Food> rsl = warehouse.getListFood();
        assertThat(rsl).containsSequence(List.of(first, second));
    }

    @Test
    public void whenCurrentNotAddFood() {
        Store warehouse = new Warehouse();
        Food first = new Food("Apple",
                new GregorianCalendar(2023, 8, 5),
                new GregorianCalendar(2023, 8, 26),
                100, 20);
        warehouse.add(first);
        List<Food> rsl = warehouse.getListFood();
        assertThat(rsl).doesNotContain(first);
    }

    @Test
    public void whenCurrentAdd1OfThe2Food() {
        Store warehouse = new Warehouse();
        Food first = new Food("Apple",
                new GregorianCalendar(2023, 8, 5),
                new GregorianCalendar(2023, 8, 26),
                100, 20);
        Food second = new Food("Orange",
                new GregorianCalendar(2023, 8, 15),
                new GregorianCalendar(2023, 8, 30),
                100, 20);
        warehouse.add(first);
        warehouse.add(second);
        List<Food> rsl = warehouse.getListFood();
        assertThat(rsl).containsSequence(List.of(second));
        assertThat(rsl).hasSize(1);
    }
}