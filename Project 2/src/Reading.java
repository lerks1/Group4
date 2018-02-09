import java.util.Scanner;

enum sensor {leftKnee, rightKnee, leftAnkle, rightAnkle, leftElbow, rightElbow, chest, lowerBack, leftWrist, rightWrist, forehead, unknown};
enum module {accelerometer, gyroscope, magnetometer};

public class Reading {
	private sensor sensorType;
	private module moduleType;
	private double x, y, z;
	private long epoch;
	private int disconnects;

	Reading(Scanner fileReader) {
		fileReader.next(); //Skip time
		String sensorStr = fileReader.next();
		
		String sensorSubStr = sensorStr.substring(16);
		if (sensorSubStr.equals("50")) sensorType = sensor.leftKnee;
		else if (sensorSubStr.equals("2B")) sensorType = sensor.rightKnee;
		else if (sensorSubStr.equals("17")) sensorType = sensor.leftAnkle;
		else if (sensorSubStr.equals("F2")) sensorType = sensor.rightAnkle;
		else if (sensorSubStr.equals("DB")) sensorType = sensor.leftElbow;	
		else if (sensorSubStr.equals("DD")) {
			String subStr = sensorStr.substring(13, 15);
			if (subStr.equals("E3")) sensorType = sensor.rightElbow;
			else if (subStr.equals("FD")) sensorType = sensor.leftWrist;		
		}
		else if (sensorSubStr.equals("48")) sensorType = sensor.chest;
		else if (sensorSubStr.equals("38")) sensorType = sensor.lowerBack;
		else if (sensorSubStr.equals("A1")) sensorType = sensor.rightWrist;
		else if (sensorSubStr.equals("F1")) sensorType = sensor.forehead;
		else sensorType = sensor.unknown;
		
		String moduleStr = fileReader.next().substring(1);
		if (moduleStr.equals("0")) moduleType = module.accelerometer;
		else if (moduleStr.equals("1")) moduleType = module.gyroscope;
		else if (moduleStr.equals("2")) moduleType = module.magnetometer;
		
		x = Double.parseDouble(fileReader.next().substring(1));
		y = Double.parseDouble(fileReader.next());
		z = Double.parseDouble(fileReader.next());
		
		epoch = Long.parseLong(fileReader.next().substring(1));
		
		disconnects = Integer.parseInt(fileReader.nextLine().substring(2));
	}
	
//	void setSensor(sensor sensorType) {this.sensorType = sensorType;}
//	void setModule(module moduleType) {this.moduleType = moduleType;}
//	void setX(double x) {this.x = x;}
//	void setY(double y) {this.y = y;}
//	void setZ(double z) {this.z = z;}
//	void setEpoch(long epoch) {this.epoch = epoch;}
//	void setDisconnects(int disconnects) {this.disconnects = disconnects;}
	
	sensor getSensor() {return sensorType;}
	module getModule() {return moduleType;}
	double getX() {return x;}
	double getY() {return y;}
	double getZ() {return z;}
	long getEpoch() {return epoch;}
	int getDisconnects() {return disconnects;}
}