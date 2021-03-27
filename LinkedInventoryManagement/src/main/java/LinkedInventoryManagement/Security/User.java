package main.java.LinkedInventoryManagement.Security;

import main.java.LinkedInventoryManagement.Menu.MenuItem;

/**
 * User
 */
public class User implements Comparable<User> {
    //TODO: Add necessary fields, constructor and methods. Consider implementing Comparable for this class. 
    private String firstName;
    private String lastName;
    private String username;
    private String hashedPassword;
    private boolean isManager;

    public User(String username, String hashedPassword, boolean isManager)
    {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.isManager = isManager;
    }

    public boolean setFirstName(String firstName)
    {
        if (firstName == null)
        {
            return false;
        } else
        {
            this.firstName = firstName;
            return true;
        }
    }

    @Override
    public int compareTo(User userToCompare) {

        // TODO compare the parameter object to the current instance of product

        // should return 0 if equal, 1 if not
        if (this.getUsername() == userToCompare.getUsername())
        {
            return 0;
        } else return 1;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public boolean setLastName(String lastName)
    {
        if (lastName == null)
        {
            return false;
        } else
        {
            this.lastName = lastName;
            return true;
        }
    }

    public boolean setUserName(String username) {
        if (username == null)
        {
            return false;
        } else {
            this.username = username;
            return true;
        }
    }

    public boolean setUserPassword(String hashedPassword) {
        if (hashedPassword == null)
        {
            return false;
        } else {
            this.hashedPassword = hashedPassword;
            return true;
        }
    }

    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }

    public String getUsername()
    {
        return username;
    }

    public String getHashedPassword()
    {
        return hashedPassword;
    }

    public boolean getManager()
    {
        return isManager;
    }
}