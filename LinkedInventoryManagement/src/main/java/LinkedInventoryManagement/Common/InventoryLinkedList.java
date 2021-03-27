package main.java.LinkedInventoryManagement.Common;

/**
 * InventoryLinkedList
 */
public class InventoryLinkedList<E extends Comparable<E>> {

    //Implement a generic linkedlist to support different data types.

    private Node<E> head, tail;
    private int size = 0; // number of elements in the list


    //Create another class that represents the node of a linkedlist.
    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E o) {
            element = o;
        }
    }

    // create an empty list
    public InventoryLinkedList() {
    }

    // create a list from an array of objects
    public InventoryLinkedList(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            add(i, objects[i]);
        }
    }

    // returns the number of elements in the list
    public int getSize() {
        return size;
    }

    // return the head element in the list
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    // return the last element in the list
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    // add element to the beginning of the list
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e); // create a new node
        newNode.next = head; // puts the new node before the first element in the list
        head = newNode; // assigns head pointer to the new node
        size++; // increment size by 1

        // if the list was empty before adding the new node
        if (tail == null) {
            tail = head;
        }

    }

    // add element to the end of the list
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); // create a new node

        // if the list was empty before adding the node
        if (tail == null) {
            head = tail = newNode; // assigns head and tail to the only element in the list
        } else {
            tail.next = newNode; // assigns newNode to be the new last element in the list
            tail = newNode; // assigns the new node to be the new tail
        }
        size++;

    }

    // add a new element at a certain index in the list
    public void add(int index, E e) {
        if (index == 0) // add to the beginning of the list
        {
            addFirst(e);
        } else if (index >= size) // add to the end of the list
        {
            addLast(e);
        } else {
            Node<E> current = head; // create the node to iterate over the list
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp;
            size++;
        }
    }

    // remove the first element in the list
    public E removeFirst() {
        if (size == 0) // if list is empty
        {
            return null;
        } else {
            Node<E> temp = head; // put first element into temporary node
            head = head.next; // remove first element
            size--; // decrement size
            if (head == null) {
                tail = null;
            }
            return temp.element;
        }
    }

    // remove last element in the array
    public E removeLast() {
        if (size == 0) // if list is empty
        {
            return null;
        } else if (size == 1) // if there is only one element in the list
        {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node<E> current = head; // to iterate over the list
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }
            Node<E> temp = tail; // assign value of tail to temporary node
            tail = current; // make the new node the last element in the list
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    // remove element at a certain index in the list
    public E Remove(int index) {
        if (index < 0 || index >= size) // if out of bounds
            return null;
        else if (index == 0) // if wants to remove first element
            return removeFirst();
        else if (index == size - 1) // if wants to remove last element
            return removeLast();
        else {
            Node<E> previous = head; // to iterate over list
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    // overload the remove method
    public E Remove(E element) {
        Node<E> current = head;
        // check the list to see if element exists
        int i = 0;
        while (current != null) {
            if (current.element.compareTo(element) == 0) // if element is found
            {
                return Remove(i);
            }
            i++;
            current = current.next;
        }
        // return null if element was not found
        return null;
    }

    //Return formatted elements information
    public String toString() {
        String string = "Contents:\n";

        Node<E> current = head;
        while (current != null) {
            string += current.element + "\n";
            current = current.next;

        }
        return string;
    }

    //Check if list contains the element
    public boolean Contains(E element) {
        Node<E> current = head;

        while (current != null) {
            if (current.element == element)
                return true;
            current = current.next;
        }
        return false; // if not found
    }

    //Set the element at the specified index
    public E SetElement(int index, E element) {
        Node<E> current = head;
        int count = 0; // index of node
        while (current != null) {
            if (count == index) {
                current.element = element;
                return current.element;
            }
            count++;
            current = current.next;
        }
        // if was not able to update
        return null;
    }

    //Get the element at the specified index
    public E GetElement(int index) {
        Node<E> current = head;

        int count = 0; // index of node
        while (current != null) {
            if (count == index)
                return current.element;
            count++;
            current = current.next;
        }

        // if element is not in the list
        assert (false);
        return null;
    }
}
