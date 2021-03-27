package main.java.LinkedInventoryManagement.PersistentStorage;

import main.java.LinkedInventoryManagement.Common.*;
import main.java.LinkedInventoryManagement.Product.*;
import main.java.LinkedInventoryManagement.Security.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * UsersOperations: This class should contain all the Users.dat file read, write 
 * and update operations.
 */
public class UsersOperations {

    //TODO: Add methods to read, write and update Users.dat
    //open file
    //get data
    //close file
    //return data to caller.

    // to return an instance
    private static UsersOperations object = null;

    public static UsersOperations GetUsersOperationsInstance()
    {
        if (object == null)
        {
            object = new UsersOperations();
        }
        return object;
    }

    public InventoryLinkedList<User> readUserFromFile() {
        InventoryLinkedList<User> userList = new InventoryLinkedList<User>();

        File userFile = new File("User.txt");
        StringTokenizer tokenizer;
        String line, firstName = null, lastName = null, lineNoCommas = "";
        // read from the user.dat file
        User user = new User("", "", false); // make empty user

        try (Scanner readFile = new Scanner(userFile)) {
            while (readFile.hasNextLine()) // loop over the whole file
            {
                line = readFile.nextLine();
                lineNoCommas = line.replaceAll(",", ""); // remove commas

                // separate line into tokens
                tokenizer = new StringTokenizer(lineNoCommas);

                // put the info from the line into user
                firstName = tokenizer.nextToken(); // first name
                // if the last name exists
                if (tokenizer.countTokens() == 4) // four tokens left, then last name exists
                    lastName = tokenizer.nextToken(); // adds last name

                // set the username, password and isManager
                user.setUserName(tokenizer.nextToken());
                user.setUserPassword(tokenizer.nextToken());

                user.setManager(Boolean.parseBoolean(tokenizer.nextToken()));
                user.setFirstName(firstName);
                user.setLastName(lastName);
                userList.addLast(user);
            }
        } catch (Exception ex) {
            // do what the instructions say to do if the file does not exist
            System.out.println("File does not exist");
        }

        return userList;
    }

    public void WriteUserToFile(User newUser)
    {
        // write in the username and hashed password to the user.dat file
        File userFile = new File("User.txt");
        try (PrintWriter fileWriter = new PrintWriter(new FileWriter(userFile, true)))
        {
            if (newUser.getLastName() != null)
            {
                fileWriter.append("\n").append(newUser.getFirstName()).append(", ")
                        .append(newUser.getLastName()).append(", ").append(newUser.getUsername()).append(", ").
                        append(newUser.getHashedPassword()).append(", ").append(String.valueOf(newUser.getManager()));
                System.out.println("\nUser created.");
                fileWriter.close();
            } else {
                fileWriter.append("\n").append(newUser.getFirstName()).append(", ")
                        .append(newUser.getUsername()).append(", ").append(newUser.getHashedPassword()).
                        append(", ").append(String.valueOf(newUser.getManager()));
                System.out.println("\nUser created.");
                fileWriter.close();
            }
        } catch (IOException ex)
        {
            System.out.println("File does not exist");
        }
    }
}