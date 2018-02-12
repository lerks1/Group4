import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ArrayList<Reading> readings = new ArrayList<Reading>();
//		File file = new File("yOGASensorData_1208104041.csv");
		File file = new File("yOGASensorData_1208104050.csv");
		
		try {
			Scanner fileReader = new Scanner(file);
			fileReader.useDelimiter(",");
			
			while (fileReader.hasNextLine())
				readings.add(new Reading(fileReader));
			
			System.out.println("Loaded " + readings.size() + " records");

			fileReader.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		ArrayList <Double> lkX = new ArrayList <Double>();
		ArrayList <Double> rkX = new ArrayList <Double>();
		for (Reading r : readings)
		{
			if (r.getSensor().equals("leftKnee"))
			{
				lkX.add(r.getX());
			}
			if (r.getSensor().equals("rightKnee"))
			{
				rkX.add(r.getX());
			}
		}
		
		double sumL = 0;
		double sumR = 0;
		
		for (Double n : lkX)
		{
			sumL += n;
			System.out.println(n);
		}
		
		for (Double n : rkX)
		{
			sumR += n;
		}
		
		System.out.println("Average L = " + (sumL / lkX.size()));
		System.out.println("Average R = " + (sumR / rkX.size()));
		
	}
	
	

}

/*
Reading reading = new Reading(fileReader);

String time = fileReader.next();
String sensor = fileReader.next();
String module = fileReader.next();
String x = fileReader.next();
String y = fileReader.next();
String z = fileReader.next();
String epoch = fileReader.next();
String disconnects = fileReader.nextLine();

System.out.println(time);
System.out.println(sensor.substring(13, 15));
System.out.println(module.substring(1));
System.out.println(x.substring(1));
System.out.println(y);
System.out.println(z);
System.out.println(epoch.substring(1));
System.out.println(disconnects.substring(2));		
*/