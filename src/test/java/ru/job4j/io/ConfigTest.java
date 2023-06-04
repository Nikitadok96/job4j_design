package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("myName")).isEqualTo("Nikita");
        assertThat(config.value("second")).isEqualTo("lalin=1");
        assertThat(config.value("key")).isNull();
    }

    @Test
    void whenEmptyAndComments() {
        String path = "./data/empty_and_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("Comment")).isNull();
    }

    @Test
    void whenIllegalArgumentException() {
        String path = "./data/violation_pattern.properties";
        Config config = new Config(path);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(config::load)
                .withMessage("Pattern violation: =vozhegov");
    }

    @Test
    void whenComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("#")).isNull();
        assertThat(config.value("PostgreSQL")).isNull();
    }
}