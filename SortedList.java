/*
Name: Joshua Sullivan
Program Description: SortedList is an extension of the generic LinkedList class with one new feature that allows objects added to
the list to be added using an insertion sort method.
Last Modified: 03/05/2019
*/

public class SortedList<T extends Comparable<T>> extends LinkedList<T>
{
	//Default Constructor
	public SortedList()
	{
		super();
	}
	
	//Method where when T objects get inserted into the list, they get inserted using an insertion sort method
	public void insertInOrder(T shape)
	{		
		this.append(shape);
	
		if (length > 1)
		{
			Node<T> temp = sentinel.getNext(); //Sets temp to the head node
			Node<T> nextTemp = temp.getNext(); //Sets nextTemp to the next of temp
			int comparison;		//Holds the result of the compareTo() method
			boolean convertComparison;  //Holds the converted result of the compareTo() method from an integer to a boolean
			
			while (nextTemp != sentinel)
			{
				comparison = nextTemp.getData().compareTo(temp.getData()); //Compares two nodes to see if one precedes another using the compareTo() method
				convertComparison = (comparison == 1); //Converts the result of the compareTo() method to a boolean for sorting
				if (convertComparison)
				{   
					while (temp != sentinel && convertComparison) //The while loop is used to switch position of nodes if nextTemp precedes temp
					{
						if (nextTemp.getNext() == sentinel && temp.getPrev() == sentinel) //If there is only two nodes 
						{  
							temp.setNext(sentinel);
							sentinel.setPrev(temp);
							sentinel.setNext(nextTemp);
							temp.setPrev(nextTemp);
							nextTemp.setPrev(sentinel);
							nextTemp.setNext(temp);
						}
						else if (nextTemp.getNext() == sentinel) //If the two nodes to be compared are the last ones in the list
						{   
							temp.getPrev().setNext(nextTemp);
							nextTemp.setPrev(temp.getPrev());
							temp.setPrev(nextTemp);
							temp.setNext(sentinel);
							nextTemp.setNext(temp);
							sentinel.setPrev(temp);
						}
						else if (temp.getPrev() == sentinel) //If the two nodes in the list to be compared are the first two 
						{
							temp.setNext(nextTemp.getNext());
							nextTemp.getNext().setPrev(temp);
							nextTemp.setNext(temp);
							nextTemp.setPrev(sentinel);
							temp.setPrev(nextTemp);
							sentinel.setNext(nextTemp);
						}
						else //If the two nodes are inbetween two other nodes
						{
							temp.setNext(nextTemp.getNext());
							nextTemp.setPrev(temp.getPrev());
							nextTemp.getNext().setPrev(temp);
							temp.getPrev().setNext(nextTemp);
							nextTemp.setNext(temp);
							temp.setPrev(nextTemp);
						}
						temp = nextTemp.getPrev(); //Temp has switched with nextTemp at this point and moves temp to the node directly before nextTemp
					}
				}
				temp = nextTemp; //Traverses temp to the next node
				nextTemp = nextTemp.getNext(); //Traverses nextTemp to the next node
			}
		}
	}
	//Pre-condition: A SortedList object is created
	//Post-condition: Adds an object T to the list using an insertion sort method
}