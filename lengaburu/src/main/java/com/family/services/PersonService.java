package com.family.services;

import com.family.dao.PersonDao;
import com.family.exceptions.PersonAlreadyExistException;
import com.family.exceptions.PersonDoesNotExistException;
import com.family.exceptions.WrongGenderException;
import com.family.models.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonService {
    public static int id = 0;
    private final PersonDao personDao;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void sortPersons(List<Person> persons) {
        // Sort the List of persons so that result can be displayed in the order in which Persons were added in the Family Tree.
        Collections.sort(persons);
    }

    public void addSpouse(String personName, String spouseName) throws PersonDoesNotExistException {
        // Set the value of spouse in the passed person objects.
        Person person = personDao.get(personName);
        Person spouse = personDao.get(spouseName);
        if (person == null || spouse == null) {
            throw new PersonDoesNotExistException("Person does not exist.");
        } else {
            person.setSpouse(spouse);
            spouse.setSpouse(person);
        }
    }

    public void addPerson(String name, String gender, String motherName, String fatherName, String spouseName) throws PersonAlreadyExistException {
        // Method to add a person with all its attributes passed in here.
        if (personDao.get(name) != null) {
            throw new PersonAlreadyExistException("Person : " + name + " already exist");
        }
        Person mother = personDao.get(motherName);
        Person father = personDao.get(fatherName);
        Person spouse = personDao.get(spouseName);
        Person person = new Person(id, name, gender.toLowerCase().trim(), mother);
        id += 1;
        person.setFather(father);
        person.setSpouse(spouse);
        personDao.save(person);
    }

    public void addChild(String name, String gender, String motherName) throws PersonDoesNotExistException, WrongGenderException, PersonAlreadyExistException {
        // Method to add a child if the mother is already there in the family tree.
        if (personDao.get(name) != null) {
            throw new PersonAlreadyExistException("Person : " + name + " already exist");
        }
        Person mother = personDao.get(motherName);
        if (mother != null) {
            if (mother.getGender().equals("female")) {
                Person person = new Person(id, name, gender.toLowerCase().trim(), mother);
                id += 1;
                Person father = mother.getSpouse();
                person.setFather(father);
                personDao.save(person);
            }
            else {
                throw new WrongGenderException("Mother : " + motherName + " must be female.");
            }
        } else {
            throw new PersonDoesNotExistException("Person : " + motherName + " does not exist.");
        }
    }

    public Person getPersonDetails(String name) throws PersonDoesNotExistException {
        // Returns person details if person is already added in the family tree else throws exception.
        Person person = personDao.get(name);
        if (person != null) {
            return person;
        } else {
            throw new PersonDoesNotExistException("Person : " + name + " does not exist.");
        }
    }

    public List<Person> getSiblings(String name) throws PersonDoesNotExistException {
        // Returns List of Siblings for the passed person name and throws exception if passed person is not in family tree.
        Person person = getPersonDetails(name);
        List<Person> siblings = new ArrayList<>();
        if (person.getMother() != null) {
            for (Person p : personDao.getAll()) {
                if (p != person && p.getMother() == person.getMother()) {
                    siblings.add(p);
                }
            }
        }
        sortPersons(siblings);
        return siblings;
    }

    public List<Person> getBrothers(String name) throws PersonDoesNotExistException {
        // Returns List of Brothers for the passed person name and throws exception if passed person is not in family tree.
        List<Person> brothers = new ArrayList<>();
        for (Person p: getSiblings(name)) {
            if (p.getGender().equals("male")) {
                brothers.add(p);
            }
        }
        return brothers;
    }

    public List<Person> getSisters(String name) throws PersonDoesNotExistException {
        // Returns List of Sisters for the passed person name and throws exception if passed person is not in family tree.
        List<Person> sisters = new ArrayList<>();
        for (Person p: getSiblings(name)) {
            if (p.getGender().equals("female")) {
                sisters.add(p);
            }
        }
        return sisters;
    }

    public List<Person> getSons(String name) throws PersonDoesNotExistException {
        // Returns List of sons for the passed person name and throws exception if passed person is not in family tree.
        Person person = getPersonDetails(name);
        List<Person> sons = new ArrayList<>();
        if (person.getGender().equals("male")) {
            for (Person p: personDao.getAll()) {
                if (p.getFather() == person && p.getGender().equals("male")) {
                    sons.add(p);
                }
            }
        } else {
            for (Person p: personDao.getAll()) {
                if (p.getMother() == person && p.getGender().equals("male")) {
                    sons.add(p);
                }
            }
        }
        sortPersons(sons);
        return sons;
    }

    public List<Person> getDaughters(String name) throws PersonDoesNotExistException {
        // Returns List of daughters for the passed person name and throws exception if passed person is not in family tree.
        Person person = getPersonDetails(name);
        List<Person> daughters = new ArrayList<>();
        if (person.getGender().equals("male")) {
            for (Person p: personDao.getAll()) {
                if (p.getFather() == person && p.getGender().equals("female")) {
                    daughters.add(p);
                }
            }
        } else {
            for (Person p: personDao.getAll()) {
                if (p.getMother() == person && p.getGender().equals("female")) {
                    daughters.add(p);
                }
            }
        }
        sortPersons(daughters);
        return daughters;
    }

    public List<Person> getPaternalUncles(String name) throws PersonDoesNotExistException {
        // Returns List of paternal uncles for the passed person name and throws exception if passed person is not in family tree.
        Person person = getPersonDetails(name);
        List<Person> paternalUncles = new ArrayList<>();
        Person father = person.getFather();
        if (father != null) {
            paternalUncles = getBrothers(father.getName());
        }
        return paternalUncles;
    }

    public List<Person> getMaternalUncles(String name) throws PersonDoesNotExistException {
        // Returns List of maternal uncles for the passed person name and throws exception if passed person is not in family tree.
        Person person = getPersonDetails(name);
        List<Person> maternaluncles = new ArrayList<>();
        Person mother = person.getMother();
        if (mother != null) {
            maternaluncles = getBrothers(mother.getName());
        }
        return maternaluncles;
    }

    public List<Person> getPaternalAunts(String name) throws PersonDoesNotExistException {
        // Returns List of paternal aunt for the passed person name and throws exception if passed person is not in family tree.
        Person person = getPersonDetails(name);
        List<Person> paternalAunts = new ArrayList<>();
        Person father = person.getFather();
        if (father != null) {
            paternalAunts = getSisters(father.getName());
        }
        return paternalAunts;
    }

    public List<Person> getMaternalAunts(String name) throws PersonDoesNotExistException {
        // Returns List of maternal aunt for the passed person name and throws exception if passed person is not in family tree.
        Person person = getPersonDetails(name);
        List<Person> maternalAunts = new ArrayList<>();
        Person mother = person.getMother();
        if (mother != null) {
            maternalAunts = getSisters(mother.getName());
        }
        return maternalAunts;
    }

    public List<Person> getSistersInLaw(String name) throws PersonDoesNotExistException {
        // Returns List of sisters in law for the passed person name and throws exception if passed person is not in family tree.
        Person person = getPersonDetails(name);
        List<Person> sistersInLaw = new ArrayList<>();
        Person spouse = person.getSpouse();
        if (spouse != null) {
            sistersInLaw = getSisters(spouse.getName());
        }
        List<Person> brothers = getBrothers(name);
        for (Person p: brothers) {
            Person brothersWife = p.getSpouse();
            if (brothersWife != null) {
                sistersInLaw.add(brothersWife);
            }
        }
        sortPersons(sistersInLaw);
        return sistersInLaw;
    }

    public List<Person> getBrothersInLaw(String name) throws PersonDoesNotExistException {
        // Returns List of brothers in law for the passed person name and throws exception if passed person is not in family tree.
        Person person = getPersonDetails(name);
        List<Person> brothersInLaw = new ArrayList<>();
        Person spouse = person.getSpouse();
        if (spouse != null) {
            brothersInLaw = getBrothers(spouse.getName());
        }
        List<Person> sisters = getSisters(name);
        for (Person p: sisters) {
            Person sistersHusband = p.getSpouse();
            if (sistersHusband != null) {
                brothersInLaw.add(sistersHusband);
            }
        }
        sortPersons(brothersInLaw);
        return brothersInLaw;
    }

}
