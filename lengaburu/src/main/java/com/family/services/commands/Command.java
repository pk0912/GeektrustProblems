package com.family.services.commands;

import com.family.exceptions.WrongEntitiesNumberException;
import com.family.services.PersonService;

public interface Command {
    void execute(String[] units, PersonService personService) throws WrongEntitiesNumberException;
}
