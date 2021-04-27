package com.ledgerCo;

import com.ledgerCo.dao.BankDao;
import com.ledgerCo.dao.BorrowerDao;
import com.ledgerCo.dao.LoanDao;
import com.ledgerCo.exceptions.*;
import com.ledgerCo.services.BankService;
import com.ledgerCo.services.BorrowerService;
import com.ledgerCo.services.LoanService;
import com.ledgerCo.services.commandHandler.Command;
import com.ledgerCo.services.commandHandler.CommandFactory;

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

            BankDao bankDao = new BankDao();
            BankService bankService = new BankService(bankDao);

            BorrowerDao borrowerDao = new BorrowerDao();
            BorrowerService borrowerService = new BorrowerService(borrowerDao);

            LoanDao loanDao = new LoanDao();
            LoanService loanService = new LoanService(loanDao);


            for (String command : inputs) {
                String[] units = command.split(" ");
                if (units.length > 0) {
                    Command cmd = CommandFactory.getCommand(units[0].trim().toLowerCase(), loanService, bankService, borrowerService);
                    cmd.execute(units);
                } else {
                    throw new WrongEntitiesNumberException("Wrong number of entities passed in command.");
                }
            }
        } catch (IOException | NoInputException | WrongCommandException | WrongEntitiesNumberException | LoanDoesNotExistException | EMIDoesNotExistException | LoanAlreadyGivenException e) {
            e.printStackTrace();
        }
    }
}
