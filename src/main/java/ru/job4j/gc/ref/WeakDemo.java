package ru.job4j.gc.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeakDemo {
    public static void main(String[] args) throws InterruptedException {
        //example1();
        //example2();
        example3();
    }

    private static void example1() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }

            @Override
            public String toString() {
                return "Object";
            }
        };
        WeakReference<Object> weak = new WeakReference<>(object);
        object = null;
        Object on = weak.get();
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(weak.get());
        System.out.println(on);
    }

    private static void example2() throws InterruptedException {
        List<WeakReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            objects.add(new WeakReference<Object>(new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Removed!");
                }
            }));
        }
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        Object ob = objects.get(0).get();
        System.out.println(ob == null ? "null" : ob);
    }

    private static void example3() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }

            @Override
            public String toString() {
                return "Ob";
            }
        };
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weak = new WeakReference<>(object, queue);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        Object ob = weak.get();
        if (ob != null) {

        } else {
            System.out.println("Pool");
            ob = queue.poll();
        }
        System.out.println(ob);
    }
}
