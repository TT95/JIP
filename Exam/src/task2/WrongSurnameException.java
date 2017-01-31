package task2;

public class WrongSurnameException extends Exception {
	
	private static final long serialVersionUID = 1L;

    private String message;
    private String input;

    public WrongSurnameException(String input, String message) {
        this.input = input;
        this.message = message;
    }

    public WrongSurnameException(String input) {
        this.input = input;
    }

    public WrongSurnameException() {
    }

}
