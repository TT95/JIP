package start;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import start.ex.IncorrectDate;
import start.ex.IncorrectInput;
import start.ex.IncorrectLastName;
import start.ex.IncorrectName;

/**
 * Created by teo on 11/7/16.
 */
public class StudentFile {

    private List<LineAnalyzed> analyzedLines;
    private String filePath;
    private static final String defaultPropFilePath = "res/studentData.properties";

    public StudentFile(String pathToPropFile) throws IOException {

        filePath = loadPropFile(pathToPropFile);

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        analyzedLines = new ArrayList<>();

        for (int lineNum=0; lineNum<lines.size(); lineNum++) {
            String line = lines.get(lineNum);
            try {
                new StudentData(line);
            } catch (IncorrectInput incorrectInput) {
                analyzedLines.add(new LineAnalyzed(lineNum+1,line,incorrectInput));
                continue;
            }
            analyzedLines.add(new LineAnalyzed(lineNum+1,line,null));
        }
    }
   

    /**
     * Read prop file and returns path to student file
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    private String loadPropFile(String pathToProp) throws FileNotFoundException, IOException {

        Properties prop = new Properties();
        prop.load(new FileInputStream(pathToProp));
        return  prop.getProperty("path");
    }

    public List<LineAnalyzed> getAnalyzedLines(Class incorrectInputClass) {
    	List<LineAnalyzed> matchingList = new ArrayList<>();
        for (LineAnalyzed lineAnalyzed : analyzedLines) {
            if (lineAnalyzed.getError() == null) {
                if (incorrectInputClass == null) {
                	matchingList.add(lineAnalyzed);
                }
            } else {
                if (lineAnalyzed.getError().getClass() == incorrectInputClass) {
                    matchingList.add(lineAnalyzed);
                }
            }
        }
        return matchingList;
    }

    public List<LineAnalyzed> getWrongLines() {
    	List<LineAnalyzed> list = new ArrayList<>();
        for (LineAnalyzed lineAnalyzed : analyzedLines) {
            if (lineAnalyzed.getError() != null) {
                list.add(lineAnalyzed);
            }
        }
    	return list;
    }

    public List<LineAnalyzed> getProperLines() {
        return getAnalyzedLines(null);
    }


    public List<LineAnalyzed> getFirstNameError() {
    	return getAnalyzedLines(IncorrectName.class);
    }

    public List<LineAnalyzed> getLastNameError() {
    	return getAnalyzedLines(IncorrectLastName.class);
    }

    public List<LineAnalyzed> getDateError() {
    	return getAnalyzedLines(IncorrectDate.class);
    }
    
    public List<LineAnalyzed> getAnalyzedLines() {
    	return analyzedLines;
    }


    public static void main(String[] args) {
        try {
            StudentFile studentFile = new StudentFile(defaultPropFilePath);
//            studentFile.showProperLines();
//            studentFile.showWrongLines();
//            studentFile.showFirstNameError();
//            studentFile.showLastNameError();
//            studentFile.showDateError();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
