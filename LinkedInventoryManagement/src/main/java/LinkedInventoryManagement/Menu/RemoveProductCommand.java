package main.java.LinkedInventoryManagement.Menu;

import main.java.LinkedInventoryManagement.Product.*;
import main.java.LinkedInventoryManagement.Security.*;

import java.util.Scanner;

public class RemoveProductCommand extends Command {
        //TODO: add necessary fields/constructors to this class

        public RemoveProductCommand(ProductCatalog productCatalog, User loggedOnUser) {
            super(productCatalog, loggedOnUser);

        }

        @Override
        public void Execute() {
            // TODO Add the code that will execute this command
            Scanner scanner = new Scanner(System.in);

            int id = 0;
            String name = null;
            double cost = 0;
            int quantity = 0;
            int margin = 0;

            // prompt user for the product ID
            System.out.print("Enter product id: ");
            id = scanner.nextInt();

            // pass that ID to the removeProduct method function
            getProductCatalog().RemoveProduct(id);
        }

    }

