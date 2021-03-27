package main.java.LinkedInventoryManagement.Menu;

import main.java.LinkedInventoryManagement.Menu.Command;
import main.java.LinkedInventoryManagement.Product.ProductCatalog;
import main.java.LinkedInventoryManagement.Security.User;

public class DisplayInventoryCommand extends Command {

    public DisplayInventoryCommand(ProductCatalog productCatalog, User loggedOnUser)
    {
        super(productCatalog, loggedOnUser);
    }

    @Override
    public void Execute()
    {
        // create instance of ProductCatalog
        ProductCatalog productCatalog = new ProductCatalog();

        // call the PrintInventoryList method function in ProductCatalog
        //String string = productCatalog.PrintInventoryList();
        //System.out.println(string);
        getProductCatalog().PrintInventoryList();
    }
}
