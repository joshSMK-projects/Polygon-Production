/*
Name: Joshua Sullivan
Program Description: This class uses the input file and arranges its contents into the circular linked list LinkedList
Last Modified: 03/05/2019
*/
import java.util.Scanner;
import java.io.File;

public class inputFile
{
	//Instance variables
    private Scanner scanFile;
    
	//Method to open the file specified by the user
    public void openFile(String fileN)
    {
        try
        {
            scanFile = new Scanner(new File(fileN));
        }
        catch(Exception e)
        {
            System.out.println("File not found");
        }
    }
	//Pre-condition: fileN is the name of the file
	//Post-condition: The file is created with the name specified by fileN 
    
	
	//Method to read info from the file
    public void readFile(LinkedList<PlanarShape> list)
    {
		PlanarShape shape;

		while (scanFile.hasNext())
		{
			shape = PA2a.shapeFactory(scanFile);
			list.append(shape);
		}
    }
	//Pre-condition: A file is opened and list is an object of type LinkedList
	//Post-condition: Reads and uses data in file to add to the linked list
	
	
	//Method to close the file
	public void closeFile()
	{
		scanFile.close();
	}
	//Pre-condition: The file is opened
	//Post-condition: The file is closed
}