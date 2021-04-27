package com.family.services.commands;

import com.family.Constants;
import com.family.exceptions.PersonDoesNotExistException;
import com.family.exceptions.WrongEntitiesNumberException;
import com.family.models.Person;
import com.family.services.PersonService;

import java.util.ArrayList;
import java.util.List;

public class GetRelationshipCommand implements Command {
    @Override
    public void execute(String[] units, PersonService personService) throws WrongEntitiesNumberException {
        try {
            if (units.length == 3) {
                List<Person> persons = new ArrayList<>();
                switch (units[2]) {
                    case Constants.Son:
                        persons = personService.getSons(units[1]);
                        break;
                    case Constants.Daughter:
                        persons = personService.getDaughters(units[1]);
                        break;
                    case Constants.Siblings:
                        persons = personService.getSiblings(units[1]);
                        break;
                    case Constants.PaternalUncle:
                        persons = personService.getPaternalUncles(units[1]);
                        break;
                    case Constants.MaternalUncle:
                        persons = personService.getMaternalUncles(units[1]);
                        break;
                    case Constants.PaternalAunt:
                        persons = personService.getPaternalAunts(units[1]);
                        break;
                    case Constants.MaternalAunt:
                        persons = personService.getMaternalAunts(units[1]);
                        break;
                    case Constants.SisterInLaw:
                        persons = personService.getSistersInLaw(units[1]);
                        break;
                    case Constants.BrotherInLaw:
                        persons = personService.getBrothersInLaw(units[1]);
                        break;
                    default:
                        System.out.println(Constants.NONE);
                        break;
                }
                if (persons.size() == 0) {
                    System.out.println(Constants.NONE);
                } else {
                    StringBuilder personNames = new StringBuilder();
                    for (Person p : persons) {
                        personNames.append(p.getName()).append(" ");
                    }
                    System.out.println(personNames.toString().trim());
                }

            }
            else {
                throw new WrongEntitiesNumberException("Wrong number of entities in GET_RELATIONSHIP command.");
            }
        } catch (PersonDoesNotExistException e) {
            System.out.println(Constants.PERSON_NOT_FOUND);
        }
    }
}
