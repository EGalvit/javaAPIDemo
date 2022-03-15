package com.example.demo1.DataService;

import com.example.demo1.dbSource.InMemDB;
import com.example.demo1.dbSource.MariaDB;
import com.example.demo1.models.Person;

import java.util.List;

public class PersonDataService {

    public List<Person> getAllPersons() {
        MariaDB dbHandler = new MariaDB();
        return InMemDB.getInstance().getPersonList();
    }

    public Person getPerson(int personId) {
        return InMemDB.getInstance().getPerson(personId);
    }

    public int updatePerson(int personId, Person person) {
        return InMemDB.getInstance().updatePerson(personId, person);
    }

    public int deletePerson(int personId) {
        return InMemDB.deleteFromPersonList(personId);
    }

    public int addPerson(Person person) {
        return InMemDB.getInstance().insertIntoPersonList(person);
    }
}
