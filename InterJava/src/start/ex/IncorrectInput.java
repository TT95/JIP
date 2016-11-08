package start.ex;

/**
 * Created by teo on 10/26/16.
 */
public class IncorrectInput extends Exception {
	
	private static final long serialVersionUID = 1L;

    private String message;
    private String input;

    public IncorrectInput(String input, String message) {
        this.input = input;
        this.message = message;
    }

    public IncorrectInput(String input) {
        this.input = input;
    }

    public IncorrectInput() {
    }

    @Override
    public String toString() {
        String string = "";
        if (input != null) {
            string+= " Input error:\"" + input + "\"";
        }
        if (message != null) {
            string += " message:\"" + message + "\"";
        }
        return string;
    }
}
