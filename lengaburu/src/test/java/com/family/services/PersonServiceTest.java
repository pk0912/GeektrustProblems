package com.family.services;

import com.family.dao.PersonDao;
import com.family.exceptions.PersonDoesNotExistException;
import com.family.models.Person;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonServiceTest {

    @Test
    public void testGetSons() throws PersonDoesNotExistException {
        PersonDao personDao = new PersonDao();
        PersonService personService = new PersonService(personDao);
        ExistingFamilyTree.initialize(personService);
        List<Person> sons = personService.getSons("King Shan");
        StringBuilder sonNames = new StringBuilder();
        for (Person p: sons) {
            sonNames.append(p.getName()).append(" ");
        }
        assertEquals("Chit Ish Vich Aras", sonNames.toString().trim());
    }

    @Test
    public void testGetDaughters() throws PersonDoesNotExistException {
        PersonDao personDao = new PersonDao();
        PersonService personService = new PersonService(personDao);
        ExistingFamilyTree.initialize(personService);
        List<Person> daughters = personService.getDaughters("Lika");
        StringBuilder daughterNames = new StringBuilder();
        for (Person p: daughters) {
            daughterNames.append(p.getName()).append(" ");
        }
        assertEquals("Vila Chika", daughterNames.toString().trim());
    }

    @Test
    public void testGetSiblings() throws PersonDoesNotExistException {
        PersonDao personDao = new PersonDao();
        PersonService personService = new PersonService(personDao);
        ExistingFamilyTree.initialize(personService);
        List<Person> siblings = personService.getSiblings("Aras");
        StringBuilder siblingNames = new StringBuilder();
        for (Person p: siblings) {
            siblingNames.append(p.getName()).append(" ");
        }
        assertEquals("Chit Ish Vich Satya", siblingNames.toString().trim());
    }

    @Test
    public void testGetBrothers() throws PersonDoesNotExistException {
        PersonDao personDao = new PersonDao();
        PersonService personService = new PersonService(personDao);
        ExistingFamilyTree.initialize(personService);
        List<Person> brothers = personService.getBrothers("Ish");
        StringBuilder brotherNames = new StringBuilder();
        for (Person p: brothers) {
            brotherNames.append(p.getName()).append(" ");
        }
        assertEquals("Chit Vich Aras", brotherNames.toString().trim());
    }

    @Test
    public void testGetSisters() throws PersonDoesNotExistException {
        PersonDao personDao = new PersonDao();
        PersonService personService = new PersonService(personDao);
        ExistingFamilyTree.initialize(personService);
        List<Person> sisters = personService.getSisters("Vritha");
        StringBuilder sisterNames = new StringBuilder();
        for (Person p: sisters) {
            sisterNames.append(p.getName()).append(" ");
        }
        assertEquals("Dritha Tritha", sisterNames.toString().trim());
    }

    @Test
    public void testGetPaternalUncles() throws PersonDoesNotExistException {
        PersonDao personDao = new PersonDao();
        PersonService personService = new PersonService(personDao);
        ExistingFamilyTree.initialize(personService);
        List<Person> paternalUncles = personService.getPaternalUncles("Tritha");
        StringBuilder paternalUncleNames = new StringBuilder();
        for (Person p: paternalUncles) {
            paternalUncleNames.append(p.getName()).append(" ");
        }
        assertEquals("Ish Vich Aras", paternalUncleNames.toString().trim());
    }

    @Test
    public void testGetMaternalUncles() throws PersonDoesNotExistException {
        PersonDao personDao = new PersonDao();
        PersonService personService = new PersonService(personDao);
        ExistingFamilyTree.initialize(personService);
        List<Person> maternalUncles = personService.getMaternalUncles("Atya");
        StringBuilder maternalUncleNames = new StringBuilder();
        for (Person p: maternalUncles) {
            maternalUncleNames.append(p.getName()).append(" ");
        }
        assertEquals("Chit Ish Vich Aras", maternalUncleNames.toString().trim());
    }

    @Test
    public void testGetPaternalAunts() throws PersonDoesNotExistException {
        PersonDao personDao = new PersonDao();
        PersonService personService = new PersonService(personDao);
        ExistingFamilyTree.initialize(personService);
        List<Person> paternalAunts = personService.getPaternalAunts("Kriya");
        StringBuilder paternalAuntNames = new StringBuilder();
        for (Person p: paternalAunts) {
            paternalAuntNames.append(p.getName()).append(" ");
        }
        assertEquals("Atya", paternalAuntNames.toString().trim());
    }

    @Test
    public void testGetMaternalAunts() throws PersonDoesNotExistException {
        PersonDao personDao = new PersonDao();
        PersonService personService = new PersonService(personDao);
        ExistingFamilyTree.initialize(personService);
        List<Person> maternalAunts = personService.getMaternalAunts("Yodhan");
        StringBuilder maternalAuntNames = new StringBuilder();
        for (Person p: maternalAunts) {
            maternalAuntNames.append(p.getName()).append(" ");
        }
        assertEquals("Tritha", maternalAuntNames.toString().trim());
    }

    @Test
    public void testGetSistersInLaw() throws PersonDoesNotExistException {
        PersonDao personDao = new PersonDao();
        PersonService personService = new PersonService(personDao);
        ExistingFamilyTree.initialize(personService);
        List<Person> sistersInLaw = personService.getSistersInLaw("Satya");
        StringBuilder sistersInLawNames = new StringBuilder();
        for (Person p: sistersInLaw) {
            sistersInLawNames.append(p.getName()).append(" ");
        }
        assertEquals("Amba Lika Chitra", sistersInLawNames.toString().trim());
    }

    @Test
    public void testGetBrothersInLaw() throws PersonDoesNotExistException {
        PersonDao personDao = new PersonDao();
        PersonService personService = new PersonService(personDao);
        ExistingFamilyTree.initialize(personService);
        List<Person> brothersInLaw = personService.getBrothersInLaw("Tritha");
        StringBuilder brothersInLawNames = new StringBuilder();
        for (Person p: brothersInLaw) {
            brothersInLawNames.append(p.getName()).append(" ");
        }
        assertEquals("Jaya", brothersInLawNames.toString().trim());
    }
}
