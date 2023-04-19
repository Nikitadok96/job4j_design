package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkEmptyParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void isNameNotContainEqually() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("keyValue"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("keyValue")
                .hasMessageContaining("does not contain the symbol \"=\"");
    }

    @Test
    void isNameStartsWithEqually() {
        NameLoad nameLoad = new NameLoad();
        String value = "=Values";
        assertThatThrownBy(() -> nameLoad.parse("Key=Value", value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(value)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void isNameEndWithEqually() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Key="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Key=")
                .hasMessageContaining("does not contain a value");
    }
}