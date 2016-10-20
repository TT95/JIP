package start;


public class MyCar {
	
	private static final int TANKMAX = 80;
	private static final int TANKMIN = 20;
	private static final int TANKCONSMIN = 3;
	private static final int TANKCONSMAX = 20;
	private static final int TANKDEF = 40;
	private static final int TANKCONDEF = 5;
	private static final int CURRFUELDEF = 0;
	private static final double MILEAGEDEF = 0.0;
	private static final double LASTTRIPDEF = 0.0;
	
	private int tankCapacity;
	private int fuelConsumption;
	private CarMakers maker;
	private double mileage;
	private int currentFuel;
	private double lastTripDistance;


	public MyCar(String input) {
		String[] arguments = input.split(";");
		//wrong input
		if(arguments.length < 3) {
			tankCapacity = TANKDEF;
			fuelConsumption = TANKCONDEF;
			maker = CarMakers.NOTKNOWN;
			mileage = MILEAGEDEF;
			currentFuel = CURRFUELDEF;
			lastTripDistance = LASTTRIPDEF;
			return;
		}
		int tankCapacityInput = Integer.parseInt(arguments[0]);
		int fuelConsumptionInput = Integer.parseInt(arguments[1]);
		String makerInput = arguments[2];
		tankCapacity = TANKMIN < tankCapacityInput && tankCapacityInput < TANKMAX ? tankCapacityInput : TANKDEF;
		fuelConsumption = TANKCONSMIN < fuelConsumptionInput && fuelConsumptionInput < TANKCONSMAX ? 
				fuelConsumptionInput : TANKCONDEF;
		maker=CarMakers.toValue(makerInput);
		mileage = MILEAGEDEF;
		currentFuel = CURRFUELDEF;
		lastTripDistance = LASTTRIPDEF;
		
	}
	
	public boolean tankIt(double howMuch) {
		if(howMuch > tankCapacity) {
			return false;
		} else {
			currentFuel+=howMuch;
			return false;
		}
	}
	
	public boolean startTrip(double tripDistance){
		if( tripDistance > currentFuel*100/fuelConsumption) {
			return false;
		} else {
			currentFuel-= tripDistance / 100 * fuelConsumption;
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
	
	public int getTankCapacity() {
		return tankCapacity;
	}

	public int getFuelConsumption() {
		return fuelConsumption;
	}

	public int getCurrentFuel() {
		return currentFuel;
	}

	public double getLastTripDistance() {
		return lastTripDistance;
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
		testLine("tank capacity", 30, car1.getTankCapacity());
		testLine("fuel consumption", 6, car1.getFuelConsumption());
		testLine("maker", "Ford", car1.getMaker());
		separator();
		System.out.println("Creating car with arguments: 70;15;BMW" );
		MyCar bmw = new MyCar("70;15;BMW");
		testLine("maker", "BMW", bmw.getMaker());
		separator();
		System.out.println("Tanking our car by 30l");
		bmw.tankIt(30);
		testLine("current fuel", 30, bmw.getCurrentFuel());
		separator();
		System.out.println("Driving our car 100km");
		bmw.startTrip(100);
		testLine("mileage", 100.0, bmw.getMileage());
		testLine("last trip distance", 100.0, 100.0);
		separator();
		System.out.println("Trying to drive out car 150km more..");
		testLine("driving car possible:", false, bmw.startTrip(150));
		separator();
		System.out.println("Trying to drive out car 25km more..");
		testLine("last trip distance:", 10, bmw.startTrip(25));
	}
	
	public static void testLine(String attribute, Object actual, Object expected) {
		System.out.println("-> " +attribute + "\n EXPECTED:" + expected.toString() + "\n ACTUAL:" + actual.toString());
	}

	public static void separator() {
		System.out.println("-------------------------");
	}

	public static void main(String[] args) {
		testMe();
	}
	
}
