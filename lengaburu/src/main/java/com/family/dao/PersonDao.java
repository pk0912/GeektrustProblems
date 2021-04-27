package com.family.dao;

import com.family.models.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonDao implements Dao<Person> {
    HashMap<String, Person> persons = new HashMap<>();

    @Override
    public Person get(String name) {
        return persons.getOrDefault(name, null);
    }

    @Override
    public List<Person> getAll() {
        return new ArrayList<>(persons.values());
    }

    @Override
    public void save(Person person) {
        persons.put(person.getName(), person);
    }

    @Override
    public void update(Person person, String[] params) {
    }

    @Override
    public void delete(Person person) {
        persons.remove(person.getName());
    }
}
