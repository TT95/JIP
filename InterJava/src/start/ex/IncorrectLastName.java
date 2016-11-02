package start.ex;

/**
 * Created by teo on 10/26/16.
 */
public class IncorrectLastName extends IncorrectInput {
	
	private static final long serialVersionUID = 1L;

    public IncorrectLastName(String input) {
        super(input);
    }

    public IncorrectLastName(String input, String message) {
        super(input,message);
    }

    public IncorrectLastName() {

    }
}
