import java.util.ArrayList;

//Represents all Readings within a particular subset of the total time e.g. All readings within the nearest second.
public class AnalysisUnit 
{
	long time; //Represents the timeslot this Analysis holds information on e.g. 10:51:50 and 50 milliseconds.
	int scale; //Represents how big or small each timeslot will be e.g. 1000
	
	ArrayList <Reading> readings = new ArrayList<Reading>();
	
	public AnalysisUnit(Reading reading, int scale)
	{
		this.scale = scale;
		time = getTime(reading); 
	}
	
	//Adds reading if it fits this particular timeslot. Boolean return value gives feedback.
	public boolean addReading(Reading reading)
	{
		if (fits(reading))
		{
			readings.add(reading);
			return true;
		}
		return false;
	}
	
	//Rounds the timeslot up to the nearest number as determined by 'scale' - e.g. nearest 1000
	private long getTime(Reading reading)
	{
		return ((reading.getEpoch() + (scale - 1)) / scale) * scale; //Rounds time to the nearest 'scale' e.g. 1000
	}
	
	//Returns true if the input Reading belongs in this particular AnalysisUnit
	public boolean fits(Reading reading)
	{
		if (getTime(reading) == time)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Returns an ArrayList of all reading values of a particular type of module and sensor within this particular timeframe.
	public ArrayList<Reading> getReadingType(String module, String sensor)
	{
		ArrayList<Reading> out = new ArrayList<Reading>();
		
		for (Reading r : readings)
		{
			if (r.getModule().toString().equals(module) && r.getSensor().toString().equals(sensor))
			{
				out.add(r);
			}
		}
		
		return out;
	}
	
	//Gets the average X value of a particular module/sensor combo within this particular timeslot.
	public double getAverageX(String module, String sensor)
	{
		ArrayList<Reading> values = getReadingType(module, sensor);
		double value = 0.0;
		for (Reading r : values)
		{
			value =+ r.getX();
		}
		return value / values.size();
	}
	
	//Parameterised verson, gets the X, Y or Z value of a partocular module.
	public double getAverage(String value, String module, String sensor)
	{
		ArrayList<Reading> values = getReadingType(module, sensor);
		double out = 0.0;
		for (Reading r : values)
		{
			if (value.equals("X")){out =+ r.getX();}
			if (value.equals("Y")){out =+ r.getY();}
			if (value.equals("Z")){out =+ r.getZ();}
		}
		return out / values.size();
	}
	
	
}
