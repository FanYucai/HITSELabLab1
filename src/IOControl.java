import java.util.regex.*;
import java.util.*;

/**
 * 
 */
public class IOControl {

    /**
     * Default constructor
     */

	private static Pattern simPattern = Pattern.compile("^!simplify ([a-zA-Z]+=[0-9]+[\\s]*)+$");
	private static Pattern simSpecPattern = Pattern.compile("^!simplify\\s*$");
	private static Pattern simErrorPattern = Pattern.compile("(=[a-zA-Z0-9]+=)");
	private static Pattern derPattern = Pattern.compile("^!d/d [a-zA-Z]+$");
    public IOControl() {
    }

    /**
     * 
     */
    private String input;

    /**
     * 
     */
    private String result;




    /**
     * 
     */
    public void exec(Expression expression, Command command){
    	Matcher simMatcher = simPattern.matcher(input);
		Matcher derMatcher = derPattern.matcher(input);
		Matcher simSecMatcher = simSpecPattern.matcher(input);
		Matcher simErrorMatcher = simErrorPattern.matcher(input);
		if (input.isEmpty()) {
			result= "Shit!";
		} else if (input.charAt(0) != '!') {
			String exp = input;
			exp = exp.replaceAll("\\s", "");
			expression.setExpContent(exp);
			exp = expression.init(exp);
			expression.setExpContent(exp);
			result = expression.getResContent();
		} else {
			if (expression.getResContent().isEmpty()) {
				result = "No expressions available!" ;
				return;
			}
			String cmd = input;
			command.setContent(cmd);
			String exp = expression.getExpContent();
			if (simSecMatcher.matches()) {
				result = expression.addPower(exp);
			} else if (simErrorMatcher.find()) {
				result = "Please add spaces in right places.";
			} else if (simMatcher.matches()) {
				if (expression.simplify(command) == "Existing unavailable syntax.") {
					result = "Existing unavailable syntax.";
				} else
					result = expression.getResContent();
			} else if (derMatcher.matches()) {
				if (expression.derivative(command) == "Error! No variable!") {
					result = "Error! No variable!";
				} else
					result = expression.getResContent();
			} else {
				result = "command not found: " + cmd;
			}
		}
    }
    public String getResult() {
    	return result;
    }

    public void setInput(String str) {
    	this.input = str;
    }

}