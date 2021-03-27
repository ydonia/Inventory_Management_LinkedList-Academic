package main.java.LinkedInventoryManagement.Product;

import main.java.LinkedInventoryManagement.Common.*;
import main.java.LinkedInventoryManagement.PersistentStorage.*;

import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * ProductCatalog
 */
public class ProductCatalog {

    InventoryLinkedList<Product> productLinkedList = new InventoryLinkedList<Product>();

    //TODO Add data fields, constructors and methods as needed. Add a collection to hold Product objects

    public ProductCatalog()
    {

    }
    public ProductCatalog(InventoryLinkedList<Product> productInventoryLinkedList)
    {
        productLinkedList = productInventoryLinkedList;
    }

    //Add or update a product if already exists
    public void AddUpdateProduct(Product product)
    {
        boolean isSuccess = false;
        // if the product already exists, update it
        for (int i = 0; i < productLinkedList.getSize(); i++)
        {
            if (productLinkedList.GetElement(i).getId() == product.getId()) {
                // update the value of the product
                product.setID(productLinkedList.GetElement(i).getId());
                productLinkedList.SetElement(i, product);
                isSuccess = true;
                System.out.println("Product updated");
            }
        }

        // if product does not exist, add it to the list
        if (!isSuccess)
        {
            productLinkedList.addLast(product);
            isSuccess = true;
            System.out.println("Product added to inventory");
        }

        // print arrayList to the file
        InventoryOperations.GetInventoryOperationsInstance().WriteInventoryToFile(productLinkedList);
    }

    public boolean RemoveProduct(int productId)
    {
        boolean isSuccess = false;
        // find the product in the list, then remove it
        for (int i = 0; i < productLinkedList.getSize(); i++)
        {
            if (productLinkedList.GetElement(i).getId() == productId)
            {
                // remove product at the index
                productLinkedList.Remove(i);
                System.out.println("Product removed.");
                isSuccess = true;
            }
        }

        // if remove succeeded, write to file
        if (isSuccess)
        {
            InventoryOperations.GetInventoryOperationsInstance().WriteInventoryToFile(productLinkedList);
        }
        return isSuccess; 
    }

    // find product in a linked list
    public boolean FindProduct(Product product)
    {
        return productLinkedList.Contains(product);
    }

    // overload of FindProduct
    public Product FindProduct(String productName)
    {
        // search to see if product exists
        for (int i = 0; i < productLinkedList.getSize(); i++)
        {
            // if product name exists
            if (productLinkedList.GetElement(i).getName().compareToIgnoreCase(productName) == 0)
            {
                return productLinkedList.GetElement(i);
            }
        }
        return null; // if product does not exist
    }

    //Print information about a product including retail price (cost + (margin*cost/100))
    public String PrintProductInformation(int productId)
    {
        String productInformation = "";
        double retail;
        boolean productFound = false;

        // check if product ID exists
        for (int i = 0; i < productLinkedList.getSize(); i++)
        {
            if (productId == (productLinkedList.GetElement(i).getId()))
            {
                // set the retail price
                retail = (productLinkedList.GetElement(i).getCost() +
                        (productLinkedList.GetElement(i).getMargin()*productLinkedList.GetElement(i).getCost()/100));
                // add product details to productInformation string
                productInformation += "Id Name Cost Quantity Retail\n";
                productInformation += "----------------------------------------------------\n";
                productInformation += productLinkedList.GetElement(i).getId() + " " + productLinkedList.GetElement(i).getName()
                        + " $" + productLinkedList.GetElement(i).getCost() + " " + productLinkedList.GetElement(i).getQuantity() + " $"
                        + retail;
                break;
            } else
            {
                System.out.println("Product not found.");
            }
        }
        System.out.println(productInformation);
        return productInformation;
    }

    //Print all product information in the inventory
    public String PrintInventoryList()
    {
        String inventoryInformation =  "";

        System.out.println("Inventory: ");
        //You may print the inventory details here
        for (int i = 0; i < productLinkedList.getSize(); i++) // foreach loop to iterate through the ArrayList
        {
            inventoryInformation +=  productLinkedList.GetElement(i).getId() + " " + productLinkedList.GetElement(i).getName() + " " +
                    productLinkedList.GetElement(i).getCost() + " " + productLinkedList.GetElement(i).getQuantity() + " " + productLinkedList.GetElement(i).getMargin() + "\n";
        }
        System.out.println(inventoryInformation);
        return inventoryInformation;
    }
}