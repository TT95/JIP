package start.ex;

public class IncorrectFuelConsumption extends IncorrectInput {
	
	private static final long serialVersionUID = 1L;

    public IncorrectFuelConsumption(String input) {
        super(input);
    }

    public IncorrectFuelConsumption(String input, String message) {
        super(input,message);
    }

    public IncorrectFuelConsumption() {
    }
}
