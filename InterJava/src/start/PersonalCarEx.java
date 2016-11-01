package start;

import start.ex.*;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by teo on 10/27/16.
 */
public class PersonalCarEx extends MyCarEx{

    private final static String DEFNAMEMALE = "John";
    private final static String DEFNAMEFEMALE = "Jane";
    private final static String DEFSURNAME = "Doe";
    private final static Gender DEFGENDER = Gender.MALE;


    private Gender gender;
    private String name;
    private String lastName;
    private GregorianCalendar lastTripDate;
    private GregorianCalendar purchaseDate;


    private static List<String> popularMaleNames;
    private static List<String> popularFemaleNames;

    public PersonalCarEx(String input) {
        super(input);

        String[] arguments = input.split(";");

        if(arguments.length < 7) {
            gender = DEFGENDER;
            name = DEFNAMEMALE;
            lastName = DEFSURNAME;
            purchaseDate = new GregorianCalendar();
            return;
        }

        popularMaleNames = PopularNames.getPopularMaleNames();
        popularFemaleNames = PopularNames.getPopularFemaleNames();
        lastTripDate = new GregorianCalendar(0,0,0);

        gender = Gender.toValue(arguments[3]);
        String providedName = arguments[4];
        String providedLastName = arguments[5];


        // if any of the input is incorrect set all values to default
        try {
            purchaseDate = parseDate(arguments[6]);
            this.lastName = parseLastName(providedLastName);
            this.name = parseName(providedName);
        } catch (IncorrectInput i) {
            purchaseDate = new GregorianCalendar();
            this.lastName = DEFSURNAME;
            this.name = gender==Gender.MALE?DEFNAMEMALE:DEFNAMEFEMALE;
        }

    }


    private GregorianCalendar parseDate(String date) throws IncorrectDate {
        String[] dateArr = date.split("/");
        if (dateArr.length != 3) {
            throw new IncorrectDate("Date argument incorrect!");
        }
        return new GregorianCalendar(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1])-1, Integer.parseInt(dateArr[2]));
    }

    private String parseLastName(String lastName) throws IncorrectLastName {
        String modifiedLastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        if ( !lastName.matches(".*\\d+.*") && modifiedLastName.equals(lastName)) {
            return lastName;
        }
        throw new IncorrectLastName("Last name must start with capital letter and must not contain numbers!");
    }

    private String parseName(String name) throws IncorrectName {
        if (gender.equals(Gender.MALE)) {
            if (!popularMaleNames.contains(name)) {
                throw new IncorrectName("Male name not popular!");
            }
        } else {
            if (popularFemaleNames.contains(name)) {
                throw new IncorrectName("Female name not popular!");
            }
        }
        return name;
    }

    @Override
    public void startTrip(double tripDistance) throws ImpossibleDrive{
        lastTripDate = new GregorianCalendar();
        super.startTrip(tripDistance);
    }

    public void startTrip(double tripDistance, int year, int month, int day) throws IncorrectDate, ImpossibleDrive {
        GregorianCalendar lastTripDateInput = new GregorianCalendar(year, month-1, day);
        if (lastTripDateInput.compareTo(purchaseDate) < 0) {
            throw new IncorrectDate("Date of travel is earlier then purchase date");
        } else {
            lastTripDate = lastTripDateInput;
            super.startTrip(tripDistance);
        }

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


    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public GregorianCalendar getLastTripDate() {
        return lastTripDate;
    }

    public GregorianCalendar getPurchaseDate() {
        return purchaseDate;
    }

}
