package test.java.LinkedInventoryManagement;

import static org.junit.Assert.assertTrue;

import main.java.LinkedInventoryManagement.Common.InventoryLinkedList;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */


    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    public void getFirst()
    {
        InventoryLinkedList<String> list = new InventoryLinkedList<String>();

        // fill the linked list
        list.addFirst("first");
        list.addLast("second");
        list.addLast("third");
        list.addLast("last");

        String expectedResult = "first";
        String actualResult = list.getFirst();
        if (!expectedResult.equals(actualResult))
        {
            assertTrue("Function did not return correct value", false);
        }
    }

    public void getLast()
    {
        InventoryLinkedList<String> list = new InventoryLinkedList<String>();

        // fill the linked list
        list.addFirst("first");
        list.addLast("second");
        list.addLast("third");
        list.addLast("last");

        String expectedResult = "last";
        String actualResult = list.getLast();
        if (!expectedResult.equals(actualResult))
        {
            assertTrue("Function did not return correct value", false);
        }
    }

    public void insert(int index, String element)
    {
        InventoryLinkedList<String> list = new InventoryLinkedList<String>();

        // fill the linked list
        list.addFirst("first");
        list.addLast("second");
        list.addLast("third");
        list.addLast("last");

        list.add(1, "newFirst");

        String expectedResult = list.GetElement(1);

        if (!expectedResult.equals("newFirst"))
        {
            assertTrue("Function is wrong", false);
        }

    }

    public void remove(int index)
    {
        InventoryLinkedList<String> list = new InventoryLinkedList<String>();

        // fill the linked list
        list.addFirst("first");
        list.addLast("second");
        list.addLast("third");
        list.addLast("last");

        list.Remove(1);

        if (list.getSize() != 3)
        {
            assertTrue("the function is wrong", false);
        }
    }

    public void Contains(String element)
    {
        InventoryLinkedList<String> list = new InventoryLinkedList<String>();

        // fill the linked list
        list.addFirst("first");
        list.addLast("second");
        list.addLast("third");
        list.addLast("last");

        boolean expectedResult = list.Contains("first");
        if (!expectedResult)
        {
            assertTrue("function is wrong", false);
        }
    }

    public void SetElement(int index, String element)
    {
        InventoryLinkedList<String> list = new InventoryLinkedList<String>();

        // fill the linked list
        list.addFirst("first");
        list.addLast("second");
        list.addLast("third");
        list.addLast("last");

        String expectedResult = list.SetElement(1, "First");
        if (!expectedResult.equals("First"))
        {
            assertTrue("function is wrong", false);
        }
    }

    public void GetElement(int index)
    {
        InventoryLinkedList<String> list = new InventoryLinkedList<String>();

        // fill the linked list
        list.addFirst("first");
        list.addLast("second");
        list.addLast("third");
        list.addLast("last");

        String expectedResult = list.GetElement(1);
        if (expectedResult != "first")
        {
            assertTrue("function is wrong", false);
        }
    }
}
