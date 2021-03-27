package main.java.LinkedInventoryManagement.Menu;


import main.java.LinkedInventoryManagement.Product.Product;
import main.java.LinkedInventoryManagement.Product.ProductCatalog;
import main.java.LinkedInventoryManagement.Security.User;

import java.util.Scanner;

/**
 * AddCommand
 */
public class UpdateProductCommand extends Command
{
    //TODO: add necessary fields/constructors to this class

    public UpdateProductCommand(ProductCatalog productCatalog, User loggedOnUser)
    {
        super(productCatalog, loggedOnUser);

    }

    @Override
    public void Execute() {
        // TODO Add the code that will execute this command

        //TODO: change this later using the ScannerFactory
        Scanner scanner = new Scanner(System.in);

        int id = 0;

        String name = null;
        double cost = 0;
        int quantity = 0;
        int margin = 0;

        // prompt user for product ID and description
        System.out.print("Enter product id: ");
        id = scanner.nextInt();
        System.out.print("Enter product name: ");
        name = scanner.nextLine();
        name = scanner.nextLine();
        System.out.print("Enter product cost: ");
        cost = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        quantity = scanner.nextInt();
        System.out.print("Enter product margin: ");
        margin = scanner.nextInt();

        // create new product
        Product product = new Product(id, name, cost, quantity, margin);

        // pass that product to the AddUpdateProduct method function
        getProductCatalog().AddUpdateProduct(product);
    }
}