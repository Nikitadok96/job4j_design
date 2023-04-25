package ru.job4j.hash;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        User firstUser = new User("Nikita", 0, birthday);
        int hashCodeFirst = firstUser.hashCode();
        int hashFirst = hashCodeFirst ^ (hashCodeFirst >>> 16);
        int bucketFirst = hashFirst & 15;
        System.out.printf("UserFirst. HashCode = %s, Hash = %s, Bucket = %s%n",
                hashCodeFirst, hashFirst, bucketFirst);
        User secondUser = new User("Nikita", 0, birthday);
        int hashCodeSecond = secondUser.hashCode();
        int hashSecond = hashCodeSecond ^ (hashCodeSecond >>> 16);
        int bucketSecond = hashSecond & 15;
        System.out.printf("UserSecond. HashCode = %s, Hash = %s, Bucket = %s",
                hashCodeSecond, hashSecond, bucketSecond);
        Map<User, Object> map = new HashMap<>(16);
        map.put(firstUser, new Object());
        map.put(secondUser, new Object());

    }
}
