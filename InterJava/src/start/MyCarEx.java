package start;

import start.ex.ImpossibleDrive;
import start.ex.IncorrectFuelConsumption;
import start.ex.IncorrectInput;
import start.ex.IncorrectTankCapacity;
import start.ex.TankOverload;

/**
 * Created by teo on 10/27/16.
 */
public class MyCarEx {

    private static final int TANKMAX = 80;
    private static final int TANKMIN = 20;
    private static final int TANKCONSMIN = 3;
    private static final int TANKCONSMAX = 20;
    private static final int CURRFUELDEF = 0;
    private static final double MILEAGEDEF = 0.0;
    private static final double LASTTRIPDEF = 0.0;

    private int tankCapacity;
    private int fuelConsumption;
    private CarMakers maker;
    private double mileage;
    private int currentFuel;
    private double lastTripDistance;


    public MyCarEx(String input) throws IncorrectInput {
        
        try {
        	String[] arguments = input.split(";");
        	tankCapacity = parseTankCapacity(arguments[0]);
        	fuelConsumption = parseFuelConsumption(arguments[1]);
            String makerInput = arguments[2];
            maker=CarMakers.toValue(makerInput);
            mileage = MILEAGEDEF;
            currentFuel = CURRFUELDEF;
            lastTripDistance = LASTTRIPDEF;            
        } catch (NumberFormatException|IndexOutOfBoundsException ex ) {
        	throw new IncorrectInput(input,"There is error in provided argument!");
        }

    }
    
    private int parseTankCapacity(String input) throws IncorrectTankCapacity {
    	int tankCapacityInput = Integer.parseInt(input);
    	if ( TANKMIN < tankCapacityInput && tankCapacityInput < TANKMAX ) {
    		return tankCapacityInput;
    	}
    	throw new IncorrectTankCapacity(input, "Tank capacity not legal!");    	
    }
    
    private int parseFuelConsumption(String input) throws IncorrectFuelConsumption {
    	int fuelConsumptionInput = Integer.parseInt(input);
    	if ( TANKCONSMIN < fuelConsumptionInput && fuelConsumptionInput < TANKCONSMAX ) {
    		return fuelConsumptionInput;
    	}
    	throw new IncorrectFuelConsumption(input, "Fuel consumpion not legal!");    	
    }
    
    //changed this method to exception
    public void tankIt(double howMuch) throws TankOverload {
        if(howMuch > tankCapacity) {
            throw new TankOverload("capacity of tank too low!" );
        } else {
            currentFuel+=howMuch;
        }
    }

    //Changed this method to exception
    public void startTrip(double tripDistance) throws ImpossibleDrive {
        if( tripDistance > currentFuel*100/fuelConsumption) {
            throw new ImpossibleDrive("Distance is too big for amount of fuel");
        } else {
            currentFuel-= tripDistance / 100 * fuelConsumption;
            mileage += mileage + tripDistance;
            lastTripDistance = tripDistance;
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

    public static void testLine(String attribute, Object actual, Object expected) {
        System.out.println("-> " +attribute + "\n EXPECTED:" + expected.toString() + "\n ACTUAL:" + actual.toString());
    }

    public static void separator() {
        System.out.println("-------------------------");
    }

}
