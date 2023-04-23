package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIfNumberOver5() {
        input.add(4);
        input.add(5);
        input.add(6);
        input.add(7);
        input.add(8);
        ListUtils.removeIf(input, n -> n > 5);
        assertThat(input).hasSize(4).containsSequence(1, 3, 4, 5);
    }

    @Test
    void whenRemoveIfNumberEvenNotChanges() {
        input.add(5);
        input.add(7);
        ListUtils.removeIf(input, n -> n % 2 == 0);
        assertThat(input).hasSize(4).containsSequence(1, 3, 5, 7);
    }

    @Test
    void whenReplaceIfNumberLess5() {
        input.add(7);
        input.add(2);
        input.add(5);
        input.add(1);
        input.add(4);
        ListUtils.replaceIf(input, n -> n < 5, 0);
        assertThat(input).containsSequence(0, 0, 7, 0, 5, 0, 0);
    }

    @Test
    void whenReplaceIfNumberOver10NotChanges() {
        ListUtils.replaceIf(input, n -> n > 10, 0);
        assertThat(input).containsSequence(1, 3);
    }

    @Test
    void whenRemoveAll() {
        input.add(5);
        ListUtils.removeAll(input, List.of(1, 3, 5));
        assertThat(input).hasSize(0);
    }

    @Test
    void whenRemoveAllSequence() {
        input.add(5);
        input.add(6);
        ListUtils.removeAll(input,  List.of(1, 3, 7, 9));
        assertThat(input).hasSize(2).containsSequence(5, 6);
    }
}