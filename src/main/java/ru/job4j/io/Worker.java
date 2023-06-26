package ru.job4j.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "worker")
@XmlAccessorType(XmlAccessType.FIELD)
public class Worker {
    @XmlAttribute
    private boolean employed;
    @XmlAttribute
    private int salary;
    private String name;
    private Contact selfContact;
    @XmlElementWrapper(name = "staffs")
    @XmlElement(name = "staff")
    private String[] staff;

    public Worker() {

    }

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

    public static void main(String[] args) throws JAXBException {
        final Worker worker = new Worker(true, 50000, "Nikita",
                new Contact(610, "+7930"), new String[] {"Ksenia", "Tatiana"});

        JAXBContext context = JAXBContext.newInstance(Worker.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        String xml = "";
        try (StringWriter stringWriter = new StringWriter()) {
            marshaller.marshal(worker, stringWriter);
            xml = stringWriter.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader stringReader = new StringReader(xml)) {
            Worker rsl = (Worker) unmarshaller.unmarshal(stringReader);
            System.out.println(rsl);
        }
    }
}
