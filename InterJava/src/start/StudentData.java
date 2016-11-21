package start;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    private Calendar birthDate;
    private Calendar matriculationDate;
    

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
        if (!PopularNames.isPopularName(name)) {
            throw new IncorrectName(name, "Given name is not popular!");
        }
        if (!PopularNames.isCorrectName(name, gender)) {
            throw new IncorrectName(name,"Given name is not " + gender);
        }
        return name;
    }

    private String parseLastName(String lastName) throws IncorrectLastName {
        String pattern = lastNameRegex;
        if (lastName.matches(pattern)) {
            return lastName;
        } else {
            throw new IncorrectLastName(lastName, "Given last name not correct!");
        }
    }

    private Calendar parseMatriculationDate(String input) throws IncorrectDate {
        Calendar date = parseDate(input);
        int ageAtMatriculation = date.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
            if (ageAtMatriculation < 16 || ageAtMatriculation > 70) {
            throw new IncorrectDate(input, "Incorrect given date - student too young or old for matriculation!");
        }
        return date;
    }

    private Calendar parseDate(String input) throws IncorrectDate {
        Calendar calendar = Calendar.getInstance();
        try {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            DateFormat df2 = new SimpleDateFormat("yy/MM/dd");
            df.setLenient(false);
            df2.setLenient(false);
            Date date = input.split("/")[0].length() == 2 ? df2.parse(input) : df.parse(input);
            calendar.setTime(date);
            calendar.getTime();
        } catch (Exception ex) {
            throw new IncorrectDate(input, "Incorrect given date - wrong format or illegal date!");
        }
        return calendar;
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

    public Calendar getBirthDate() {
        return birthDate;
    }

    public Calendar getMatriculationDate() {
        return matriculationDate;
    }
}
