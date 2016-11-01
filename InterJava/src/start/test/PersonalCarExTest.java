package start.test;

import org.junit.Before;
import org.junit.Test;
import start.Gender;
import start.PersonalCarEx;
import start.ex.ImpossibleDrive;
import start.ex.IncorrectDate;
import start.ex.TankOverload;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by teo on 11/1/16.
 */
public class PersonalCarExTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testConstructor1() {
        PersonalCarEx personalCar = new PersonalCarEx("30;6;Ford;male;Theo;Smith");
        assertEquals(Gender.MALE, personalCar.getGender());
        assertEquals("John", personalCar.getName());
        assertEquals("Doe", personalCar.getLastName());
    }

    //testing with incorrect date input
    @Test
    public void testConstructor2() {
        PersonalCarEx personalCar = new PersonalCarEx("30;6;Ford;male;Theo;Smi88th;2015/10/1");
        assertEquals("Doe", personalCar.getLastName());
    }

    @Test (expected = IncorrectDate.class)
    public void testStartTrip() throws IncorrectDate , ImpossibleDrive, TankOverload {
        PersonalCarEx ford = new PersonalCarEx("30;6;Ford;male;Theo;Smith;2015/10/1");
        ford.tankIt(20);
        ford.startTrip(10, 2014, 9, 1);
    }


}