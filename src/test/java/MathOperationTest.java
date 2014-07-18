import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class MathOperationTest {
	
	MathOperation classUnderTest;

	@Before
	public void setUp() throws Exception {
		classUnderTest = new MathOperation();
	}

	@Test
	public void testDevideByWhenAIsZero() {
		double a=0;
		double b=5;
		double expectedValue=0;
		double actualValue = classUnderTest.devideBy(a, b);
		Assert.assertEquals("Expected value should be 0",expectedValue, actualValue);
		
		
		
		
	}

}
