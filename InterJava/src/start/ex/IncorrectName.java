package start.ex;

/**
 * Created by teo on 10/26/16.
 */
public class IncorrectName extends IncorrectInput {

    public IncorrectName(String input) {
        super(input);
    }

    public IncorrectName(String input, String message) {
        super(input,message);
    }

    public IncorrectName() {
    }
}
