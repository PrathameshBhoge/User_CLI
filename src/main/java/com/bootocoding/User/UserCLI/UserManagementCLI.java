package com.bootocoding.User.UserCLI;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManagementCLI {
    private Map<Integer, User> users;

    public UserManagementCLI() {
        users = new HashMap<>();
    }

    // Create operation
    public void createUser(int id, String name, String email, String phoneNumber) {
        User newUser = new User(id, name, email, phoneNumber);
        users.put(id, newUser);
        System.out.println("User created: " + newUser);
    }

    // Read operation
    public void readUser(int id) {
        User user = users.get(id);
        if (user != null) {
            System.out.println("User details: " + user);
        } else {
            System.out.println("User not found with ID: " + id);
        }
    }

    // Update operation
    public void updateUser(int id, String name, String email, String phoneNumber) {
        User user = users.get(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            System.out.println("User updated: " + user);
        } else {
            System.out.println("User not found with ID: " + id);
        }
    }

    // Delete operation
    public void deleteUser(int id) {
        User removedUser = users.remove(id);
        if (removedUser != null) {
            System.out.println("User deleted: " + removedUser);
        } else {
            System.out.println("User not found with ID: " + id);
        }
    }

    public static void main(String[] args) {
        UserManagementCLI userManagement = new UserManagementCLI();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter operation (create/read/update/delete/exit):");
            String operation = scanner.nextLine();

            if (operation.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            switch (operation.toLowerCase()) {
                case "create":
                    System.out.println("Enter user ID:");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter email:");
                    String email = scanner.nextLine();
                    System.out.println("Enter phone number:");
                    String phoneNumber = scanner.nextLine();
                    userManagement.createUser(id, name, email, phoneNumber);
                    break;
                case "read":
                    System.out.println("Enter user ID:");
                    int readId = Integer.parseInt(scanner.nextLine());
                    userManagement.readUser(readId);
                    break;
                case "update":
                    System.out.println("Enter user ID:");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter new name:");
                    String newName = scanner.nextLine();
                    System.out.println("Enter new email:");
                    String newEmail = scanner.nextLine();
                    System.out.println("Enter new phone number:");
                    String newPhoneNumber = scanner.nextLine();
                    userManagement.updateUser(updateId, newName, newEmail, newPhoneNumber);
                    break;
                case "delete":
                    System.out.println("Enter user ID:");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    userManagement.deleteUser(deleteId);
                    break;
                default:
                    System.out.println("Invalid operation. Try again.");
                    break;
            }
        }

        scanner.close();
    }
}

