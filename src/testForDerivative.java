import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author lanxuan
 *
 */
public class testForDerivative {

	@Test
	public void test1() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("aaa*aaa*aaa+bbb+ccc");
		cmd.setContent("!d/d aaa");
		assertEquals("3*aaa^2",exp.derivative(cmd));
	}
	@Test
	public void test2() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("3+a+b");
		cmd.setContent("!d/d a");
		assertEquals("1",exp.derivative(cmd));
	}
	@Test
	public void test3() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("a*b*c");
		cmd.setContent("!d/d z");
		assertEquals("Error! No variable!",exp.derivative(cmd));
	}

	@Test
	public void test4() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("a*a+a*b");
		cmd.setContent("!d/d a");
		assertEquals("2*a+b",exp.derivative(cmd));
	}



}
