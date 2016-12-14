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
import start.ex.IncorrectGender;
import start.ex.IncorrectLastName;
import start.ex.IncorrectName;

/**
 * Created by teo on 11/7/16.
 */
public class StudentFile {

    private List<LineAnalyzed> analyzedLines;
    private String filePath;
    
	public StudentFile(String pathToFile) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(pathToFile));
        analyzedLines = new ArrayList<>();

        for (int lineNum=0; lineNum<lines.size(); lineNum++) {
            String line = lines.get(lineNum);
            analyzedLines.add(new LineAnalyzed(lineNum+1,line));
        }
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

    public List<LineAnalyzed> getGenderError() {
        return getAnalyzedLines(IncorrectGender.class);
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


}
