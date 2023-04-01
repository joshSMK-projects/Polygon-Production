/*
Name: Joshua Sullivan
Program Description: Class for data of a point on a cartesian plane
Last Modified: 17/03/2019   
*/
public class Point
{
    //Instance variables
    private double x;
    private double y;
    
    //Default Constructor
    public Point()
    {
        x = 0.0;
        y = 0.0;
    }
    
    //Constructor with one parameter for the x coordinate
    public Point(double xCoord)
    {
        x = xCoord;
        y = 0.0;
    }
    
    //Constructor with two parameters for both coordinates
    public Point(double xCoord, double yCoord)
    {
        x = xCoord;
        y = yCoord;
    }
    
    //Method that calculates and returns the distance from the point (x,y) to the origin
    public double distanceOrigin()
    {   
        double distance;
        distance = Math.abs(Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0))); //Calculates pythagoras theorem
        return distance;
    }
    //Pre-condition: The instance variables of the calling object have values
    //Post-condition: Returns the distance from a point on the cartesian plane to the origin (0,0)
    
	
    //Method that formats the instance variables and returns it as a string
    public String toString()
    {
        String strX = String.format("%4.2f", x);
        String strY = String.format("%4.2f", y);
        String total = "(" + strX + "," + strY + ")";
        return total;
    }
    //Pre-condition: The instance variables of the calling object have values
    //Post-condition: Returns the point of the cartesian plane in the form (x,y) with decimal formatted to 4.2f
}
