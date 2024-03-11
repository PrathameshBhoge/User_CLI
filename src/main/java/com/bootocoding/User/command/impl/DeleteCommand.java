package com.bootocoding.User.command.impl;

import com.bootocoding.User.command.Command;
import com.bootocoding.User.command.validator.CommandValidator;
import com.bootocoding.User.model.Result;

public class DeleteCommand implements Command, CommandValidator {
    @Override
    public Result execute(String[] attributes) throws Exception {
        return null;
    }

    @Override
    public boolean validate(String[] attributes) throws Exception {
        return false;
    }
}
