package User;

import java.util.Scanner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Usermain {

    private String userFile; // Path to the user data file

    public Usermain(String userFile) {
        this.userFile = userFile;
    }

    public void createUser(int id, String name, String email, String phone) throws IOException {
        User user = new User(id, name, email, phone);
        List<User> users = readUsers(); // Read existing users, if any
        users.add(user);
        writeUsers(users); // Write updated user list to file
        System.out.println("User " + name + " (ID: " + id + ") created successfully!");
    }

    private List<User> readUsers() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Split on comma delimiter
                users.add(new User(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3]));
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, create an empty list
        }
        return users;
    }

    private void writeUsers(List<User> users) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
            for (User user : users) {
                writer.write(user.toString() + "\n"); // Write user data with newline
            }
        }
    }


}
