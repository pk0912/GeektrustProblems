package com.family;

import com.family.dao.PersonDao;
import com.family.exceptions.NoInputException;
import com.family.exceptions.WrongCommandException;
import com.family.exceptions.WrongEntitiesNumberException;
import com.family.services.ExistingFamilyTree;
import com.family.services.PersonService;
import com.family.services.commands.Command;
import com.family.services.commands.CommandFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<String> readFile(String[] args) throws IOException, NoInputException {
        if (args.length > 0) {
            List<String> inputs = new ArrayList<>();
            File file = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                if (!st.trim().equals("")) {
                    inputs.add(st);
                }
            }
            return inputs;
        } else {
            throw new NoInputException("No File path provided.");
        }
    }


    public static void main(String[] args) {
        try {
            List<String> inputs = readFile(args);
            PersonDao personDao = new PersonDao();
            PersonService personService = new PersonService(personDao);

            ExistingFamilyTree.initialize(personService);

            for (String command : inputs) {
                String[] units = command.split(" ");
                if (units.length > 0) {
                    Command cmd = CommandFactory.getCommand(units[0].trim().toLowerCase());
                    cmd.execute(units, personService);
                } else {
                    throw new WrongEntitiesNumberException("Wrong number of entities passed in command.");
                }
            }
        } catch (NoInputException | IOException | WrongCommandException | WrongEntitiesNumberException e) {
            e.printStackTrace();
        }
    }
}
