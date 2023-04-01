/*
Name: Joshua Sullivan
Program Description: Using the aggregation of the class Point, this class stores this as the centre point.
Along with this is the radius which is used to calculate the circles area, origin distance (from the centre point)
and a method toString which returns the Circle as a string which implement the abstract PlanarShape class.
Last Modified: 03/05/2019
*/
public class Circle extends PlanarShape
{
	//Instance variables
	private Point centre;
	private double radius;
	
	//Default constructor
	public Circle()
	{
		centre = new Point();
		radius = 0.00;
	}
	
	//Constructor with one parameter for the radius of the Circle
	public Circle(double rad)
	{
		centre = new Point();
		radius = rad;
	}
	
	//Constructor with two parameters for the x and y values which are the centre point of the Circle
	public Circle(double xValue, double yValue)
	{
		centre = new Point(xValue, yValue);
		radius = 0.00;
	}
	
	//Constructor with two parameters for the centre point and the radius
	public Circle(Point p, double rad)
	{
		centre = p;
		radius = rad;
	}
	
	//Constructor with three parameters for the x and y value of the centre point and radius of the Circle
	public Circle(double xValue, double yValue, double rad)
	{
		centre = new Point(xValue, yValue);
		radius = rad;
	}
	
	//Method to convert an object of Circle into a string of its centre point and radius followed by its area
	public String toString()
	{
		double areaTemp = area();
		String areaString, finalString, radiusString;		//String variables to hold the area and radius as a string and the final string
		
		radiusString = String.format("%4.2f", radius);		//Formatting values to the specifications
		areaString = String.format("%5.2f", areaTemp);
		finalString = "CIRC=[" + centre.toString() + " " + radiusString + "]: " + areaString;
		return finalString;
	}
	//Pre-condition: An object of Circle is created
	//Post-condition: Returns an object of Circle converted into a string of its centre point and radius followed by its area
	
	//Method to calculate and return the Circles area according to the formula: PI * r^2
	public double area()
	{
		return (Math.PI * (Math.pow(radius, 2)));
	}
	//Pre-condition: An object of type Circle is created
	//Post-condition: Returns the area of the Circle
	
	//Method to calculate the distance from the origin to the centre point of the Circle minus the radius
	public double originDistance()
	{
		return (centre.distanceOrigin() - radius);
	}
	//Pre-condition: An object of type Circle is created
	//Post-condition: Returns the distance from the origin to the centre point of the Circle minus the radius
}