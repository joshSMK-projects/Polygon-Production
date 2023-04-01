/*
Name: Joshua Sullivan
Program Description: Using aggregation of the class Point the class stores them in an array and calculates its area, shortest distance from the origin and returns the object as a string
The class also implements the PlanarShape abstract class.
Last Modified: 03/05/2019
*/
import java.util.Scanner;
public class Polygon extends PlanarShape
{
	//Instance variables
    private Point arrayPoint[]; //Array to hold all the points in the Polygon
    private int length; //The amount of points in the Polygon
    
	//Default Constructor
    public Polygon()
    {
        arrayPoint = new Point[25];
        for (int i = 0; i < arrayPoint.length; i++)
        {
            arrayPoint[i] = new Point();
        }
    }

	//Constructor with parameter for the amount of points in the Polygon and input stream
    public Polygon(int leng, Scanner scan)
    {
		length = leng;
		int counter = 0, nextCounter = 0; //counter follows the amount of points in the array and nextCounter follows the pair of (x,y) values as they are inputted
        arrayPoint = new Point[length];
		double x = 0.0, y = 0.0;
		while (nextCounter < (2 * length)) //Checks whether nextCounter is less than double the amount of points in the Polygon 
		{								   //(e.g. If there are 5 points, the while loop stops when nextCounter equals 10 but not before inputting the last point in the Polygon)
			x = scan.nextDouble();
			y = scan.nextDouble();
			
			arrayPoint[counter] = new Point(x, y);
			counter = counter + 1;
			nextCounter = nextCounter + 2;
		}
    }
    
	//Method to get the area of the Polygon given some points
    public double area()
    {
        String temp, temp1, temp2; //To seperate the Point.toString() function
        int indexOfComma, count = 0; 
        double area;
        double currentX, currentY; //Current x and y values of a point in the array
        double xyCoordinates[] = new double[arrayPoint.length * 2]; //Creates an array to store each x and y value of each point sequentially
        for (int i = 0; i < arrayPoint.length; i++)
        {
            temp = arrayPoint[i].toString(); //Sets temp to the string value of a point
            indexOfComma = temp.indexOf(',');

            temp = temp.replace("(", "");
            temp = temp.replace(")", "");
            temp1 = temp.substring(0, indexOfComma - 1); //Splits the string temp into the x value 
            temp2 = temp.substring(indexOfComma); //Splits the string temp into the y value
            
            currentX = Double.parseDouble(temp1); //Parse to convert the strings to a double
            currentY = Double.parseDouble(temp2);
            
            xyCoordinates[count] = currentX; //Sets x value of a point into the new array
            xyCoordinates[count + 1] = currentY; //Sets corresponding y value of the same point into the consecutive array index
            
            count = count + 2; //Moves index up by two to start on a new point
        }
		
        int counter = 0;
        int xNextCurrentIndex = 2;
        int yNextCurrentIndex = 3;
        double xCurrent = xyCoordinates[0]; //Initailises xCurrent to the first position of the array
        double yCurrent = xyCoordinates[1]; //Initialises yCurrent to the second position of the array
        double xNextCurrent = xyCoordinates[2];
        double yNextCurrent = xyCoordinates[3];
        double xCurrentTotal = 0, yCurrentTotal = 0, total = 0;
        while (xNextCurrentIndex < xyCoordinates.length && yNextCurrentIndex < xyCoordinates.length)
        {
            if (counter == 1) //Disregard for first iteration
            {
                xNextCurrent = xyCoordinates[xNextCurrentIndex]; //Moves xNextCurrent and yNextCurrent up to the next point
                yNextCurrent = xyCoordinates[yNextCurrentIndex];
            }
           
            xCurrentTotal = xNextCurrent + xCurrent; //Finds the sum of two points x values and y values which are consecutive in the array
            yCurrentTotal = yNextCurrent - yCurrent; 
            total = total + (xCurrentTotal * yCurrentTotal); //Multiplies the current totals of x and y values of two points and adds to total
			
            xCurrent = xNextCurrent; //xCurrent and yCurrent hold the value of the next point
            yCurrent = yNextCurrent;

            xNextCurrentIndex = xNextCurrentIndex + 2; //Moves the index up by two
            yNextCurrentIndex = yNextCurrentIndex + 2;
            counter = 1;
        }
		
		//Added step that adds the final point back to the starting point
		xCurrentTotal = xyCoordinates[0] + xCurrent; //At this point xCurrent and yCurrent are the last point in the polygon
		yCurrentTotal = xyCoordinates[1] - yCurrent;
		total = total + (xCurrentTotal * yCurrentTotal);
		
        area = 0.5 * Math.abs(total); 
        return area;
    }
    //Pre-condition: Polygon has more than two points
	//Post-condition: Returns the area of the Polygon 
	
	
	//Method to convert an object of type Polygon into a string of its points followed by its area
    public String toString()
    {
        String total = "", finalTotal, areaString; //Variables to hold the total of strings of the points, the final ouput and the area converted to a string
        double areaTemp; //Gets the area
        for (int i = 0; i < arrayPoint.length; i++)
        {
            total = total + arrayPoint[i].toString(); //Concatenates the strings of the points
        }
        areaTemp = area();
        areaString = String.format("%5.2f", areaTemp); //Formats the area to the specification "%5.2f"
        finalTotal = "POLY=[" + total + "]: " + areaString;
        return finalTotal;
    }
    //Pre-condition: The Polygon object has more than two points
	//Post-condition: Returns an object Polygon converted into a string of all its points followed by its area
	
	
	//Method to find the shortest distance from the origin
    public double originDistance()
    {   
	//Same as in the method area()
        String temp, temp1, temp2;
        int indexOfComma, count = 0;
        double currentX, currentY;
        double xyCoordinates[] = new double[arrayPoint.length * 2];
        for (int i = 0; i < arrayPoint.length; i++)
        {
            temp = arrayPoint[i].toString();
            indexOfComma = temp.indexOf(',');

            temp = temp.replace("(", "");
            temp = temp.replace(")", "");
            temp1 = temp.substring(0, indexOfComma - 1);
            temp2 = temp.substring(indexOfComma);
            
            currentX = Double.parseDouble(temp1);
            currentY = Double.parseDouble(temp2);
            
            xyCoordinates[count] = currentX;
            xyCoordinates[count + 1] = currentY;
            
            count = count + 2;
        }
        //End Similarities to area()
		
        int counter = 0;
        int xInitialIndex = 0;
        int yInitialIndex = 1;
        int minIndex = 0;
        double xCurrent = xyCoordinates[0];
        double yCurrent = xyCoordinates[1];
        double addXY = xCurrent + yCurrent; //finds the total of a current points x and y values
        double min = addXY; //Initialises min to the first addition of the first points x and y values
        double distance; //Holds the smallest distance from a point to the origin
        while (xInitialIndex < xyCoordinates.length && yInitialIndex < xyCoordinates.length) //While the array index is within bounds
        {
            if (counter != 0)
            {
                xCurrent = xyCoordinates[xInitialIndex]; //Sets xCurrent and yCurrent to the consecutive point in the array
                yCurrent = xyCoordinates[yInitialIndex];
                addXY = xCurrent + yCurrent; // Calculates the sum of the next points x and y values
            }
            
            if (addXY <= min) //If the current sum of x and y points is less than the current minimum value
            {
                min = addXY; //Set the smaller value to min
                minIndex = xInitialIndex; //Sets the index of the smallest value to minIndex
            }
            
            xInitialIndex = xInitialIndex + 2; //Moves the index to the next point in the array
            yInitialIndex = yInitialIndex + 2;
            
            counter = 1;
        }        
        distance = Math.abs(Math.sqrt(Math.pow(xyCoordinates[minIndex], 2.0) + Math.pow(xyCoordinates[minIndex + 1], 2.0))); //finds the distance from the point to the origin 
        return distance;
    }
	//Pre-condition: A Polgon object is created and has at least three points
	//Post-condition: Returns the shortest distance from a point within the Polygon
}
