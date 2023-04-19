package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "four", "second", "three");
        assertThat(list).hasSize(4)
                .containsExactly("first", "four", "second", "three")
                .containsAnyOf("six", "nine", "first")
                .allMatch(x -> !x.isEmpty())
                .containsSequence("four", "second");
        assertThat(list).first().isEqualTo("first");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> stringSet = simpleConvert.toSet("first", "first", "second", "five");
        assertThat(stringSet).hasSize(3)
                        .contains("first", "five", "second");
        assertThat(stringSet).filteredOn(e -> e.length() > 5)
                .hasSize(1)
                .first().isEqualTo("second");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("First", "Second", "Three", "Four");
        assertThat(map).isNotNull()
                .containsKeys("First", "Four")
                .containsValues(0, 1, 2, 3)
                .containsEntry("Four", 3)
                .doesNotContainEntry("Five", 4);
    }
}