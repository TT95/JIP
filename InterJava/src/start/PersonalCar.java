package start;


import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by teo on 10/17/16.
 */
public class PersonalCar extends MyCar {

    private final static String DEFNAMEMALE = "John";
    private final static String DEFNAMEFEMALE = "Jane";
    private final static String DEFSURNAME = "Doe";
    private final static Gender DEFGENDER = Gender.MALE;
    

    private Gender gender;
    private String name;
    private String lastName;
    private GregorianCalendar lastTripDate;
    private GregorianCalendar purchaseDate;

    public PersonalCar(String input) {
        super(input);
        
        String[] arguments = input.split(";");
        
        if(arguments.length < 7) {
        	gender = DEFGENDER;
        	name = gender.equals(Gender.MALE)?DEFNAMEMALE:DEFNAMEFEMALE;
        	lastName = DEFSURNAME;
        	purchaseDate = new GregorianCalendar();
            return;
        }
        
        gender = Gender.toValue(arguments[3]);
        String providedName = arguments[4];
        String providedLastName = arguments[5];
        try {
            String[] date = arguments[6].split("/");
            purchaseDate = new GregorianCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1])-1, Integer.parseInt(date[2]));
        } catch (Exception e) {
            purchaseDate = new GregorianCalendar();
        }
        lastTripDate = new GregorianCalendar(0,0,0);

        if (PopularNames.isCorrectName(providedName, gender)) {
            name = providedName;
        } else {
            name = gender.equals(Gender.MALE)?DEFNAMEMALE:DEFNAMEFEMALE;
        }

        this.lastName = correctLastName(providedLastName) ? providedLastName : DEFSURNAME;
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
        GregorianCalendar lastTripDateInput = new GregorianCalendar(year, month-1, day);
        if (lastTripDateInput.compareTo(purchaseDate) < 0) {
            lastTripDate = new GregorianCalendar(0,0,0);
            super.startTrip(tripDistance);
            return false;
        } else {
            lastTripDate = lastTripDateInput;
            return super.startTrip(tripDistance);
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

	public static void testMe() {
    	
    	
    	System.out.println("Creating a test car with following string (tankCapacity;fuelConsumption;maker;gender;name;surname;date): 30;6;Ford;male;Logan;Iwo;1995/10/1");
    	PersonalCar car1 = new PersonalCar("30;6;Ford;male;Logan;Iwo;1995/10/1");
		testLine("name", "Logan", car1.getName());
		testLine("surname", "Iwo", car1.getLastName());
		testLine("purchase date", "1995/10/1", car1.getPurchaseDate().getTime());
        testLine("gender", "male", car1.getGender());
        separator();
        System.out.println("Creating a test car with following string: 30;6;Ford;female;Logan;BACON;");
		PersonalCar car = new PersonalCar("30;6;Ford;female;Logan;BACON;");
		testLine("name", DEFNAMEMALE, car.getName());
		testLine("surname", DEFSURNAME, car.getLastName());
		separator();
		System.out.println("Creating a test car for driving with following string: 30;6;Ford;male;Theo;Smith;2015/10/1");
		PersonalCar ford = new PersonalCar("30;6;Ford;male;Theo;Smith;2015/10/1");
		testLine("pruchaseDate", "2015/10/1", ford.getPurchaseDate().getTime());
		separator();
        System.out.println("Tanking car and starting a trip on 2015/10/2");
        ford.tankIt(30);
        ford.startTrip(20, 2015, 10, 2);
        testLine("lastTripDistance", "2015/10/2", ford.getLastTripDate().getTime());
		separator();
        System.out.println("Tanking car and starting a trip to the past!! 2014/10/2");
        ford.startTrip(20, 2014, 10, 2);
        testLine("lastTripDistance", "null", ford.getLastTripDate().getTime());

    }

    public static void main(String[] args) {
        testMe();
    }

}
