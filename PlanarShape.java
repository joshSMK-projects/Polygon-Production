/*
Name: Joshua Sullivan
Program Description: An abstract class that allows implementation of these methods depending on what Shape uses them.
This class allows for polymorphism to be used with its derived classes. These methods include a toString() method,
and methods to calculate the area and distance from the origin (These methods hold late binding with the derived classes).
The last method is implemented already which is derived from the Comparable interface allowing for any two shapes to be
compared against one another.
Last Modified: 03/05/2019
*/
public abstract class PlanarShape implements Comparable<PlanarShape>
{   
	//Abstract methods to be implemented in classes that derive from PlanarShape
	public abstract String toString();
	public abstract double area();
	public abstract double originDistance();
	//End Abstract methods
	
	//Method to find the positioning of two shapes given by their area and origin distance
	public int compareTo(PlanarShape otherShape)
	{
		int temp = 0; //Initialise temp

        if (this.area() <= otherShape.area() + 0.005 && this.area() >= otherShape.area() - 0.005) //If calling objects area is within 0.005 units of the argument objects area
        {
            if (this.originDistance() < otherShape.originDistance()) //If the calling objects shortest distance from origin is less than the argument objects shortest distance from origin
            {
                temp = 1;
            }
            else
            {
                temp = 0;
            }
        }
        else if (this.area() < otherShape.area()) //If calling objects area is less than the argument objects area
        {
            temp = 1;
        }
        return temp;
	}
	//Pre-condition: otherShape is an object of type otherShape
	//Post-condition: Returns 1 if the calling object is supposed to be before the argument object, otherwise return 0
}