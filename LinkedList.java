/*
Name: Joshua Sullivan
Program Description: This program is a circular linked list with a sentinel node of PlanarShape objects with options to prepend, append, remove,
print with/without an iterator, and copy a list into a derived class to be sorted.
Last Modified: 03/05/2019
*/
import java.util.*; //To access Iterator<E>

public class LinkedList<T extends Comparable<T>> implements Iterable<T>
{
	//Instance variables - are protected so that the derived class SortedList can see and use them
    protected Node<T> sentinel;
    protected int length;
	protected int modCount;
    
	//Default Constructor
    public LinkedList()
    {
        sentinel = new Node<T>();
		sentinel.setPrev(sentinel); //It sets its previous and next nodes to itself since it's empty
		sentinel.setNext(sentinel);
        length = 0;
		modCount = 0;
    }
    
	//Constructor with one parameter for an object of type T
    public LinkedList(T shape)
    {
        sentinel = new Node<T>();
        Node<T> newNode = new Node<>(shape);
        sentinel.setNext(newNode);
        sentinel.setPrev(newNode);
        newNode.setNext(sentinel);
        newNode.setPrev(sentinel);
        length = 1;
		modCount = 0; 
    }
    
	//Method to add a node to the front of the list
    public void prepend(T shape)
    {
        Node<T> newNode = new Node<>(shape);
        
        if (sentinel.getNext() == null) //if there is nothing in the list
        {
            sentinel.setNext(newNode);
            sentinel.setPrev(newNode);
            newNode.setNext(sentinel);
            newNode.setPrev(sentinel);
        }
        else
        {
            sentinel.getNext().setPrev(newNode);
            newNode.setNext(sentinel.getNext());
            sentinel.setNext(newNode);
            newNode.setPrev(sentinel);
        }
        length = length + 1;
		modCount = modCount + 1;
    }
    //Pre-condition: shape is an object of type T
	//Post-condition: A new node is added directly before the head of the list
	
	//Method to add a node to the end of the list
    public void append(T shape)
    {
        Node<T> newNode = new Node<>(shape);
        
        if (sentinel.getPrev() == null) //if there is nothing in the list
        {
            sentinel.setNext(newNode);
            sentinel.setPrev(newNode);
            newNode.setNext(sentinel);
            newNode.setPrev(sentinel);
        }
        else
        {
            sentinel.getPrev().setNext(newNode);
            newNode.setPrev(sentinel.getPrev());
            sentinel.setPrev(newNode);
            newNode.setNext(sentinel);
        }
        length = length + 1;
		modCount = modCount + 1;
    }
	//Pre-condition: shape is an object of type T
	//Post-condition: A new node is added directly after the tail node of the list   
	
	//Method to return and remove the head node of the list
    public T removeHead()
    {
        Node<T> temp = sentinel.getNext(); //temp holds reference to head
        if (length == 0) //If the list is empty
        {
            System.out.println("You cannot delete from an empty list");
            System.exit(0);
        }
        else if (length == 1) //If there is one node in list
        {
            temp = sentinel.getNext();
            temp.setNext(null);
            temp.setPrev(null);
            sentinel.setNext(sentinel);
            sentinel.setPrev(sentinel);
            length = length - 1;
        }
        else if (length > 1)
        {
            temp = sentinel.getNext();
            temp.getNext().setPrev(sentinel);
            sentinel.setNext(temp.getNext());
            temp.setNext(null);
            temp.setPrev(null);
            length = length - 1;
        }
		modCount = modCount + 1; 
        return temp.getData();
    }
	//Pre-condition: An object of LinkedList is created
	//Post-condition: Returns the data of the soon to be deleted node
	
	//Method that prints all the objects (T) in the list
    public void printList()
    {
        Node<T> temp = sentinel.getNext(); //temp equals head
        int shapeNum = 1;
		
		if (temp == sentinel)
		{
			System.out.println("You can't print from an empty list");
			return;
		}
		
        while (temp != sentinel) //while temp does not equal sentinel
        {
			if (temp.getNext() != sentinel) //If temp isn't tail
			{
				System.out.println("Planar Shape " + shapeNum + ": " + temp.getData().toString()); //Prints the T object according to the toString() method
			}												
			shapeNum = shapeNum + 1;
            temp = temp.getNext(); //temp equals the next node
        }
		System.out.print("\n");
    }
	//Pre-condition: An object of LinkedList is created
	//Post-condition: The list of objects (T) is printed according to the toString() method
	
	//Method to copy a LinkedList object into a derived class to be sorted
	public void copyList(SortedList<T> list)
	{
		Iterator<T> iter = this.iterator(); //Creates an iterator
		while (iter.hasNext())
		{
			T shape = iter.next(); //Moves the iterator to the next node in the list and returns that object which a node references 
			list.insertInOrder(shape); //Insert the object into the new SortedList
		}
	}
	//Pre-condition: An object of type LinkedList is created
	//Post-condition: The parameter list will contain a sorted list of values gathered by the iterator from the current object LinkedList
	
	//Method to print all the objects in the LinkedList using an iterator
	public void printWithIterator()
	{
		int counter = 1; 
		Iterator<T> iter = this.iterator();
		while (iter.hasNext())
		{
			T shape = iter.next(); //Moves the iterator to the next node in the list and returns the object that the node references
			System.out.println("Planar Shape " + counter + ": " + shape.toString());
			counter = counter + 1;
		}
		System.out.print("\n");
	}
	//Pre-condition: An object of type LinkedList is created
	//Post-condition: Prints all the objects of the LinkedList using an iterator
	
	//ITERATOR STUFF-------------------------------------------------------------------------------------------------------
	
	//Method that implements the Iterable interface which returns an iterator for the LinkedList
	public Iterator<T> iterator()
	{
		return new listIterator<T>();
	}
	//Pre-condition: An object of type LinkedList is created
	//Post-condition: Returns an iterator for the LinkedList
	
	private class listIterator<E> implements Iterator<T> 
	{
		//Instance variables
		private int expectedModCount; //Used to compare with modCount to check if there are any discrepencies when traversing using an iterator
		private Node<T> currentPos; //Current position of the iterator
		
		//Default Constructor
		private listIterator()
		{
			expectedModCount = modCount;
			currentPos = sentinel; 
		}
		
		//One of the methods that helps implement the Iterator interface and returns FALSE if the current position of the iterator is the sentinel node, otherwise it returns TRUE 
		public boolean hasNext()
		{
			return (!(currentPos.getNext() == sentinel));
		}
		//Pre-condition: An object of type LinkedList is created and a listIterator object is created
		//Post-condition: Returns FALSE if the current position of the iterator is the sentinel node, otherwise return TRUE
		
		//Another method that helps implement the Iterator interface which moves to the next node in the list before returning its data
		public T next()
		{
			if (modCount != expectedModCount)
				throw new ConcurrentModificationException("Can't mutate in context  of iterator!");
			if (!hasNext())
				throw new NoSuchElementException("There are no more elements!");
			
			currentPos = currentPos.getNext(); //Moves the iterator to the next node
			return (currentPos.getData()); //Returns the object currently being referenced by the iterator in the node
		}
		//Pre-condition: An object of type LinkedList is created and an object of type listIterator is created
		//Post-condition: Moves the iterator to the next node before returning the nodes data
		
		//Last method that implements the Iterator interface which in this case doesn't do anything other than throwing an UnsupportedOperationException
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
		//Pre-condition: An object of type LinkedList is created and an object of type listIterator is created
		//Post-condition: Throws an UnsupportedOperationException
	}
}