package com.bootocoding.User.command.impl;

import com.bootocoding.User.command.Command;
import com.bootocoding.User.command.validator.CommandValidator;
import com.bootocoding.User.model.Result;
import com.bootocoding.User.model.User;
import com.bootocoding.User.store.InMemoryStore;

public class UpdateCommand implements Command, CommandValidator {
    @Override
    public Result execute(String[] attributes) throws Exception {
        if (validate(attributes)) {
            String userID = null;
            String fieldName = null;
            String value = null;

            for (int i = 1; i < attributes.length; i = i + 2) {
                switch (attributes[i]) {
                    case "-i":
                        userID = attributes[i + 1];
                        break;
                    case "-f":
                        fieldName = attributes[i + 1];
                        break;
                    case "-v":
                        value = attributes[i + 1];
                        break;
                }
            }

            if (userID != null && fieldName != null && value != null) {

                User userToUpdate = null;
                for (User user : InMemoryStore.users) {
                    if (user.getId().equals(userID)) {
                        userToUpdate = user;
                        break;
                    }
                }

                if (userToUpdate != null) {
                    updateField(userToUpdate, fieldName, value);
                    return Result.builder().message("User updated successfully").build();
                } else {
                    return Result.builder().message("User not found").build();
                }
            } else {
                return Result.builder().message("Missing attributes for update").build();
            }
        }
        return Result.builder().message("Invalid command arguments").build();
    }

    @Override
    public boolean validate(String[] attributes) throws Exception {
        if (attributes.length % 2 == 0) {
            throw new Exception("Invalid number of attributes.");
        }

        if (!attributes[0].equals("update")) {
            throw new Exception("Action is not 'update'.");
        }

        boolean isIDProvided = false;
        boolean isFieldProvided = false;
        boolean isValueProvided = false;

        for (int i = 1; i < attributes.length; i = i + 2) {
            switch (attributes[i]) {
                case "-i":
                    isIDProvided = true;
                    break;
                case "-f":
                    isFieldProvided = true;
                    break;
                case "-v":
                    isValueProvided = true;
                    break;
            }
        }

        if (!isIDProvided || !isFieldProvided || !isValueProvided) {
            throw new Exception("Missing required attributes for update.");
        }

        return true;
    }


    private void updateField(User user, String fieldName, String value) throws Exception {

        switch (fieldName) {
            case "name":
                user.setName(value);
                break;
            case "phone":
                user.setPhone(Long.parseLong(value));
                break;
            case "address":
                user.setAddress(value);
                break;
            case "email":
                user.setEmailId(value);
                break;
            default:
                throw new Exception("Invalid field name for update");
        }
    }
}

