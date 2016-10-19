package start;

import sun.misc.IOUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by teo on 10/17/16.
 */
public class PersonalCar extends MyCar {

    private final Gender DEFGENDER = Gender.MALE;
    private final String DEFNAMEMALE = "John";
    private final String DEFNAMEFEMALE = "Jane";
    private final String DEFSURNAME = "Doe";

    private Gender gender;
    private String name;
    private String lastName;
    private GregorianCalendar lastTripDate;
    private GregorianCalendar purchaseDate;

    private static List<String> popularNames;
    private static List<String> popularMaleNames;
    private static List<String> popularFemaleNames;

    public PersonalCar(String input) {
        super(input);

        String[] arguments = input.split(";");
        if (popularNames == null) {
            getPopularNames();
        }
        gender = Gender.toValue(arguments[3]);
        String name = arguments[4];
        String lastName = arguments[5];
        try {
            String[] date = arguments[6].split("/");
            purchaseDate = new GregorianCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
        } catch (Exception e) {
            purchaseDate = new GregorianCalendar();
        }
        lastTripDate = new GregorianCalendar(0,0,0);
        if (gender.equals(Gender.MALE)) {
            if (popularMaleNames.contains(name)) {
                this.name = name;
            } else {
                this.name = DEFNAMEMALE;
            }
        }
        if (gender.equals(Gender.FEMALE)) {
            if (popularFemaleNames.contains(name)) {
                this.name = name;
            } else {
                this.name = DEFNAMEFEMALE;
            }
        }
        this.lastName = correctLastName(lastName) ? lastName : DEFSURNAME;
    }

    private static void getPopularNames(){
        List<String> names = new ArrayList<>();
        try {
            names = Files.readAllLines(Paths.get("res/PopularNames.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        popularNames = names;
        int index = names.indexOf("#### Girls'");
        popularMaleNames = names.subList(1,index); //skipping first line
        popularFemaleNames = names.subList(index + 1, names.size());
    }

    private boolean correctLastName(String lastName) {
        String modifiedLastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        if ( !lastName.matches(".*\\d+.*") && modifiedLastName.equals(lastName)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean startTrip(double tripDistance) {
        lastTripDate = new GregorianCalendar();
        return super.startTrip(tripDistance);
    }

    public boolean startTrip(double tripDistance, int year, int month, int day) {
        GregorianCalendar lastTripDateInput = new GregorianCalendar(year, month, day);
        if (lastTripDateInput.compareTo(purchaseDate) < 0) {
            System.out.println("Wrong date of traveling!!");
            lastTripDate = new GregorianCalendar(0,0,0);
        } else {
            lastTripDate = lastTripDateInput;
        }
        return super.startTrip(tripDistance);
    }

    @Override
    public String toString() {
        return super.toString()
                + "gender: " + gender + System.lineSeparator()
                + "name: " + name + System.lineSeparator()
                + "surname: " + lastName + System.lineSeparator()
                + "purchaseDate: " + purchaseDate.getTime() + System.lineSeparator()
                + "lastTripDate: " + lastTripDate.getTime() + System.lineSeparator();

    }

    public static void testMe() {
        System.out.println("Creating a test car with following string (tankCapacity;fuelConsumption;maker;gender;name;surname;date): 30;6;Ford;male;Logan;Iwo;1995/10/1");
        PersonalCar car1 = new PersonalCar("30;6;Ford;male;Logan;Iwo;1995/10/1");
        System.out.println("EXPECTED: gender: MALE\n name: Logan\n surname: Iwo\n purchaseDate: 1995/10/1\n lastTripDate: null");
        System.out.println("ACTUAL:" + car1);
        separator();
        System.out.println("Creating a test car with following string: 30;6;Ford;female;Logan;BACON;");
        PersonalCar car2 = new PersonalCar("30;6;Ford;female;Logan;BACON;");
        System.out.println("EXPECTED: gender: FEMALE\n name: Jane\n surname: Doe\n purchaseDate: currentTime\n lastTripDate: null");
        System.out.println("ACTUAL:" + car2);
        separator();
        System.out.println("Creating a test car for driving with following string: 30;6;Ford;male;Theo;Smith;2015/10/1");
        PersonalCar car = new PersonalCar("30;6;Ford;male;Theo;Smith;2015/10/1");
        System.out.println("EXPECTED: gender: Male\n name: Theo\n surname: Smith\n purchaseDate: 2015/10/1\n lastTripDate: null");
        System.out.println("ACTUAL:" + car);
        separator();
        System.out.println("Tanking car and starting a trip on 2015/10/2");
        car.tankIt(30);
        car.startTrip(20, 2015, 10, 2);
        System.out.println("EXPECTED: gender: Male\n name: Theo\n surname: Smith\n purchaseDate: 2015/10/1\n lastTripDate: 2015/10/2");
        System.out.println("ACTUAL:" + car);
        separator();
        System.out.println("Tanking car and starting a trip to the past!! 2014/10/2");
        car.startTrip(20, 2014, 10, 2);
        System.out.println("EXPECTED: gender: Male\n name: Theo\n surname: Smith\n purchaseDate: 2015/10/1\n lastTripDate: null");
        System.out.println("ACTUAL:" + car);
        separator();
    }

    public static void main(String[] args) {
        testMe();
    }

}
