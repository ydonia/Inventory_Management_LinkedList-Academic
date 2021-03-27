package main.java.LinkedInventoryManagement.Product;

/**
 * This class represent a line in Inventory.dat file
 */
public class Product implements Comparable<Product>
{
    //TODO Add data fields, constructors and methods as needed. Class must implement Comparable to compare two products
    private int _id; // id unique to every product
    private String _name;
    private double _cost;
    private int _quantity;
    private int _margin; // going to be displayed as a percentage

    public Product(int id, String name, double cost, int quantity, int margin)
    {
        _id = id;
        _name = name;
        _cost = cost;
        _quantity = quantity;
        _margin = margin;
    }

    @Override
    public int compareTo(Product productToCompare) {
        // TODO compare the parameter object to the current instance of product

        // should return 0 if equal, 1 if not
        if (this.getId() == productToCompare.getId())
        {
            return 0;
        } else return 1;
    }

    public void setID(int id)
    {
        _id = id;
    }

    public void set_name(String name)
    {
        _name = name;
    }

    public void setCost(double cost)
    {
        _cost = cost;
    }

    public void setQuantity(int quantity)
    {
        _quantity = quantity;
    }

    public void set_margin(int margin)
    {
        _margin = margin;
    }
    public int getId()
    {
        return _id;
    }

    public String getName()
    {
        return _name;
    }

    public double getCost()
    {
        return _cost;
    }

    public int getQuantity()
    {
        return _quantity;
    }

    public int getMargin()
    {
        return _margin;
    }
}