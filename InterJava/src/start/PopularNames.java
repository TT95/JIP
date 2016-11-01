package start;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by teo on 10/26/16.
 */
public class PopularNames {

    private static List<String> popularNames;
    private static List<String> popularMaleNames;
    private static List<String> popularFemaleNames;

    private static String fileSeperator = "#### Girls'";
    private static String filePath = "res/PopularNames.txt";

    private static void loadNames(){
        List<String> names = new ArrayList<>();
        try {
            names = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        popularNames = names;
        int index = names.indexOf(fileSeperator);
        popularMaleNames = names.subList(1,index); //skipping first line
        popularFemaleNames = names.subList(index + 1, names.size());
    }


    public static List<String> getPopularNames() {
        if (popularNames == null) {
            loadNames();
        }
        return popularNames;
    }

    public static List<String> getPopularMaleNames() {
        if (popularMaleNames == null) {
            loadNames();
        }
        return popularMaleNames;
    }

    public static List<String> getPopularFemaleNames() {
        if (popularFemaleNames == null) {
            loadNames();
        }
        return popularFemaleNames;
    }
}
