/**
 * 
 */

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author lanxuan
 *
 */
public class test_derivative {
	@Test
	public void test0() {
		assertEquals("1+b",feifeicaicai.simplify("a*a+a*b", "!simplify a=1"));
	}
	@Test
	public void test1() {
		assertEquals("6",feifeicaicai.simplify("aaa*aaa*aaa+bbb+ccc", "!simplify aaa=1 bbb=2 ccc=3"));
	}
	@Test
	public void test2() {
		assertEquals("@",feifeicaicai.simplify("aaa*aaa*aaa+bbb+ccc", "!simplify z=1"));
	}
	@Test
	public void test3() {
		assertEquals("8*b",feifeicaicai.simplify("b*c*d", "!simplify c=8 d=1"));
	}
	@Test
	public void test4() {
		assertEquals("189+KK",feifeicaicai.simplify("UI+a+KK", "!simplify UI=100 a=89"));
	}
	@Test
	public void test5() {
		assertEquals("3+aaa^3+bbb",feifeicaicai.simplify("aaa*aaa*aaa+bbb+ccc", "!simplify ccc=3"));
	}
	@Test
	public void test6() {
		assertEquals("15+b",feifeicaicai.simplify("15*a+b", "!simplify a=1"));
	}
	@Test
	public void test7() {
		assertEquals("88*c+2*c*d",feifeicaicai.simplify("88*a*a*c+b*c*d", "!simplify a=1 b=2"));
	}
	@Test
	public void test8() {
		assertEquals("32",feifeicaicai.simplify("a*a*a*a*a", "!simplify a=2"));
	}
	@Test
	public void test9() {
		assertEquals("0",feifeicaicai.simplify("b*c*d", "!simplify b=0"));
	}
	@Test
	public void test10() {
		assertEquals("20+b+e+f",feifeicaicai.simplify("b+c*d+e+f", "!simplify c=2 d=10"));
	}
	@Test
	public void test11() {
		assertEquals("@",feifeicaicai.simplify("u*i*i", "!simplify aaa=1 bbb=2 ccc=3"));
	}
	@Test
	public void test12() {
		assertEquals("10",feifeicaicai.simplify("b", "!simplify b=10"));
	}
	@Test
	public void test13() {
		assertEquals("0",feifeicaicai.simplify("c+d", "!simplify c=0 d=0"));
	}
	@Test
	public void test14() {
		assertEquals("6+2*d",feifeicaicai.simplify("a*bc+bc*d+a", "!simplify a=2 bc=2"));
	}
	@Test
	public void test15() {
		assertEquals("nij",feifeicaicai.simplify("iuo+nij", "!simplify iuo=0"));
	}
	@Test
	public void test16() {
		assertEquals("a",feifeicaicai.simplify("a+b*x", "!simplify x=0"));
	}
	@Test
	public void test17() {
		assertEquals("0",feifeicaicai.simplify("a*u+u*z", "!simplify u=0"));
	}
}
