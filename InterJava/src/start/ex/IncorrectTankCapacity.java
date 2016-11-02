package start.ex;

public class IncorrectTankCapacity extends IncorrectInput {
	
	private static final long serialVersionUID = 1L;

    public IncorrectTankCapacity(String input) {
        super(input);
    }

    public IncorrectTankCapacity(String input, String message) {
        super(input,message);
    }

    public IncorrectTankCapacity() {
    }
}
