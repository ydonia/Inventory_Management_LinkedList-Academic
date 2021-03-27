package main.java.LinkedInventoryManagement.Menu;

import main.java.LinkedInventoryManagement.Menu.Command;
import main.java.LinkedInventoryManagement.Product.ProductCatalog;
import main.java.LinkedInventoryManagement.Security.SecurityOperations;
import main.java.LinkedInventoryManagement.Security.User;

import java.util.Scanner;

public class ChangePasswordCommand extends Command {

    public ChangePasswordCommand(ProductCatalog productCatalog, User loggedOnUser)
    {
        super(productCatalog, loggedOnUser);
    }

    @Override
    public void Execute()
    {
        // prompt user for account info
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;
        String newPassword;

        // prompt manager for info
        System.out.print("Enter username: ");
        username = scanner.nextLine();
        System.out.print("Enter password: ");
        password = scanner.nextLine();
        System.out.print("Enter new password: ");
        newPassword = scanner.nextLine();

        // call the ChangePassword method function in InventoryManagementSecurity class
        SecurityOperations.ChangePassword(username, password, newPassword);
    }
}
