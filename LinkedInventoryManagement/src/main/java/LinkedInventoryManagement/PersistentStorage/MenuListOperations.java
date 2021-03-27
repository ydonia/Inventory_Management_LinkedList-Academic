package main.java.LinkedInventoryManagement.PersistentStorage;

import main.java.LinkedInventoryManagement.Common.InventoryLinkedList;
import main.java.LinkedInventoryManagement.Menu.*;
import main.java.LinkedInventoryManagement.Menu.MenuItem;
import main.java.LinkedInventoryManagement.Product.*;
import main.java.LinkedInventoryManagement.Security.User;


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * MenuListOperations: This class should contain all the MenuList.dat file read, write 
 * and update operations.
 */
public class MenuListOperations {

    //TODO Add methods to read, write and update MenuList.dat

    // to return an instance
    private static MenuListOperations object = null;

    public static MenuListOperations GetMenuListOperationsInstance()
    {
        if (object == null)
        {
            object = new MenuListOperations();
        }
        return object;
    }

    // method to read menuList from the file
    public InventoryLinkedList<MenuItem> ReadMenuFromFile(ProductCatalog productCatalog, User user) {
        InventoryLinkedList<MenuItem> menuItemList = new InventoryLinkedList<MenuItem>();


        String commandClassName;
        int optionNumber = 1;
        String description;
        boolean isRestricted;
        String menuLine = "";
        StringTokenizer tokenizer = null;
        // if the Menulist.dat file doesnt exist, still display the exit option, which will always be the last option in menu
        File menuListFile = new File("MenuList.txt");
        Scanner readMenu;
        String menuLineNoCommas = "";
        try {
            readMenu = new Scanner(menuListFile);
            while (readMenu.hasNextLine()) {
                menuLine = readMenu.nextLine();
                menuLineNoCommas = menuLine.replaceAll(",", ""); // remove commas
                tokenizer = new StringTokenizer(menuLineNoCommas);

                // read in the variables from the line
                description = tokenizer.nextToken();
                // if there are more tokens left in the line than isRestricted and commandCLassName
                while (tokenizer.countTokens() > 2) {
                    description += " " + tokenizer.nextToken();
                }
                isRestricted = Boolean.parseBoolean(tokenizer.nextToken());
                commandClassName = tokenizer.nextToken();

                // create dynamic command
                Command dynamicCommand = Command.CreateCommandDynamically(productCatalog, user, commandClassName);
                // create Menu Item based on the dynamic command
                MenuItem menuItem = new MenuItem(dynamicCommand, optionNumber, description, isRestricted);
                // add the Menu Item to a MenuList
                menuItemList.addLast(menuItem);
                optionNumber++;
            }
        } catch (
                FileNotFoundException exception) {
            System.out.println("File does not exist");
        }

        return menuItemList;
    }

    // method to write list to file
    public void WriteListToFile(InventoryLinkedList<MenuItem> menuItemInventoryLinkedList)
    {
        // print arrayList to the file
        File menuFile = new File("MenuList.txt");
        try (PrintWriter fileWriter = new PrintWriter(menuFile))
        {
            for (int i = 0; i < menuItemInventoryLinkedList.getSize(); i++)
            {
                fileWriter.print(menuItemInventoryLinkedList.GetElement(i).getDescription() + ", " + menuItemInventoryLinkedList.GetElement(i).getRestricted() + ", " +
                        menuItemInventoryLinkedList.GetElement(i).getCommand());
            }
        } catch (FileNotFoundException ex)
        {
            System.out.println("File not found");
        }
    }

}