package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 8);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isHasFourVertex() {
        Box box = new Box(4, 8);
        int countVertex = box.getNumberOfVertices();
        assertThat(countVertex).isEqualTo(4)
                .isGreaterThan(3)
                .isLessThan(5);
    }

    @Test
    void isSphereHasLessThenFour() {
        Box box = new Box(0, 10);
        int countVertex = box.getNumberOfVertices();
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
        assertThat(countVertex).isLessThan(4);
    }

    @Test
    void thisBoxHas3VertexNotExist() {
        Box box = new Box(3, 3);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void thisBoxHas4VertexIsExist() {
        Box box = new Box(4, 10);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void thisBoxHas0EdgeNotExist() {
        Box box = new Box(4, 0);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void checkSphereArea() {
        Box box = new Box(0, 10);
        double rsl = box.getArea();
        assertThat(rsl).isCloseTo(1256, withinPercentage(0.1D))
                .isLessThan(1257)
                .isGreaterThan(1256);
    }

    @Test
    void checkUnknownArea() {
        Box box = new Box(3, 3);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(0);
    }
}