package start.ex;

/**
 * Created by teo on 10/26/16.
 */
public class IncorrectInput extends Exception {

    private String message;
    private final static String idMessage = "Input error ";

    public IncorrectInput(String message) {
        this.message = message;
    }

    public IncorrectInput() {
    }

    @Override
    public String toString() {
        return idMessage + "message:" + message==null?"":message;
    }
}
