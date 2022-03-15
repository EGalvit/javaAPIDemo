package com.example.demo1.dbSource;

import com.example.demo1.models.Person;

import java.util.ArrayList;
import java.util.List;

public class InMemDB {

    private static int nextId = 1000;
    private static List<Person> personList;
    private static InMemDB instance;

    private static int getNextId(){
        return nextId++;
    }

    private InMemDB() {
        personList = new ArrayList<>();
        personList.add(new Person(getNextId(), "Eric ceo", "Eric@ceo.dk", "Hilsen Eric ceo"));
    }

    public static synchronized InMemDB getInstance() {
        if (instance == null) {
            instance = new InMemDB();
        }
        return instance;
    }

    public static List<Person> getPersonList() {
        return personList;
    }

    public Person getPerson(int personId) {
        for(Person p: personList) {
            if(p.getPersonId() == personId) {
                return p;
            }
        }
        return null;
    }

    public int updatePerson(int personId, Person person) {
        for(Person p: personList) {
            if(p.getPersonId() == personId) {
                person.setPersonId(personId);
                personList.set(personList.indexOf(p), person);
                return 1;
            }
        }
        return 0;
    }

    public static int deleteFromPersonList(int personId) {
        for(Person p: personList) {
            if(p.getPersonId() == personId) {
                personList.remove(p);
                return 1;
            }
        }
        return 0;
    }

    public int insertIntoPersonList(Person person) {
        person.setPersonId(getNextId());
        personList.add(person);
        return 1;
    }
}
