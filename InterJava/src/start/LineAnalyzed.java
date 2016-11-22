package start;

import start.ex.IncorrectInput;

public class LineAnalyzed {

    private Integer lineNUm;
    private String input;
    //null if not error
    private IncorrectInput error;
    private StudentData studentData;

    public LineAnalyzed(Integer lineNUm, String input) {
        this.lineNUm = lineNUm;
        setInput(input);
    }

    public void setInput(String input) {
        this.input = input;
        try {
            new StudentData(input);
        } catch (IncorrectInput i) {
            this.error = i;
            return;
        }
        error = null;
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
        return input;
    }

    public String toFullString() {
        String errorString = getError() == null ? "Line correct" : getError().toString();
        return "Line number:"+lineNUm+" input:\"" + input + "\" " + errorString;
    }
    
    @Override
    public boolean equals(Object obj) {
    	LineAnalyzed comp = (LineAnalyzed) obj;
    	return comp.getInput().equals(this.getInput());
    }
}
