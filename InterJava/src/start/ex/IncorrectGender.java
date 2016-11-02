package start.ex;

/**
 * Created by teo on 11/1/16.
 */
public class IncorrectGender extends IncorrectInput {
	
	private static final long serialVersionUID = 1L;

    public IncorrectGender(String input) {
        super(input);
    }

    public IncorrectGender(String input, String message) {
        super(input,message);
    }

    public IncorrectGender() {
    }
}
