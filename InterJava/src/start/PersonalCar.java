package start;

import sun.misc.IOUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        Gender gender = Gender.valueOf(arguments[3]);
        String name = arguments[4];
        String lastName = arguments[5];
        if (popularNames.contains(name)) {
            this.name = name;
        } else {
            this.name = gender.equals(Gender.MALE) ? DEFNAMEMALE : DEFNAMEFEMALE;
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
        this.popularNames = names;
        int index = names.indexOf("#### Girls'");
        popularMaleNames = names.subList(1,index);
        popularFemaleNames = names.subList(index + 1, names.size());
    }

    private boolean correctLastName(String lastName) {
        String modifiedLastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        if ( lastName.matches(".*\\d+.*") && modifiedLastName.equals(lastName)) {
            return true;
        }
        return false;
    }

}
