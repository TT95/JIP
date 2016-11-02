package start.ex;

/**
 * Created by teo on 10/27/16.
 */
public class TankOverload extends Exception {
	
	private static final long serialVersionUID = 1L;

    private static final String idMessage = "Tank overload: ";
    private String message;

    public TankOverload(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return idMessage + message;
    }
}
