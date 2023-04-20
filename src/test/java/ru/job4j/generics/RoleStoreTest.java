package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenUsernameIsNikita() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Nikita"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Nikita");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Nikita"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsNikita() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Nikita"));
        store.add(new Role("1", "Oleg"));
        Role rsl = store.findById("1");
        assertThat(rsl.getUsername()).isEqualTo("Nikita");
    }

    @Test
    void whenReplaceThenUsernameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Nikita"));
        store.replace("1", new Role("1", "Maxim"));
        Role rsl = store.findById("1");
        assertThat(rsl.getUsername()).isEqualTo("Maxim");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Nikita"));
        store.replace("2", new Role("1", "Maxim"));
        Role rsl = store.findById("1");
        assertThat(rsl.getUsername()).isEqualTo("Nikita");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Nikita"));
        store.delete("1");
        Role rsl = store.findById("1");
        assertThat(rsl).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsNikita() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Nikita"));
        store.add(new Role("2", "Oleg"));
        store.add(new Role("3", "Petr"));
        store.delete("5");
        Role rsl = store.findById("1");
        assertThat(rsl.getUsername()).isEqualTo("Nikita");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Nikita"));
        store.add(new Role("2", "Oleg"));
        boolean bool = store.replace("2", new Role("2", "Maxim"));
        Role rsl = store.findById("2");
        assertThat(bool).isTrue();
        assertThat(rsl.getId()).isEqualTo("2");
        assertThat(rsl.getUsername()).isEqualTo("Maxim");
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Nikita"));
        store.add(new Role("2", "Oleg"));
        boolean bool = store.delete("2");
        assertThat(bool).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Nikita"));
        store.add(new Role("2", "Oleg"));
        boolean bool = store.delete("0");
        assertThat(bool).isFalse();
    }
}