package main.java.LinkedInventoryManagement.Menu;

/**
 * MenuItem
 */
public class MenuItem implements Comparable<MenuItem>
{
    //TODO Add data fields, constructors and methods as needed. Class must implement Comparable to compare two menu items.
    private Command _command;
    private String _description;
    private boolean _isRestricted;
    private int _optionNumber;

    public MenuItem(Command command, int optionNumber, String description, Boolean isRestricted)
    {
        _command = command;
        _description = description;
        _isRestricted = isRestricted;
        _optionNumber = optionNumber;
        System.out.println("Menu item created with command " + command.getClass().getSimpleName());
    }

    @Override
    public int compareTo(MenuItem menuItemToCompare) {

        // TODO Auto-generated method stub
        return _command.getClass().getName().compareTo(menuItemToCompare._command.getClass().getName());

        /* should return 0 if true, if true, then we will know that we do not want to insert
           a duplicate menu item
         */
    }

    public Command getCommand()
    {
        return _command;
    }

    public void setOptionNumber(int optionNumber)
    {
        _optionNumber = optionNumber;
    }

    public int getOptionNumber()
    {
        return _optionNumber;
    }

    public String getDescription()
    {
        return _description;
    }

    public void setRestricted(boolean isRestricted)
    {
        _isRestricted = isRestricted;
    }

    public boolean getRestricted()
    {
        return _isRestricted;
    }

    public void Execute()
    {
        this._command.Execute();
    }
}