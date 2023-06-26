package ru.job4j.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Worker {
    private boolean employed;
    private int salary;
    private String name;
    private Contact selfContact;
    private String[] staff;

    public Worker(boolean employed, int salary, String name, Contact selfContact, String[] staff) {
        this.employed = employed;
        this.salary = salary;
        this.name = name;
        this.selfContact = selfContact;
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "Worker{"
                + "employed=" + employed
                + ", salary=" + salary
                + ", name='" + name + '\''
                + ", selfContact=" + selfContact
                + ", staff=" + Arrays.toString(staff)
                + '}';
    }

    public static void main(String[] args) {
        final Worker worker = new Worker(true, 50000, "Nikita",
                new Contact(610, "+7930"), new String[] {"Ksenia", "Tatiana"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(worker));
        final String secondWorker =
                "{"
                    + "\"employed\":true,"
                    + "\"salary\":35000,"
                    + "\"name\":\"Oleg\","
                    + "\"selfContact\":"
                    + "{"
                    + "\"zipCode\":305,\"phone\":\"+7991\""
                    + "},"
                    + "\"staff\":[\"Maksim\",\"Pavel\"]"
                    + "}";
        final Worker workerMod = gson.fromJson(secondWorker, Worker.class);
        System.out.println(workerMod);
    }
}
