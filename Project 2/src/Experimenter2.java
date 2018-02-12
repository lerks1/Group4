import java.util.ArrayList;

public class Experimenter2 {

	public static void main(String[] args) {
		
		ReadingPuller r = new ReadingPuller(1);
		
		AUAdmin admin = new AUAdmin(1000, r.getReadings());
		
		admin.printLeftRightComparison("X", "accelerometer", "Knee");
		
		//System.out.println(admin.getLRAverageDiff("X", "accelerometer", "Knee"));

	}

}
