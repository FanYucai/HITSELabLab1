import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author lanxuan
 *
 */
public class testForSimplify {


	@Test
	public void test0() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("a*a+a*b");
		cmd.setContent("!simplify a=1");
		assertEquals("1+b",exp.simplify(cmd));
	}


	@Test
	public void test1() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("aaa*aaa*aaa+bbb+ccc");
		cmd.setContent("!simplify aaa=1 bbb=2 ccc=3");
		assertEquals("6",exp.simplify(cmd));
	}


	@Test
	public void test2() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("aaa*aaa*aaa+bbb+ccc");
		cmd.setContent("!simplify z=1");
		assertEquals("Existing unavailable syntax.",exp.simplify(cmd));
	}


	@Test
	public void test3() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("b*c*d");
		cmd.setContent("!simplify c=8 d=1");
		assertEquals("8*b",exp.simplify(cmd));
	}

	@Test
	public void test4() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("UI+a+KK");
		cmd.setContent("!simplify UI=100 a=89");
		assertEquals("189+KK",exp.simplify(cmd));
	}
	@Test
	public void test5() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("aaa*aaa*aaa+bbb+ccc");
		cmd.setContent("!simplify ccc=3");
		assertEquals("3+aaa^3+bbb",exp.simplify(cmd));
	}
	@Test
	public void test6() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("15*a+b");
		cmd.setContent("!simplify a=1");
		assertEquals("15+b",exp.simplify(cmd));
	}
	@Test
	public void test7() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("88*a*a*c+b*c*d");
		cmd.setContent("!simplify a=1 b=2");
		assertEquals("88*c+2*c*d",exp.simplify(cmd));
	}
	@Test
	public void test8() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("a*a*a*a*a");
		cmd.setContent("!simplify a=2");
		assertEquals("32",exp.simplify(cmd));
	}
	@Test
	public void test9() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("b*c*d");
		cmd.setContent("!simplify b=0");
		assertEquals("0",exp.simplify(cmd));
	}
	@Test
	public void test10() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("b+c*d+e+f");
		cmd.setContent("!simplify c=2 d=10");
		assertEquals("20+b+e+f",exp.simplify(cmd));
	}
	@Test
	public void test11() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("u*i*i");
		cmd.setContent("!simplify aaa=1 bbb=2 ccc=3");
		assertEquals("Existing unavailable syntax.",exp.simplify(cmd));
	}
	@Test
	public void test12() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("b");
		cmd.setContent("!simplify b=10");
		assertEquals("10",exp.simplify(cmd));
	}
	@Test
	public void test13() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("c+d");
		cmd.setContent("!simplify c=0 d=0");
		assertEquals("0",exp.simplify(cmd));
	}
	@Test
	public void test14() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("a*bc+bc*d+a");
		cmd.setContent("!simplify a=2 bc=2");
		assertEquals("6+2*d",exp.simplify(cmd));
	}
	@Test
	public void test15() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("iuo+nij");
		cmd.setContent("!simplify iuo=0");
		assertEquals("nij",exp.simplify(cmd));
	}
	@Test
	public void test16() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("a+b*x");
		cmd.setContent("!simplify x=0");
		assertEquals("a",exp.simplify(cmd));
	}
	@Test
	public void test17() {
		Expression exp = new Expression();
		Command cmd=new Command();
		exp.setExpContent("a*u+u*z");
		cmd.setContent("!simplify u=0");
		assertEquals("0",exp.simplify(cmd));
	}


}
