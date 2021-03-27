package main.java.LinkedInventoryManagement.Menu;

import main.java.LinkedInventoryManagement.PersistentStorage.MenuListOperations;
import main.java.LinkedInventoryManagement.PersistentStorage.UsersOperations;
import main.java.LinkedInventoryManagement.Product.Product;
import main.java.LinkedInventoryManagement.Security.*;
import main.java.LinkedInventoryManagement.Common.*;

import java.util.Iterator;

/**
 * MenuList
 */
public class MenuList {



    InventoryLinkedList<MenuItem> menuItemInventoryLinkedList = new InventoryLinkedList<MenuItem>();

    public MenuList(InventoryLinkedList<MenuItem> menuItemInventoryLinkedList1)
    {
        menuItemInventoryLinkedList = menuItemInventoryLinkedList1;
    }
    public MenuList(String menuHeader)
    {
        menuHeader = "Inventory Management System Menu";
    }

    public void AddMenuItem(MenuItem menuItem)
    {
        menuItemInventoryLinkedList.addLast(menuItem);
    }

    public MenuItem getMenuItemById(int option)
    {
        return menuItemInventoryLinkedList.GetElement(option - 1);
    }

    public void StartMenu(User user)
    {
        //TODO Display menu items based on user type, prompt user for command, execute selected command.
        //TODO Display menu items based on user type, prompt user for command, execute selected command.

        // use for loop to iterate over the menu list
        for (int i = 0; i < menuItemInventoryLinkedList.getSize(); i++) {
            if (user.getManager())
            {
                System.out.println(String.format("%d - %s", menuItemInventoryLinkedList.GetElement(i).getOptionNumber(),
                        menuItemInventoryLinkedList.GetElement(i).getDescription()));
            } else if (!user.getManager()) // if user is not a manager, only show the menu options that are not restricted to them
            {
                if (!menuItemInventoryLinkedList.GetElement(i).getRestricted())
                {
                    System.out.println(String.format("%d - %s", menuItemInventoryLinkedList.GetElement(i).getOptionNumber(),
                            menuItemInventoryLinkedList.GetElement(i).getDescription()));
                }
            }
        }
    }
}