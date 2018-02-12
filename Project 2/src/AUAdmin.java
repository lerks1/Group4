import java.util.ArrayList;

//This class is used as an admin tool to manipulate and control the Analysis Unit Objects.
public class AUAdmin 
{
	int scale;
	ArrayList<AnalysisUnit> anUnits;
	
	public AUAdmin(int scale, ArrayList<Reading> readings)
	{
		this.scale = scale;
		
		System.out.println("Sorting Analysis units.....");
		
		anUnits = new ArrayList<AnalysisUnit>();
		
		for (Reading read : readings)
		{
			boolean gotIt = false;
			for (AnalysisUnit a : anUnits)
			{
				gotIt = a.addReading(read);
				if (gotIt){break;}
			}
			if (!gotIt)
			{
				anUnits.add(new AnalysisUnit(read, scale));
			}
		}
		
		System.out.println("Analysis Units Sorted. Total = " + anUnits.size());
	}
	
	//Returns the difference between averages on the left and right for a particular X, Y or Z value, module and body part.
	public double getLRAverageDiff(String value, String module, String area)
	{
		double diffs = 0.0;
		ArrayList<Double> left = new ArrayList<Double>();
		ArrayList<Double> right = new ArrayList<Double>();
		
		left = getAverages(value, module, "left" + area);
		right = getAverages(value, module, "right" + area);
		
		for (int i = 0; i < left.size(); i++)
		{
			diffs += (Math.abs(left.get(i) - right.get(i)));
		}
		
		return diffs / left.size();
	}
	
	//Prints averages of a user defined X, Y or Z for cmparison. Input area might be Knee, Elbow, Ankle.
	public void printLeftRightComparison(String value, String module, String area)
	{
		ArrayList<Double> left = new ArrayList<Double>();
		ArrayList<Double> right = new ArrayList<Double>();
		
		left = getAverages(value, module, "left" + area);
		right = getAverages(value, module, "right" + area);
		
		for (int i = 0; i < left.size(); i++)
		{
			System.out.println("Timeslot " + i + ": " + left.get(i) + "    " + right.get(i));
		}
	}
	
	private ArrayList<Double> getAverages(String value, String module, String sensor)
	{
		ArrayList<Double> out = new ArrayList<Double>();
		
		for (AnalysisUnit n : anUnits)
		{
			out.add(n.getAverage(value, module, sensor));
		}
		
		return out;
	}
}
