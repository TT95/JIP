package start.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import start.Gender;
import start.PersonalCarEx;
import start.ex.ImpossibleDrive;
import start.ex.IncorrectDate;
import start.ex.IncorrectInput;
import start.ex.TankOverload;

/**
 * Created by teo on 11/1/16.
 */
public class PersonalCarExTest {

    @Test(expected = IncorrectInput.class)
    public void testConstructor1() throws IncorrectInput {
        PersonalCarEx personalCar = new PersonalCarEx("30;6;Ford;male;Theo;Smith");
        assertEquals(Gender.MALE, personalCar.getGender());
        assertEquals("John", personalCar.getName());
        assertEquals("Doe", personalCar.getLastName());
    }

    @Test (expected = IncorrectInput.class)
    public void testConstructor2() throws ImpossibleDrive, TankOverload, IncorrectInput {
    	PersonalCarEx ford = new PersonalCarEx("30;6;Ford;male");
    	ford.tankIt(20);
    	ford.startTrip(10, 2014, 9, 1);
    }
    
    //testing with incorrect date input
    @Test
    public void testConstructor3() throws IncorrectInput {
        PersonalCarEx personalCar = new PersonalCarEx("30;6;Ford;Male;Theo;Smith;2015/10/1");
        assertEquals("Smith", personalCar.getLastName());
    }

    @Test (expected = IncorrectDate.class)
    public void testStartTrip() throws ImpossibleDrive, TankOverload, IncorrectInput {
        PersonalCarEx ford = new PersonalCarEx("30;6;Ford;Male;Theo;Smith;2015/10/1");
        ford.tankIt(20);
        ford.startTrip(10, 2014, 9, 1);
    }
    

}