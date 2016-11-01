package start.ex;

/**
 * Created by teo on 11/1/16.
 */
public class IncorrectGender extends IncorrectInput {

    public IncorrectGender(String input) {
        super(input);
    }

    public IncorrectGender(String input, String message) {
        super(input,message);
    }

    public IncorrectGender() {
    }
}
