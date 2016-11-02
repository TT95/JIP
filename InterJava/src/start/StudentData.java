package start;

import java.util.Calendar;
import java.util.GregorianCalendar;

import start.ex.IncorrectDate;
import start.ex.IncorrectInput;
import start.ex.IncorrectLastName;
import start.ex.IncorrectName;

/**
 * Created by teo on 11/1/16.
 */
public class StudentData {

    private Gender gender;
    private String firstName;
    private String lastName;
    private GregorianCalendar birthDate;
    private GregorianCalendar matriculationDate;
    

    private static final String lastNameRegex = "^[A-Z]([a-z])*(-[A-Z]([a-z])*)?";

    public StudentData(String input) throws IncorrectInput {

        String[] inputArr = input.split(";");

        try {
        	gender = Gender.toValue(inputArr[0].trim());
            firstName = parseName(inputArr[1].trim(), gender);
            lastName = parseLastName(inputArr[2].trim());
            birthDate = parseDate(inputArr[3].trim());
            matriculationDate = parseMatriculationDate(inputArr[4].trim());
        } catch (IndexOutOfBoundsException ex) {
        	throw new IncorrectInput(input,"Incorrect number of arguments!");
        }
        
    }

    private String parseName(String name, Gender gender) throws IncorrectName {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        if (PopularNames.isCorrectName(name, gender)) {
            return name;
        }
        throw new IncorrectName(name, "Given name is not popular!");
    }

    private String parseLastName(String lastName) throws IncorrectLastName {
        String pattern = lastNameRegex;
        if (lastName.matches(pattern)) {
            return lastName;
        } else {
            throw new IncorrectLastName(lastName, "Given last name not correct!");
        }
    }

    private GregorianCalendar parseMatriculationDate(String input) throws IncorrectDate {
        GregorianCalendar date = parseDate(input);
        if (Calendar.getInstance().get(Calendar.YEAR) - date.get(Calendar.YEAR) > 16) {
            throw new IncorrectDate(input, "Incorrect given date - student too young for matriculation!");
        }
        return date;
    }

    private GregorianCalendar parseDate(String input) throws IncorrectDate {
        String[] dateArr = input.split("/");
        if (dateArr.length != 3) {
            throw new IncorrectDate(input, "Date argument incorrect!");
        }
        if (dateArr[0].length() == 2) {
            dateArr[0] = "20" + dateArr[0];
        }
        return new GregorianCalendar(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1])-1, Integer.parseInt(dateArr[2]));
    }

    public Gender getGender() {
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public GregorianCalendar getBirthDate() {
        return birthDate;
    }

    public GregorianCalendar getMatriculationDate() {
        return matriculationDate;
    }
}
