package main.java.LinkedInventoryManagement.Menu;

import java.lang.reflect.Constructor;

import main.java.LinkedInventoryManagement.Product.*;
import main.java.LinkedInventoryManagement.Security.*;

/**
 * Command
 */
public abstract class Command 
{
    //TODO: add necessary fields/constructors to this class
    private ProductCatalog productCatalog;
    private User loggedOnUser;

    public Command(ProductCatalog productCatalog, User loggedOnUser)
    {
        this.productCatalog = productCatalog;
        this.loggedOnUser = loggedOnUser;
    }

    public ProductCatalog getProductCatalog()
    {
        return productCatalog;
    }

    public static Command CreateCommandDynamically(ProductCatalog productCatalog, User user, String commandClassName)
    {
        Class<?>    concreteCommandClass    = null;
        Command     command                 = null;
        String      packageName             = "main.java.LinkedInventoryManagement.Menu"; // has to be same as package name

        try 
        {
            concreteCommandClass = Class.forName(packageName + "." + commandClassName);
            Constructor<?> con = concreteCommandClass.getConstructor(ProductCatalog.class, User.class);
            command = (Command)con.newInstance(productCatalog, user);
        } 
        catch (final Exception e) 
        {
            e.printStackTrace();
        }

        return command;
    }

    //An abstract method that must be overriden in subclasses of class Command
    public abstract void Execute(); 


}