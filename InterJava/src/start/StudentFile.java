package start;

import start.ex.IncorrectDate;
import start.ex.IncorrectInput;
import start.ex.IncorrectLastName;
import start.ex.IncorrectName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by teo on 11/7/16.
 */
public class StudentFile {

    private List<LineAnalyzed> analyzedLines;

    public StudentFile(String filePath) throws IOException {

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

    public void printAnalyzedLine(Class incorrectInputClass) {
        for (LineAnalyzed lineAnalyzed : analyzedLines) {
            if (lineAnalyzed.getError() == null) {
                if (incorrectInputClass == null) {
                    System.out.println(lineAnalyzed);
                }
            } else {
                if (lineAnalyzed.getError().getClass() == incorrectInputClass) {
                    System.out.println(lineAnalyzed);
                }
            }
        }
    }

    public void showWrongLines() {
        for (LineAnalyzed lineAnalyzed : analyzedLines) {
            if (lineAnalyzed.getError() != null) {
                System.out.println(lineAnalyzed);
            }
        }
    }

    public void showProperLines() {
        printAnalyzedLine(null);
    }


    public void showFirstNameError() {
        printAnalyzedLine(IncorrectName.class);
    }

    public void showLastNameError() {
        printAnalyzedLine(IncorrectLastName.class);
    }

    public void showDateError() {
        printAnalyzedLine(IncorrectDate.class);
    }

    public static void main(String[] args) {
        try {
            new StudentFile("res/Students.txt").showWrongLines();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class LineAnalyzed {

        private Integer lineNUm;
        private String input;
        //null if not error
        private IncorrectInput error;

        public LineAnalyzed(Integer lineNUm, String input, IncorrectInput error) {
            this.lineNUm = lineNUm;
            this.input = input;
            this.error = error;
        }

        public Integer getLineNUm() {
            return lineNUm;
        }

        public String getInput() {
            return input;
        }

        public IncorrectInput getError() {
            return error;
        }

        @Override
        public String toString() {
            String errorString = getError() == null ? "Line correct" : getError().toString();
            return "Line number:"+lineNUm+" input:\"" + input + "\" " + errorString;
        }
    }


}
