package com.family.services.commands;

import com.family.Constants;
import com.family.exceptions.PersonAlreadyExistException;
import com.family.exceptions.PersonDoesNotExistException;
import com.family.exceptions.WrongEntitiesNumberException;
import com.family.exceptions.WrongGenderException;
import com.family.services.PersonService;

public class AddChildCommand implements Command {
    @Override
    public void execute(String[] units, PersonService personService) throws WrongEntitiesNumberException {
        try {
            if (units.length == 4) {
                personService.addChild(units[2], units[3], units[1]);
                System.out.println(Constants.CHILD_ADDITION_SUCCEEDED);
            } else {
                throw new WrongEntitiesNumberException("Wrong number of entities in ADD_CHILD command.");
            }
        } catch (PersonDoesNotExistException e) {
            System.out.println(Constants.PERSON_NOT_FOUND);
        } catch (WrongGenderException | PersonAlreadyExistException e) {
            System.out.println(Constants.CHILD_ADDITION_FAILED);
        }
    }
}
