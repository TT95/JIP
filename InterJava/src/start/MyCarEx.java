package start;

import start.ex.ImpossibleDrive;
import start.ex.TankOverload;

/**
 * Created by teo on 10/27/16.
 */
public class MyCarEx {

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


    public MyCarEx(String input) {
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
