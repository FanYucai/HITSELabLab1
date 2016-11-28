
import java.util.regex.*;
import java.util.*;
import javax.activation.*;

/**
 * 
 */
public class UserInterface {

    /**
     * Default constructor
     */

	
    public UserInterface() {
    }

    /**
     * 
     */
    private static String input;

    /**
     * 
     */
    private String result;



    /**
     * 
     */
    private void getInput(Scanner in) {
        input = in.nextLine();
    }

    /**
     * 
     */
    private void getResult(IOControl IOC, Expression exp , Command cmd ) {
        IOC.setInput(input);
        IOC.exec(exp, cmd);
        result = IOC.getResult();
    }
    /**
     * 
     */
    private void showResult() {
    	System.out.print(result+"\n>>");
    }
    
    /**
     * 
     */
    private void showInfo() {
        // TODO implement here
        System.out.print("-------------------------------------------\n");
        System.out.print("   (lanxuan & N0e1) All rights preserved.  \n");
        System.out.print("-------------------------------------------\n\n>>");
    }
    
    public static void main(String[] args) {
        
    	UserInterface UI = new UserInterface();
        Expression exp = new Expression();
        Command cmd = new Command();
        IOControl IOC = new IOControl();
        
        Scanner in = new Scanner(System.in);
        
        UI.showInfo();
        while(true) {
            UI.getInput(in);
            UI.getResult(IOC,exp, cmd);
            UI.showResult();
        }
    }

}