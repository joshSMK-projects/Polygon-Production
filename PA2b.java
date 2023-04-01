/*
Name: Joshua Sullivan
Program Description: This program is the driver class for the entirety of the project. It accepts all shapes defined in the factory method
It prints out the PlanarShape objects in input order from an input file and prints them again in a sorted order using an iterator.
Last Modified: 03/05/2019
*/
public class PA2b
{
	public static void main(String[] args)
	{
		inputFile inputF = new inputFile();
		LinkedList<PlanarShape> myList = new LinkedList<>();
		SortedList<PlanarShape> mySortedList = new SortedList<>();
        String fileName = args[0]; //Takes the file name when running and stores it in fileName
		
        inputF.openFile(fileName);
        inputF.readFile(myList); //Creates and adds PlanarShape nodes to the linked list myList
		
		System.out.println("List 1 (Original List): ");
		myList.printWithIterator();
		
		myList.copyList(mySortedList); //Copies the first list into a sorted list that automatically sorts it when adding nodes based on an insertion sort method
		
		System.out.println("List 2 (Sorted List): ");
		mySortedList.printWithIterator();
		
		inputF.closeFile(); //Closes input stream
	}
}