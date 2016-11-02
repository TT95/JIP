package start.ex;

/**
 * Created by teo on 10/26/16.
 */
public class IncorrectDate extends IncorrectInput {
	
	private static final long serialVersionUID = 1L;

    public IncorrectDate(String input) {
        super(input);
    }

    public IncorrectDate(String input, String message) {
        super(input,message);
    }

    public IncorrectDate() {
    }
}
