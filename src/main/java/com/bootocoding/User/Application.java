package com.bootocoding.User;

import com.bootocoding.User.command.Command;
import com.bootocoding.User.command.impl.CreateCommand;
import com.bootocoding.User.command.impl.ExitCommand;
import com.bootocoding.User.command.impl.ReadCommand;
import com.bootocoding.User.command.impl.UpdateCommand;
import com.bootocoding.User.model.Result;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        while (true) {

            Scanner sc = new Scanner(System.in);

            System.out.println("To create User, command should be like ::: create -n name -p phone -a addr -e emailId");

            System.out.println("You are free to enter any command!");

            String commandStr = sc.nextLine();

            String[] commandWithAttrs = commandStr.split(" ");

            Command command = findCommand(commandWithAttrs[0]);
            if(
                    null == command || command instanceof ExitCommand){
                break;
            }
            try {
                Result result = command.execute(commandWithAttrs);
                System.out.println(result);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    private static Command findCommand(String cmd) {
        switch (cmd){
            case "create":
                Command command = new CreateCommand();
                return command;
            case "read":
                Command command1=new ReadCommand();
                return command1;
            case "update":
                Command command2=new UpdateCommand();
            default:
                return null;
        }
    }
}
