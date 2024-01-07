package com.bootocoding.User.command;


import com.bootocoding.User.model.Result;

public interface Command {
    public Result execute(String[] attributes) throws Exception;
}
