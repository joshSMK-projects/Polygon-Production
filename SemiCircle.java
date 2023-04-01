/*
Name: Joshua Sullivan
Program Description: Using the aggregation of the class Point, this class stores these as the centre point and circumference point.
Along with this is the radius which is used to calculate the SemiCircles area,
origin distance (by finding the extremity points and comparing their distance from the origin with the centre and circumference point)
and a method toString which returns the SemiCircle as a string which implements the abstract PlanarShape class.
Last Modified: 03/05/2019
*/
public class SemiCircle extends PlanarShape
{
	//Instance variables
	private double radius;
	private Point centre, circumPoint, extreme1, extreme2; //Values to hold each point of importance: centre point, circumference point and the two extremity points
	
	//Default Constructor
	public SemiCircle()
	{
		radius = 0.00;
		centre = new Point();
		circumPoint = new Point();
		extreme1 = null;
		extreme2 = null;
	}
	
	//Constructor with one parameter for the SemiCircles radius
	public SemiCircle(double rad)
	{
		radius = rad;
		centre = new Point();
		circumPoint = new Point();
		extreme1 = null;
		extreme2 = null;
	}
	
	//Constructor with two parameters for the centre point and circumference point
	public SemiCircle(Point p1, Point p2)
	{
		centre = p1;
		circumPoint = p2;
		radius = getRadius();
		extreme1 = null;
		extreme2 = null;
	}
	
	//Constructor with three parameters for the radius, centre point and circumference point
	public SemiCircle(double rad, Point p1, Point p2)
	{
		radius = rad;
		centre = p1;
		circumPoint = p2;
		extreme1 = null;
		extreme2 = null;
	}
	
	//Method to calculate the radius of the SemiCircle
	public double getRadius()
	{
		double determinedRad;
		String temp, temp1, temp2;
		double currentX, currentY;
		int indexOfComma;
		Point [] arrayPoint = new Point[2];
		arrayPoint[0] = centre;
		arrayPoint[1] = circumPoint;
		
		double [] xyCoordinates = new double[4];
		int currXIndex = 0, currYIndex = 1; //This yIndex was 0 before
		
		for (int i = 0; i < 2; i++)
		{
			temp = arrayPoint[i].toString();
			
			indexOfComma = temp.indexOf(',');

			temp = temp.replace("(", "");
			temp = temp.replace(")", "");
			temp1 = temp.substring(0, indexOfComma - 1); //Splits the string temp into the x value 
			temp2 = temp.substring(indexOfComma); //Splits the string temp into the y value
				
			currentX = Double.parseDouble(temp1); //Parse to convert the strings to a double
			currentY = Double.parseDouble(temp2);
			
			xyCoordinates[currXIndex] = currentX;
			xyCoordinates[currYIndex] = currentY;
			
			currXIndex = currXIndex + 2;
			currYIndex = currYIndex + 2;
		}
		determinedRad = Math.sqrt(Math.pow(xyCoordinates[2] - xyCoordinates[0], 2) + Math.pow(xyCoordinates[3] - xyCoordinates[1], 2));
		
		return determinedRad;
	}
	//Pre-condition: The SemiCircle object has at least two points, them being the centre point and the circumference point
	//Post-condition: Returns the radius of the SemiCircle
	
	//Method to convert an object of SemiCircle into a string of its centre point and circumference point followed by its area
	public String toString()
	{
		double areaTemp = area();
		String areaString, finalString; //areaString is the conversion of the area into the string using a format and final string is the final product that is returned
		
		areaString = String.format("%5.2f", areaTemp);
		finalString = "SEMI=[" + centre.toString() + " " + circumPoint.toString() + "]: " + areaString;
		return finalString;
	}
	//Pre-condition: An object of type SemiCircle is created and its radius is calculated
	//Post-condition: Returns an object converted into a string of its centre point, circumference point and area
	
	//Method to calculate the area of the SemiCircle according to the formula: (PI * r^2) / 2
	public double area()
	{
		return ((Math.PI * (Math.pow(radius, 2))) / 2);
	}
	//Pre-condition: An object of type SemiCircle is created using the (Point p1, Point p2) constructor
	//Post-condition: Returns the area of the SemiCircle
	
	//Method to calculate the shortest distance from the SemiCircle to the origin from the four points, which are: centre point, circumference point and the two extremity points 
	public double originDistance()
	{
		double distCentre, distCircum, distExtreme1, distExtreme2; //Holds each individual distance from the origin
		double currentX, currentY;
		double gradient, inverseGradient, yIntercept; //yIntercept is b in the equation of a line y = mx + b
		String temp, temp1, temp2; //To seperate the Point.toString() function
		int indexOfComma;
		Point [] arrayPoint = new Point[2]; //Array used to extract the individual points from the two known points
		arrayPoint[0] = centre;
		arrayPoint[1] = circumPoint;
		
		double [] xyCoordinates = new double[4]; //Array to hold all the four X and Y values sequentially starting with the centre points X and Y value and then the circumference X and Y value
		int currXIndex = 0, currYIndex = 1; //Starts index at the first X and Y value
		
		distCentre = centre.distanceOrigin();
		distCircum = circumPoint.distanceOrigin();
		
		for (int i = 0; i < 2; i++) //Loop to fill the xyCoordinates array
		{
			temp = arrayPoint[i].toString();
			
			indexOfComma = temp.indexOf(',');

			temp = temp.replace("(", "");
			temp = temp.replace(")", "");
			temp1 = temp.substring(0, indexOfComma - 1); //Splits the string temp into the x value 
			temp2 = temp.substring(indexOfComma); //Splits the string temp into the y value
				
			currentX = Double.parseDouble(temp1); //Parse to convert the strings to a double
			currentY = Double.parseDouble(temp2);
			
			xyCoordinates[currXIndex] = currentX; //Assigns the parsed values from the arrayPoint array to the xyCoordinates array
			xyCoordinates[currYIndex] = currentY;
			
			currXIndex = currXIndex + 2; //Moves index up to the next point
			currYIndex = currYIndex + 2;
		}
		//At this point the xyCoordinates array holds the X and Y values of the centre point and circumference point
		
		//****************Finding Extremity Points*************************************************************************************
		//We now need to find the extremity points
		//This can be done by finding the equation of the line of the diameter (which centre point is sitting on)
		//and the equation of the Circle which inhabits the SemiCircle and solving using substitution to get the two X values of the extremity points.
		//After that all we have let to do is plug in that X value into the line equation to find Y for that extremity point.
		//*****************************************************************************************************************************
		
		//Firstly we need to derive the gradient of the line that both the centre point and the circumference point inhabits
		//This is in the form (y2 - y1) / (x2 - x1)
		//After this we can derive the gradient of the line that only the centre point is on by using the formula
		// m1 * m2 = -1 (where both m1 and m2 are gradients and are perpendicular to each other)
		//Finally we can derive the y - intercept since we have all the other variables (being y, x and now m)
		//This can be done by rearranging the equation in terms of b where the line equation originally looks like: y = mx + b
		//Now it becomes: b = y - mx
		
		gradient = (xyCoordinates[3] - xyCoordinates[1]) / (xyCoordinates[2] - xyCoordinates[0]);
		inverseGradient = - 1 / gradient;
		yIntercept = xyCoordinates[1] - (inverseGradient * xyCoordinates[0]);
		
		
		//Now we are looking at the Circle equation holding the SemiCircle structure.
		//The standard form of a Circle is: (x - h)^2 + (y - k)^2 = r^2 (where (h,k) is the centre point and r is the radius)
		//The general form of a Circle is: x^2 + y^2 + Ax + By + C = 0 (which is the standard form but expanded and simplified, also notice A, B, C are numbers)
		//In this case we are going to skip straight to implementing the general form
		
		double coefficientX2, coefficientX, coefficientY, constant;
		
		coefficientX2 = 1;
		coefficientX = (-xyCoordinates[0]) * 2; //These statements expands the brackets used in the standard form
		coefficientY = (-xyCoordinates[1]) * 2;
		constant = Math.pow(xyCoordinates[0], 2) + Math.pow(xyCoordinates[1], 2) - Math.pow(radius, 2);
		
		//Substituting for y from the line equation: y = mx + b to general from of a circle: Ax^2 + By^2 + Cx + Dy + E = 0
		//Solving the equation to get the two points that intersect both the semicircle and the line (diameter)
		//Result: Ax^2 + B(mx + b)^2 + Cx + D(mx + b) + E = 0
			//This removes the need for the coefficient of y2 since it is substituted
		
		//The intermediate values hold the new values obtained from substituting the line equation into the Circle equation and expanding the brackets only
		
		double intermediateX2, intermediateX, intermediateConstant;
		
		intermediateX2 = Math.pow(inverseGradient, 2);
		intermediateX = (inverseGradient * yIntercept) * 2 + (coefficientY * inverseGradient);
		intermediateConstant = Math.pow(yIntercept, 2) + (coefficientY * yIntercept);
		
		//Adds up the values found for the general form of the equation of the Circle and the new intermediate values found when substituting the line equation and expanding the brackets
		
		double totalX2, totalX, totalConstant;
		
		totalX2 = coefficientX2 + intermediateX2;
		totalX = coefficientX + intermediateX;
		totalConstant = constant + intermediateConstant;
		
		//Now the result is in the form solvable for a quadratic equation: Ax^2 + Bx + C = 0
		//Now solve using the quadratic equation: (- b +/- sqrt(b^2 - 4ac))/2a
		
		//The values for the x and y points of the extremity points
		
		double x1, x2, y1, y2;
		
		x1 = (- totalX + Math.sqrt(Math.pow(totalX, 2) - 4 * totalX2 * totalConstant)) / (2 * totalX2);
		x2 = (- totalX - Math.sqrt(Math.pow(totalX, 2) - 4 * totalX2 * totalConstant)) / (2 * totalX2);
		
		//Finally substituting the found X values into the line formula found above
		
		y1 = inverseGradient * x1 + yIntercept;
		y2 = inverseGradient * x2 + yIntercept;
		
		extreme1 = new Point(x1, y1); //Creating the extremity points
		extreme2 = new Point(x2, y2);
		
		distExtreme1 = extreme1.distanceOrigin(); //Finding their distance from the origin
		distExtreme2 = extreme2.distanceOrigin();
		
		//Creates an array of all the distances from each of the four points
		double [] allDistances = new double[]{distCentre, distCircum, distExtreme1, distExtreme2};
		double minDistance; //Holds the minimum distance out of all the distances
		
		minDistance = allDistances[0];
		for (int i = 1; i < allDistances.length; i++)
		{
			if (minDistance > allDistances[i])
				minDistance = allDistances[i];
		}
		//Finally returning the minimum distance from the origin
		return minDistance; 
	}
	//Pre-condition: An object of type SemiCircle is created
	//Post-condition: Finds and returns the shortest distance from the origin of all the points on the SemiCircle
}