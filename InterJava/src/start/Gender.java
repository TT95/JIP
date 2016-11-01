package start;

/**
 * Created by teo on 10/17/16.
 */
public enum Gender {
    MALE,FEMALE;

    /**
     * This method returns default value (MALE) if
     * given string is not parsable
     */
    static Gender toValue(String string) {
        if (string.equals("M")) {
            return Gender.MALE;
        }
        if (string.equals("F")) {
            return Gender.FEMALE;
        }
        for(Gender gender : Gender.values()) {
            if(gender.name().equals(string.toUpperCase())) {
                return gender;
            }
        }
        return Gender.MALE;
    }

}
