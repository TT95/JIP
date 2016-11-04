package start;

import java.util.Arrays;
import java.util.List;

import start.ex.IncorrectGender;

/**
 * Created by teo on 10/17/16.
 */
public enum Gender {
    MALE,FEMALE;

    static Gender toValue(String input) throws IncorrectGender{
        if(legalGenderInputsMale.contains(input)) {
        	return MALE;
        }
        if(legalGenderInputsFemale.contains(input)) {
        	return FEMALE;
        }
        throw new IncorrectGender();
    }

    private static final List<String> legalGenderInputsMale = 
    		Arrays.asList("Male","M","MALE","m","male");
    	
    private static final List<String> legalGenderInputsFemale = 
    		Arrays.asList("Female","F","FEMALE","f","female");
}
