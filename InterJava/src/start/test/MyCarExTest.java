package start.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import start.CarMakers;
import start.MyCarEx;
import start.ex.ImpossibleDrive;
import start.ex.TankOverload;
import static org.junit.Assert.assertEquals;

/**
 * Created by teo on 11/1/16.
 */
public class MyCarExTest {

    static MyCarEx bmw;

    @Before
    public void setUp() throws Exception {
        bmw = new MyCarEx("70;15;BMW");

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

    @Test
    public void testDefaultValuesInConstruct() {
        MyCarEx car = new MyCarEx("10;10");
        assertEquals(car.getMaker(), CarMakers.NOTKNOWN.toString());
        assertEquals(car.getFuelConsumption(), 5);
        assertEquals(car.getTankCapacity(),40);
    }

}