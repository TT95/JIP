package task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by teo on 10/26/16.
 */
public class PopularNames {

    private static List<String> popularNames = new LinkedList<>();
    private static List<String> popularMaleNames = new LinkedList<>();
    private static List<String> popularFemaleNames = new LinkedList<>();

    private static final String fileSeperator = "#### Girls'";
    private static final String defFilePath = "res/PopularNames.txt";
//    private static final String defFilePath = "./res/PopularNames.txt";

    private static String givenPath = null;

    private static void loadNames(){
        List<String> names = new ArrayList<>();
        String pathToLoad = givenPath==null?defFilePath:givenPath;
        try {
            names = Files.readAllLines(Paths.get(pathToLoad));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int index = names.indexOf(fileSeperator);
        popularMaleNames = new LinkedList<>(names.subList(1,index)); //skipping first line
        popularFemaleNames = new LinkedList<>(names.subList(index + 1, names.size()));
        popularNames = new ArrayList<>();
        popularNames.addAll(popularMaleNames);
        popularNames.addAll(popularFemaleNames);
    }
    

    
    public static void setPathToFile(String path) {
    	
    	givenPath = path;

    	popularNames.clear();
    	loadNames();
    	
    }


    
    public static List<String> getPopularNames() {

            loadNames();
        return popularNames;
    }




}