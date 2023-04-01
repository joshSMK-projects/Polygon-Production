/*
Name: Joshua Sullivan
Program Description: This program is the driver class for the Polygon specifications only.
It prints out the PlanarShape (Polygon) objects in input order from an input file and prints them again in a sorted order using an iterator.
This class also contains the factory method which is utilised by both driver classes to read input and create and add PlanarShape objects to the LinkedList.
Last Modified: 03/05/2019
*/
import java.util.Scanner;

public class PA2a
{
	public static void main(String[] args)
    {
        inputFile inputF = new inputFile();
		LinkedList<PlanarShape> myList = new LinkedList<>();
		SortedList<PlanarShape> mySortedList = new SortedList<>();
        String fileName = args[0]; //Takes the file name when running and stores it in fileName
		
        inputF.openFile(fileName);
        inputF.readFile(myList); //Creates and adds PlanarShapes to the linked list myList
		
		System.out.println("List 1 (Original List): ");
		myList.printWithIterator();
		
		myList.copyList(mySortedList);
		
		System.out.println("List 2 (Sorted List): ");
		mySortedList.printWithIterator();
		
		inputF.closeFile(); //Closes input stream
	}
	
	//A factory method that contains specifications on finding and giving instructions to, which are dependent on the PlanarShape specifications found
	public static PlanarShape shapeFactory(Scanner lineScanner)
	{
		String shapeChar = lineScanner.next(); //Gets the next character from the scanner which specifies the type of shape
		int length = 0;      //The amount of points in a polygon
		double radius = 0.00; //For circles/semi-circles
		double xCentre, yCentre; //For circles/semi-circles
		double xCircumference, yCircumference; //For semi-circles only

		switch (shapeChar)
		{
			case "P":
				length = lineScanner.nextInt(); //Sets length to the integer directly after P
				return (new Polygon(length, lineScanner));
			
			case "C":
				xCentre = lineScanner.nextDouble(); //Sets x value of centre, y value of centre and radius of Circle (in that order)
				yCentre = lineScanner.nextDouble();
				radius = lineScanner.nextDouble();
				return (new Circle(xCentre, yCentre, radius));
			
			case "S":
				Point p1, p2; //Declares two points which will be the centre and circumference point
				xCentre = lineScanner.nextDouble(); //First takes input into the centre point
				yCentre = lineScanner.nextDouble();
				
				p1 = new Point(xCentre, yCentre);
				
				xCircumference = lineScanner.nextDouble(); //Finally takes input into the circumference point
				yCircumference = lineScanner.nextDouble();
				
				p2 = new Point(xCircumference, yCircumference);
				
				return (new SemiCircle(p1, p2));
			
			default:
				System.out.println("Invalid input type."); //If the program finds no matching character value for a given shape
		}
		return null; //If no matching character is found
	}
	//Pre-condition: An input file is opened and has correct specifications for the given shapes within the factory method
	//Post-condition: Returns a PlanarShape object dependent on the specifications given to make it
}