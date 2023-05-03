package ru.job4j.hash;

import java.util.HashMap;
import java.util.Objects;

public class Student {
    private String name;
    private String id;
    private int course;

    public Student(String name, String id, int course) {
        this.name = name;
        this.id = id;
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return course == student.course && Objects.equals(name, student.name) && Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(course);
        result = 31 * result * name.hashCode();
        result = 31 * result * id.hashCode();
        return result;
    }

    public static void main(String[] args) {
        HashMap<Student, String> map = new HashMap<>();
        map.put(new Student("Nikita", "345-543", 2), "Junior");
        boolean rsl = map.get(new Student("Nikita", "345-543", 2)).equals("Junior");
        System.out.println("Result is: " + rsl);
    }
}
