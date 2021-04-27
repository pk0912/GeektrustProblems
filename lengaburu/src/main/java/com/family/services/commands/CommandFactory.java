package com.family.services.commands;

import com.family.exceptions.WrongCommandException;

public class CommandFactory {

    public static Command getCommand(String cmdName) throws WrongCommandException {
        Command command;
        switch (cmdName) {
            case "add_child":
                command = new AddChildCommand();
                break;
            case "get_relationship":
                command = new GetRelationshipCommand();
                break;
            default:
                throw new WrongCommandException("Wrong command entered. Valid options are loan/balance/payment");
        }
        return command;
    }
}
