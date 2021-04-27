package com.family.services;

import com.family.exceptions.PersonAlreadyExistException;
import com.family.exceptions.PersonDoesNotExistException;
import com.family.exceptions.WrongGenderException;

public class ExistingFamilyTree {

    public static void initialize(PersonService personService) {
        try {
            personService.addPerson("King Shan", "male", "", "", "");
            personService.addPerson("Queen Anga", "female", "", "", "");
            personService.addSpouse("King Shan", "Queen Anga");

            personService.addChild("Chit", "male", "Queen Anga");
            personService.addPerson("Amba", "female", "", "", "");
            personService.addSpouse("Chit", "Amba");
            personService.addChild("Ish", "male", "Queen Anga");
            personService.addChild("Vich", "male", "Queen Anga");
            personService.addPerson("Lika", "female", "", "", "");
            personService.addSpouse("Vich", "Lika");
            personService.addChild("Aras", "male", "Queen Anga");
            personService.addPerson("Chitra", "female", "", "", "");
            personService.addSpouse("Aras", "Chitra");
            personService.addChild("Satya", "female", "Queen Anga");
            personService.addPerson("Vyan", "male", "", "", "");
            personService.addSpouse("Satya", "Vyan");

            personService.addChild("Dritha", "female", "Amba");
            personService.addPerson("Jaya", "male", "", "", "");
            personService.addSpouse("Dritha", "Jaya");
            personService.addChild("Tritha", "female", "Amba");
            personService.addChild("Vritha", "male", "Amba");
            personService.addChild("Vila", "female", "Lika");
            personService.addChild("Chika", "female", "Lika");
            personService.addPerson("Arit", "male", "", "", "");
            personService.addChild("Jnki", "female", "Chitra");
            personService.addSpouse("Arit", "Jnki");
            personService.addChild("Ahit", "male", "Chitra");
            personService.addPerson("Satvy", "female", "", "", "");
            personService.addChild("Asva", "male", "Satya");
            personService.addSpouse("Satvy", "Asva");
            personService.addPerson("Krpi", "female", "", "", "");
            personService.addChild("Vyas", "male", "Satya");
            personService.addSpouse("Krpi", "Vyas");
            personService.addChild("Atya", "female", "Satya");

            personService.addChild("Yodhan", "male", "Dritha");
            personService.addChild("Laki", "male", "Jnki");
            personService.addChild("Lavnya", "female", "Jnki");
            personService.addChild("Vasa", "male", "Satvy");
            personService.addChild("Kriya", "male", "Krpi");
            personService.addChild("Krithi", "female", "Krpi");
        }
        catch (PersonAlreadyExistException | PersonDoesNotExistException | WrongGenderException e) {
            e.printStackTrace();
        }
    }
}
