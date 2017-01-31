package task2;

public class WrongNameException extends Exception {
	
	private static final long serialVersionUID = 1L;

    private String message;
    private String input;

    public WrongNameException(String input, String message) {
        this.input = input;
        this.message = message;
    }

    public WrongNameException(String input) {
        this.input = input;
    }

    public WrongNameException() {
    }

}