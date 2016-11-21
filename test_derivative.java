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
	public void test4() {
		assertEquals("2*a+b",feifeicaicai.derivative("a*a+a*b", "!d/d a"));
	}
}
