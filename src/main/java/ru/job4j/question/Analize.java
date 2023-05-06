package ru.job4j.question;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, User> prevMap = new HashMap<>();
        for (User user : previous) {
            prevMap.put(user.getId(), user);
        }
        for (User user : current) {
            User e = prevMap.get(user.getId());
            if (e == null) {
                added++;
            } else if (!e.getName().equals(user.getName())) {
                changed++;
            }
        }
        Map<Integer, User> currentMap = new HashMap<>();
        for (User user : current) {
            currentMap.put(user.getId(), user);
        }
        for (User user : previous) {
            User e = currentMap.get(user.getId());
            if (e == null) {
                deleted++;
            }
        }
        return new Info(added, changed, deleted);
    }
}
