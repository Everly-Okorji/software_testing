import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ut.Grad;
import ut.LetterGrade;


public class GradTest {

	Grad grad;
	
	@Before
	public void setUp() throws Exception {
		grad = new Grad();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		assertEquals("There should be no credits assigned!", 0, grad.getTotalCredits());
	}
	
	@Test (expected=AssertionError.class)
	public void testInvalidAssignGrade() {
		grad.assignGrade((short) 0, LetterGrade.APURE);
	}
	
	@Test (expected=AssertionError.class)
	public void testInvalidAssignGrade2() {
		grad.assignGrade((short) 6, LetterGrade.APURE);
	}
	
	@Test (expected=AssertionError.class)
	public void testInvalidAssignGrade3() {
		grad.assignGrade((short) 3, null);
	}
	
	@Test
	public void testGetLastGrade() {
		grad.assignGrade((short) 4, LetterGrade.BPLUS);
		assertEquals("The expected last grade does not match!", LetterGrade.BPLUS, grad.getLastGrade());
		
		grad.assignGrade((short) 3, LetterGrade.CMINUS);
		assertEquals("The expected last grade does not match!", LetterGrade.CMINUS, grad.getLastGrade());
	}

	@Test
	public void testGetTotalCredits() {
		assertEquals("The expected total credits does not match!", 0, grad.getTotalCredits());

		grad.assignGrade((short) 4, LetterGrade.BPLUS);
		assertEquals("The expected total credits does not match!", 4, grad.getTotalCredits());
		
		grad.assignGrade((short) 3, LetterGrade.CMINUS);
		assertEquals("The expected total credits does not match!", 7, grad.getTotalCredits());
	
		grad.assignGrade((short) 1, LetterGrade.CMINUS);
		assertEquals("The expected total credits does not match!", 8, grad.getTotalCredits());
	}
	
	@Test
	public void testGetGPA() {
		grad.assignGrade((short) 4, LetterGrade.BPLUS);
		assertEquals("GPA is incorrect!", 3.3f, grad.getGPA(), 0.001);
		
		grad.assignGrade((short) 3, LetterGrade.CMINUS);
		assertEquals("GPA is incorrect!", ((3.3f * 4) + (1.7f * 3)) / 7, grad.getGPA(), 0.001);

		grad.assignGrade((short) 1, LetterGrade.APURE);
		assertEquals("GPA is incorrect!", ((3.3f * 4) + (1.7f * 3) + (4.0f * 1)) / 8, grad.getGPA(), 0.001);
	}
	
    public static void main(String args[]) {
        String[] testCaseName = 
            { GradTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }
    
}
