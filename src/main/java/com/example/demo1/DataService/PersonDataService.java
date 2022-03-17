package com.example.demo1.DataService;

import com.example.demo1.dbSource.InMemDB;
import com.example.demo1.dbSource.MariaDB;
import com.example.demo1.models.Person;

import java.util.List;

public class PersonDataService {

    public List<Person> getAllPersons() {
        MariaDB dbHandler = new MariaDB();
        return dbHandler.getAllPersons();
        //return InMemDB.getInstance().getPersonList();
    }

    public Person getPerson(int personId) {
        MariaDB dbHandler = new MariaDB();
        return dbHandler.getPerson(personId);
        //return InMemDB.getInstance().getPerson(personId);
    }

    public int updatePerson(Person person) {
        MariaDB dbHandler = new MariaDB();
        return dbHandler.putPerson(person);
        //return InMemDB.getInstance().updatePerson(person);
    }

    public int deletePerson(int personId) {
        MariaDB dbHandler = new MariaDB();
        return dbHandler.deletePerson(personId);
        //return InMemDB.deleteFromPersonList(personId);
    }

    public int addPerson(Person person) {
        MariaDB dbHandler = new MariaDB();
        return dbHandler.postPerson(person);
        //return InMemDB.getInstance().insertIntoPersonList(person);
    }
}
