package com.bootocoding.User.command.impl;

import com.bootocoding.User.command.Command;
import com.bootocoding.User.command.validator.CommandValidator;
import com.bootocoding.User.model.Result;
import com.bootocoding.User.model.User;
import com.bootocoding.User.store.InMemoryStore;

import java.util.ArrayList;
import java.util.List;

public class ReadCommand implements Command, CommandValidator {
    @Override
    public Result execute(String[] attributes) throws Exception {
        if (validate(attributes)) {
            String nameToSearch = null;


            for (int i = 1; i < attributes.length; i = i + 2) {
                if (attributes[i].equals("-n")) {
                    nameToSearch = attributes[i + 1];
                    break;
                }
            }

            if (nameToSearch != null) {
                // Search for user by name
                List<User> usersWithName = new ArrayList<>();

                //logic
                for (User user : InMemoryStore.users) {
                    if (user.getName().equals(nameToSearch)) {
                        usersWithName.add(user);
                    }
                }


                return Result.builder().message("SUCCESS").users(usersWithName).build();
            } else {
                return Result.builder().message("Name not provided").build();
            }
        }

        return Result.builder().message("Invalid command arguments").build();

    }

    @Override
    public boolean validate(String[] attributes) throws Exception {
        if (attributes.length % 2 == 0) {
            throw new Exception("Invalid number of attributes.");
        }


        if (!attributes[0].equals("read")) {
            throw new Exception("Action is not 'read'.");
        }

        boolean isNameProvided = false;
        for (int i = 1; i < attributes.length; i = i + 2) {
            if (attributes[i].equals("-n")) {
                isNameProvided = true;
                break;
            }
        }

        if (!isNameProvided) {
            throw new Exception("Name attribute is missing.");
        }

        return true;

    }

}
