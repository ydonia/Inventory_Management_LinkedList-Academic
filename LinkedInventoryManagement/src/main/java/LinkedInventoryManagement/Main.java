package main.java.LinkedInventoryManagement;

import main.java.LinkedInventoryManagement.Menu.*;
import main.java.LinkedInventoryManagement.Common.*;
import main.java.LinkedInventoryManagement.PersistentStorage.InventoryOperations;
import main.java.LinkedInventoryManagement.PersistentStorage.MenuListOperations;
import main.java.LinkedInventoryManagement.Product.*;
import main.java.LinkedInventoryManagement.Security.*;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Hello world!
 */
public class Main 
{
    public static void main( String[] args )
    {

        // intialize all local variables
        String username= "";
        String password = "";
        String line;
        Scanner readFile;
        StringTokenizer tokenizer;
        String name;
        // read in information from the Users.Dat file
        User user = null;

        // while loop for username and password validation
        boolean isValidUser = false;
        Scanner userInput = new Scanner(System.in);
        while (!isValidUser)
        {
            // prompt user to add user info until the info is valid (using AuthenticateUser method)
            System.out.print("Enter username: ");
            username = userInput.nextLine();
            System.out.print("Enter password: ");
            password = userInput.nextLine();

            user = SecurityOperations.AuthenticateUser(username, password);

            // if valid user, exit the loop
            if (user != null)
            {
                isValidUser = true; // to leave the while loop
            }
        }

        if(user.getLastName() != null)
        {
            System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName() + "!");
        } else
        {
            System.out.println("Welcome " + user.getFirstName() + "!");
        }

        ProductCatalog productCatalog = new ProductCatalog(InventoryOperations.GetInventoryOperationsInstance().ReadInventoryFromFile());

        // read from the inventory.dat file and fill in the productCatalog
        InventoryOperations.GetInventoryOperationsInstance().ReadInventoryFromFile();

        MenuList menu = new MenuList(MenuListOperations.GetMenuListOperationsInstance().ReadMenuFromFile(productCatalog, user));
        // read in from the menuList.dat file and fill in the menu


        // display the menu, prompt user for option
        menu.StartMenu(user);
        System.out.println("9 - Exit");
        Scanner userChoice = new Scanner(System.in);
        System.out.print("Enter your selection: ");
        int option = userChoice.nextInt();

        // TODO: delete teh code below because the number of options in menu depends on the file we read from
        // and use code such as below to analyze the user choice
        while(option != 9) {

            menu.getMenuItemById(option).Execute();
            menu.StartMenu(user);
            System.out.println("9 - Exit");
            System.out.print("Enter your selection: ");
            option = userChoice.nextInt();
        }
        if (option == 9)
        {
            System.out.println("Program Exited.");
            System.exit(0);
        }
        // exit option must always be last in the menu





        /*
        //THIS IS JUST AN EXAMPLE ON HOW TO AUTHENTICATE AND CREATE A COMMAND DYNAMICALLY. 
        //REMOVE CODE AND CHANGE AS NEEDED.

        //Authenticate the user and get a user object back
        User user = SecurityOperations.AuthenticateUser("admin", "admin"); 
        
        ProductCatalog  productCatalog = new ProductCatalog(); 
        
        //TODO: You will have to read the information below from the MenuList.dat file instead of hardcoding it here.
        String          commandClassName    = "AddProductCommand"; 
        int             optionNumber        = 1; 
        String          description         = "Add Product"; 
        Boolean         isRestricted        = true; 

        Command dynamicCommand = Command.CreateCommandDynamically(productCatalog, user, commandClassName); 

        System.out.println("The command concrete type is " + dynamicCommand.getClass().getSimpleName());
        
        //Create all MenuItems and add them to the MenuList. Array list usage is NOT permitted.
        MenuItem menuItem = new MenuItem(dynamicCommand, optionNumber, description, isRestricted); 

        MenuList menuList = new MenuList("Main Menu"); 

        menuList.AddMenuItem(menuItem);

        //Example of using a Singleton class to create and use a scanner object
        ScannerFactory.GetScannerInstance().nextLine();
        //Use the ScannerFactory as many times as you like throughout your project. 


        //Close the Scanner at the end of your program as follows. 
        ScannerFactory.CloseScannerInstance();

         */
    }
}
