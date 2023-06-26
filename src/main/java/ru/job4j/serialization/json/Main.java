package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.io.Contact;
import ru.job4j.io.Worker;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7930\"}");
        List<String> list = new ArrayList<>();
        list.add("Ksenia");
        list.add("Tatiana");
        JSONArray jsonStaffs = new JSONArray(list);
        final Worker worker = new Worker(true, 50000, "Nikita",
                new Contact(610, "+7930"), new String[] {"Ksenia", "Tatiana"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("employed", worker.getEmployed());
        jsonObject.put("salary", worker.getSalary());
        jsonObject.put("name", worker.getName());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("staffs", jsonStaffs);

        System.out.println(jsonObject);
        System.out.println(new JSONObject(worker));

    }
}
