import java.util.regex.*;
import java.util.*;

/**
 * 
 */
public class Command {

    /**
     * Default constructor
     */
    public Command() {
    }

    /**
     * 
     */
    private String cmdContent;

    /**
     * 
     */

    public void setContent(String str) {
        // TODO implement here
        cmdContent = str;
    }
    public String getContent(){
    	return cmdContent;
    }

}