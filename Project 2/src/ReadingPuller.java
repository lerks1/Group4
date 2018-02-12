import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadingPuller 
{
	private ArrayList<Reading> readings = new ArrayList<Reading>();
	private File file;
	
	public ReadingPuller(int csv)
	{
		pullReadings(csv);
	}
	
	public void changeFile(int csv)
	{
		pullReadings(csv);
	}
	
	private void pullReadings(int csv)
	{
		readings.clear();
		
		if (csv == 1)  {file = new File("yOGASensorData_1208104050.csv");}
		else  {file = new File("yOGASensorData_1208104041.csv");}
		
		System.out.println("Pulling Data from CSV...");
		
		try 
		{
			Scanner fileReader = new Scanner(file);
			fileReader.useDelimiter(",");
			
			while (fileReader.hasNextLine())
				readings.add(new Reading(fileReader));
			
			System.out.println("Loaded " + readings.size() + " records");

			fileReader.close();
		} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		
		System.out.println("Pull complete.");
	}
	
	public ArrayList<Reading> getReadings()
	{
		return readings;
	}
	
}
