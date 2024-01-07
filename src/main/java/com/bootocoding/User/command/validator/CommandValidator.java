package com.bootocoding.User.command.validator;

public interface CommandValidator {
    public boolean validate(String[] attributes) throws Exception;
}
