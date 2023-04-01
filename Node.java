/*
Name: Joshua Sullivan
Program Description: A double link node class with objects of T (which ar runtime will be PlanarShapes) as its data
Last Modified: 03/05/19
*/
public class Node<T>
{
	//Instance variables
    private T data;
    private Node<T> next;
    private Node<T> prev;
    
	//Default Constructor
    public Node()
    {
        data = null; //Data holds a reference to a T object not yet initialised
        next = null;
        prev = null;
    }
    
	//Constructor with one parameter for a T object
    public Node(T d)
    {
        data = d;
        next = null;
        prev = null;
    }
    
	//Setters
    public void setData(T d)
    {
        data = d;
    }
    
    public void setNext(Node<T> nextShape)
    {
        next = nextShape;
    }
    
    public void setPrev(Node<T> prevShape)
    {
        prev = prevShape;
    }
    //End Setters
	
	//Getters
    public T getData()
    {
        return data;
    }
    
    public Node<T> getNext()
    {
        return next;
    }
    
    public Node<T> getPrev()
    {
        return prev;
    }
	//End Getters
}