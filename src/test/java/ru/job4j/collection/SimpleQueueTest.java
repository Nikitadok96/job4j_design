package ru.job4j.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class SimpleQueueTest {
    private SimpleQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new SimpleQueue<>();
        queue.push(1);
    }

    @Test
    void whenPushPoll() {
        assertThat(queue.poll()).isEqualTo(1);
    }

    @Test
    void when2PushPoll() {
        queue.push(2);
        assertThat(queue.poll()).isEqualTo(1);
    }

    @Test
    void when2PushPollPushPoll() {
        queue.poll();
        queue.push(2);
        assertThat(queue.poll()).isEqualTo(2);
    }

    @Test
    void whenEmptyPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        assertThatThrownBy(queue::poll)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Queue is empty");
    }

    @Test
    void when2PushPollPushPollEmpty() {
        queue.poll();
        queue.push(2);
        queue.poll();
        assertThatThrownBy(queue::poll)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Queue is empty");
    }

    @Test
    void whenPushPushPollAndPush() {
        queue.push(2);
        queue.poll();
        queue.push(3);
        assertThat(queue.poll()).isEqualTo(2);
    }

    @Test
    void when4PushAnd5Poll() {
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        assertThat(queue.poll()).isEqualTo(1);
        assertThat(queue.poll()).isEqualTo(2);
        assertThat(queue.poll()).isEqualTo(3);
        assertThat(queue.poll()).isEqualTo(4);
        assertThat(queue.poll()).isEqualTo(5);
    }

    @Test
    void when2Push1Poll2Push4Poll() {
        queue.push(2);
        queue.push(3);
        assertThat(queue.poll()).isEqualTo(1);
        queue.push(4);
        queue.push(5);
        assertThat(queue.poll()).isEqualTo(2);
        assertThat(queue.poll()).isEqualTo(3);
        assertThat(queue.poll()).isEqualTo(4);
        assertThat(queue.poll()).isEqualTo(5);
    }
}