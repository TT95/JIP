package start.ex;

/**
 * Created by teo on 10/26/16.
 */
public class ImpossibleDrive extends Exception{

    private static final String idMessage = "Impossible driving trip: ";
    private String message;

    public ImpossibleDrive(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return idMessage + message;
    }
}
