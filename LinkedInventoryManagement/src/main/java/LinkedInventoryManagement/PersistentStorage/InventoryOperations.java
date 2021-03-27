package main.java.LinkedInventoryManagement.PersistentStorage;

import main.java.LinkedInventoryManagement.Product.*;
import main.java.LinkedInventoryManagement.Common.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * InventoryOperations: This class should contain all the Inventory.dat file read, write 
 * and update operations. 
 */
public class InventoryOperations {

    // to return an instance
    private static InventoryOperations object = null;

    public static InventoryOperations GetInventoryOperationsInstance()
    {
        if (object == null)
        {
            object = new InventoryOperations();
        }
        return object;
    }

    public InventoryLinkedList<Product> ReadInventoryFromFile()
    {
        InventoryLinkedList<Product> productList = new InventoryLinkedList<Product>();

        File inventoryFile = new File("Inventory.txt");
        Scanner readInventory;
        int productId;
        int quantity, margin;
        String productName;
        double productCost;
        String inventoryLine = "";
        String inventoryLineNoCommas = "";
        StringTokenizer tokenizer = null;
        ProductCatalog productCatalog = new ProductCatalog();
        try
        {
            readInventory = new Scanner(inventoryFile);
            while (readInventory.hasNextLine())
            {
                inventoryLine = readInventory.nextLine();
                inventoryLineNoCommas = inventoryLine. replaceAll(",", ""); // remove commas
                tokenizer = new StringTokenizer(inventoryLineNoCommas);

                // fill the variables for a product from each line of the file
                productId = Integer.parseInt(tokenizer.nextToken());
                productName = tokenizer.nextToken();
                while (tokenizer.countTokens() > 3)
                {
                    productName += " " + tokenizer.nextToken() + " ";
                }
                productCost = Double.parseDouble(tokenizer.nextToken());
                quantity = Integer.parseInt(tokenizer.nextToken());
                margin = Integer.parseInt(tokenizer.nextToken());

                // store into a product
                Product product = new Product(productId, productName, productCost, quantity, margin);

                // store that product into the ProductCatalog
                // TODO: should i have this here and only have a linked list in productCatalog
                productList.addLast(product);
            }
        } catch (FileNotFoundException ex)
        {
            // do what the instructions say to do if the file does not exist
            System.out.println("File does not exist");
        }
        return productList;
    }

    public void WriteInventoryToFile(InventoryLinkedList<Product> productList)
    {
        // print arrayList to the file
        File inventoryFile = new File("Inventory.txt");
        try (PrintWriter fileWriter = new PrintWriter(inventoryFile))
        {
            for (int i = 0; i < productList.getSize(); i++)
            {
                fileWriter.print(productList.GetElement(i).getId() + ", " + productList.GetElement(i).getName() + ", " +
                        productList.GetElement(i).getCost() + ", " + productList.GetElement(i).getQuantity() + ", "
                        + productList.GetElement(i).getMargin() + "\n");
            }
        } catch (FileNotFoundException ex)
        {
            System.out.println("File not found");
        }
    }

}