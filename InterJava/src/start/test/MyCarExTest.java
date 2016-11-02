package start.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import start.MyCarEx;
import start.ex.ImpossibleDrive;
import start.ex.IncorrectInput;
import start.ex.IncorrectTankCapacity;
import start.ex.TankOverload;

/**
 * Created by teo on 11/1/16.
 */
public class MyCarExTest {

    static MyCarEx bmw;

    @Before
    public void setUp() throws Exception {
        bmw = new MyCarEx("70;15;BMW");

    }
    
    @Test (expected = IncorrectInput.class)
    public void testConstructor1() throws IncorrectInput{
    	new MyCarEx("70;15");
    }
    
    @Test (expected = IncorrectInput.class)
    public void testConstructor2() throws IncorrectInput{
    	new MyCarEx("70;1a5;FORD");
    }
    
    @Test (expected = IncorrectTankCapacity.class)
    public void testConstructor3() throws IncorrectInput{
    	new MyCarEx("10000;1a5;FORD");
    }

    @Test (expected = TankOverload.class)
    public void testTankIt1() throws TankOverload{
        bmw.tankIt(100);
    }

    @Test
    public void testTankIt2() throws TankOverload{
        bmw.tankIt(30);
        assertEquals(bmw.getFuelLevel(), 30, 0.001);
    }

    @Test (expected = ImpossibleDrive.class)
    public void testStartTrip1() throws ImpossibleDrive, TankOverload{
        bmw.tankIt(10);
        bmw.startTrip(100);
    }

    @Test
    public void testStartTrip2() throws ImpossibleDrive, TankOverload{
        bmw.tankIt(30);
        bmw.startTrip(100);
        assertEquals(bmw.getMileage(), 100, 0.001);
    }

}