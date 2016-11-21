package start;

import start.ex.IncorrectInput;

public class LineAnalyzed {

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
    
    @Override
    public boolean equals(Object obj) {
    	LineAnalyzed comp = (LineAnalyzed) obj;
    	return comp.getInput().equals(this.getInput());
    }
}
