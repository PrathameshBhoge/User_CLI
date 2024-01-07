package com.bootocoding.User.command.impl;

import com.bootocoding.User.command.Command;
import com.bootocoding.User.command.validator.CommandValidator;
import com.bootocoding.User.model.Result;
import com.bootocoding.User.model.User;
import com.bootocoding.User.store.InMemoryStore;

import java.util.UUID;

public class CreateCommand implements Command, CommandValidator {

    @Override
    public Result execute(String[] attributes) throws Exception {
        if(validate(attributes)){
            // start processing
            User user = User.builder()
                    .id(UUID.randomUUID().toString())
                    .build();
            for(int i = 1; i < attributes.length ; i = i + 2) {
                String attrName = attributes[i];
                setAttributeValue(user, attrName, attributes[i + 1]);
            }
            InMemoryStore.users.add(user);
            return Result.builder().message("SUCCESS").users(InMemoryStore.users).build();
        }
        return Result.builder().message("Invalid command arguments").build();
    }

    @Override
    public boolean validate(String[] attributes) throws Exception {
        if(attributes.length != 9){
            throw new Exception("Please provide all attributes: " +
                    "For ex: \ncreate -n \"Ramesh\" -p 8989 -a " +
                    "\"Nagpur Kdk college cha maaage\" -e \"iamcoder@gmail.com\"");
        }
        if(!attributes[0].equals("create")){
            throw new Exception("Action is not create!");
        }
        boolean isValid = true;
        int i = 1;
        while(isValid && i < attributes.length){
            String attrName = attributes[i];
            isValid = validateAttributes(attrName);
            i = i + 2;
        }

        return isValid;
    }

    private boolean validateAttributes(String attrName) {
        switch (attrName){
            case "-n":
                return true;
            case "-p":
                return true;
            case "-a":
                return true;
            case "-e":
                return true;
            default:
                return false;

        }
    }


    private void setAttributeValue(User user, String attrName, String value) throws Exception{
        switch (attrName){
            case "-n":
                user.setName(value);
                break;
            case "-p":
                user.setPhone(Long.valueOf(value));
                break;
            case "-a":
                user.setAddress(value);
                break;
            case "-e":
                user.setEmailId(value);
                break;
            default:
                throw new Exception("Invalid command attribute format!");

        }
    }

}
