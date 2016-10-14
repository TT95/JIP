package start;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.Arrays;

public class MyCar {
	
	private final int TANKMAX = 80;
	private final int TANKMIN = 20;
	private final int TANKCONSMIN = 3;
	private final int TANKCONSMAX = 20;
	private final int TANKDEF = 40;
	private final int TANKCONDEF = 5;
	private final int CURRFUELDEF = 0;
	private final double MILEAGEDEF = 0.0;
	
	private int tankCapacity;
	private int fuelConsumption;
	private CarMakers maker;
	private double mileage;
	private int currentFuel;
	private double lastTripDistance;

	public MyCar(String input) {
		String[] arguments = input.split(";");
		int tankCapacityInput = Integer.parseInt(arguments[0]);
		int fuelConsumptionInput = Integer.parseInt(arguments[1]);
		String makerInput = arguments[2];
		tankCapacity = TANKMIN < tankCapacityInput && tankCapacityInput < TANKMAX ? tankCapacityInput : TANKDEF;
		fuelConsumption = TANKCONSMIN < fuelConsumptionInput && fuelConsumptionInput < TANKCONSMAX ? 
				fuelConsumptionInput : TANKCONDEF;
		maker=CarMakers.toValue(makerInput);
		mileage = 0.0;
		currentFuel = 0;
		lastTripDistance = 0.0;
		
	}
	
	public void tankIt(double howMuch) {
		if(howMuch > tankCapacity) {
			System.out.println("Tank capacity exceeded!");
		} else {
			System.out.println("OK");
			currentFuel+=howMuch;
		}
	}
	
	public boolean startTrip(double tripDistance){
		if( tripDistance > currentFuel*100/fuelConsumption) {
			System.out.println("Cannot reach destination!");
			return false;
		} else {
			System.out.println("Traveliiing!");
			mileage += mileage + tripDistance;
			lastTripDistance = tripDistance;
			return true;
		}
	}
	
	public double getMileage() {
		return mileage;
	}
	
	public double lastTripDistance() {
		return lastTripDistance;
	}
	
	public double getFuelLevel() {
		return currentFuel;
	}

	public String getMaker() {
		return maker.toString();
	}
	
	@Override
	public String toString() {
		return "tank capacity: " + tankCapacity + System.lineSeparator()
				+ "fuel consumption: " + fuelConsumption + System.lineSeparator()
				+ "maker: " + maker.toString() + System.lineSeparator()
				+ "mileage: " + mileage + System.lineSeparator()
				+ "current fuel: " + currentFuel + System.lineSeparator()
				+ "last trip distance: " + lastTripDistance + System.lineSeparator();
	}
	
	public static void testMe() {

		System.out.println("Creating a test car with following string (tankCapacity;fuelConsumption;maker): 30;6;Ford");
		MyCar car1 = new MyCar("30;6;Ford");
		System.out.println("EXPECTED: tank capacity: 30\n fuel consumption: 6\n maker: Ford\n mileage: 0.0\n current fuel: 0\n last trip distance: 0.0\n");
		System.out.println("ACTUAL:" + car1);
		separator();
		System.out.println("Creating a test car with following string (tankCapacity;fuelConsumption;maker): 100;-1;Daniel");
		MyCar car2 = new MyCar("100;-1;Daniel");
		System.out.println("EXPECTED: tank capacity: 40\n fuel consumption: 5\n maker: Notknown\n mileage: 0.0\n current fuel: 0\n last trip distance: 0.0\n");
		System.out.println("ACTUAL:" + car2);
		separator();
		System.out.println("Creating our cool BMW car for further testing with following string (tankCapacity;fuelConsumption;maker): 70;15;BMW");
		MyCar bmw = new MyCar("70;15;BMW");
		System.out.println("EXPECTED: tank capacity: 70\n fuel consumption: 15\n maker: BWM\n mileage: 0.0\n current fuel: 0\n last trip distance: 0.0\n");
		System.out.println("ACTUAL:" + bmw);
		separator();
		System.out.println("Driving our car ");
		bmw.tankIt(30);
		System.out.println("EXPECTED: tank capacity: 70\n fuel consumption: 15\n maker: BWM\n mileage: 0.0\n current fuel: 30\n last trip distance: 0.0\n");
		System.out.println("ACTUAL:" + bmw);
		separator();
	}

	public static void separator() {
		System.out.println("-------------------------");
	}

	public static void main(String[] args) {
		testMe();
	}
	
}
