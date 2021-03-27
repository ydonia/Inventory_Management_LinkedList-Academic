package main.java.LinkedInventoryManagement.Security;

import main.java.LinkedInventoryManagement.Common.InventoryLinkedList;
import main.java.LinkedInventoryManagement.PersistentStorage.UsersOperations;
import main.java.LinkedInventoryManagement.Security.*;

import java.io.*;
import java.net.UnknownServiceException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * InventoryManagementSecurity
 */
public class SecurityOperations 
{
    static InventoryLinkedList<User> userLinkedList = new InventoryLinkedList<User>();
    //TODO Add data fields, constructors and methods as needed. Consider implementing Comparable for the User class.



    public static User AuthenticateUser(String username, String password)
    {
        File userFile = new File("User.txt");
        User authenticatedUser = null;
        StringTokenizer tokenizer;
        boolean userFound = false;

        // check if user is admin
        if((username.compareToIgnoreCase("admin") == 0) && 
           (GetPasswordHash(password).compareToIgnoreCase("58c536ed8facc2c2a293a18a48e3e120") == 0))
        {
            authenticatedUser = new User(username, GetPasswordHash(password), true);
            authenticatedUser.setFirstName("admin");
            System.out.println("This user is the admin");
            userFound = true;
        }
        else
        {
            // read from the user.dat file
             userLinkedList = UsersOperations.GetUsersOperationsInstance().readUserFromFile();

                    // check if user exists based on the info gathered in the line
                    for (int i = 0; i < userLinkedList.getSize(); i++) {
                        if (userLinkedList.GetElement(i).getUsername().equals(username)
                                && userLinkedList.GetElement(i).getHashedPassword().equals(GetPasswordHash(password))) {

                            // if user exists
                            authenticatedUser = new User(username, GetPasswordHash(password), userLinkedList.GetElement(i).getManager());

                            userFound = true;
                        }
                    }
                }
                if (!userFound)
                {
                    System.out.println("User cannot be authenticated.");
                }
        return authenticatedUser; //Change the return value based on authentication result
    }

    public static void AddNewUser(User newUser)
    {
        //TODO hash password and save username and hashed password to Users.dat

        // hash the password
        newUser.setUserPassword(GetPasswordHash(newUser.getHashedPassword()));

        // write the user to file
        UsersOperations.GetUsersOperationsInstance().WriteUserToFile(newUser);

    }
    
    public static void RemoveUser(String userName)
    {
        //TODO remove username from Users.dat

        // check if user exists in Users.dat
        File userFile = new File("User.txt");
        Scanner readFile;
        String line = null;
        String updatedFile = null;
        boolean isDeleted;
        // if user exists, remove from users.dat
        try {
            readFile = new Scanner(userFile);
            while (readFile.hasNextLine()) // loop over the whole file
            {
                line = readFile.nextLine();
                // add all the lines to the new string except for the one we want to delete
                if (!line.contains(userName))
                {
                    updatedFile +=  line + "\n";
                }
            }
            // write the updatedFile string into the file

            isDeleted = userFile.delete();
            if (!isDeleted)
            {
                System.out.println("File could not be deleted");
            } else {
                userFile = new File("User.txt");
                try (PrintWriter fileWriter = new PrintWriter(userFile))
                {
                    fileWriter.print(updatedFile);
                    // to update linked list
                    userLinkedList = UsersOperations.GetUsersOperationsInstance().readUserFromFile();
                }
            }
            System.out.println("\nUser deleted.");

        } catch(FileNotFoundException ex)
        {
            // do what the instructions say to do if the file does not exist
            System.out.println("File does not exist");
        }
    }

    public static void ChangePassword(String username, String currentPassword, String newPassword)
    {
        //TODO change the password only if current password match what is on records
        File userFile = new File("User.txt");
        Scanner readFile;
        String line = null;
        String updatedFile = null;
        User user = new User("", "", false); // make empty user
        StringTokenizer  tokenizer = null;
        String lineNoCommas;
        boolean isDeleted;
        // check if the user exists in the file user.dat
        try {
            readFile = new Scanner(userFile);
            while (readFile.hasNextLine()) // loop over the whole file
            {
                line = readFile.nextLine();
                lineNoCommas = line. replaceAll(",", ""); // remove commas
                // add all the lines to the new string except for the one we want to delete
                if (!line.contains(username) && (!line.contains(currentPassword)))
                {
                    updatedFile +=  line + "\n";
                } else
                {
                    // change the line in order to change current password to new password
                    tokenizer = new StringTokenizer(lineNoCommas);
                    user.setFirstName(tokenizer.nextToken());
                    if (tokenizer.countTokens() == 4)
                    {
                        user.setLastName(tokenizer.nextToken());
                    }
                    user.setUserName(tokenizer.nextToken());
                    user.setUserPassword(newPassword);
                    user.setManager(Boolean.parseBoolean(tokenizer.nextToken()));
                    line = null;
                    if (user.getLastName() != null)
                    {
                        line = user.getFirstName() + ", " + user.getLastName() + ", " + user.getUsername() + ", "
                                + GetPasswordHash(user.getHashedPassword()) + ", " + user.getManager();
                        updatedFile += line + "\n";
                    } else
                    {
                        line = user.getFirstName() + ", " + user.getUsername() + ", "
                                + GetPasswordHash(user.getHashedPassword()) + ", " + user.getManager();
                        updatedFile += line + "\n";
                    }
                }
            }
            // write the updatedFile string into the file
            isDeleted = userFile.delete();
            if (!isDeleted)
            {
                System.out.println("File could not be deleted");
            } else {
                userFile = new File("User.txt");
                try (PrintWriter fileWriter = new PrintWriter(userFile))
                {
                    fileWriter.print(updatedFile);
                    // to update the linked list
                    userLinkedList = UsersOperations.GetUsersOperationsInstance().readUserFromFile();
                }
            }
            System.out.println("\nPassword changed.");

        } catch(FileNotFoundException ex)
        {
            // do what the instructions say to do if the file does not exist
            System.out.println("File does not exist");
        }
    }

    public static String GetPasswordHash(String password) 
    {        
        String generatedPassword = null;
        
        try 
        {
            byte[] salt = new byte[] {12, -12, 65, 61, 
                                      2, -6, -90, 12, 
                                      4, -7, -87, 2, 
                                      34, -102, 3, 115};

            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Add password bytes to digest
            md.update(salt);
            // Get the hash's bytes
            byte[] bytes = md.digest(password.getBytes());
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        } 

        return generatedPassword;
    }
}