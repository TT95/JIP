package start;

/**
 * Created by teo on 10/17/16.
 */
public enum Gender {
    MALE,FEMALE;

    static Gender toValue(String string) {
        for(Gender gender : Gender.values()) {
            if(gender.name().equals(string.toUpperCase())) {
                return gender;
            }
        }
        return Gender.MALE;
    }

}
