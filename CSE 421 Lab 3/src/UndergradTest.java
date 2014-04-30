import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ut.LetterGrade;
import ut.Undergrad;


public class UndergradTest extends TestCase {

	Undergrad undergrad;
	
	@Before
	public void setUp() throws Exception {
		undergrad = new Undergrad();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		assertEquals("There should be no credits assigned!", 0, undergrad.getTotalCredits());
	}
	
	@Test (expected=AssertionError.class)
	public void testInvalidAssignGrade() {
		undergrad.assignGrade((short) 0, LetterGrade.APURE);
	}
	
	@Test (expected=AssertionError.class)
	public void testInvalidAssignGrade2() {
		undergrad.assignGrade((short) 6, LetterGrade.APURE);
	}
	
	@Test (expected=AssertionError.class)
	public void testInvalidAssignGrade3() {
		undergrad.assignGrade((short) 3, null);
	}
	
	@Test
	public void testGetLastGrade() {
		undergrad.assignGrade((short) 4, LetterGrade.BPLUS);
		assertEquals("The expected last grade does not match!", LetterGrade.BPLUS, undergrad.getLastGrade());
		
		undergrad.assignGrade((short) 3, LetterGrade.CMINUS);
		assertEquals("The expected last grade does not match!", LetterGrade.CMINUS, undergrad.getLastGrade());
	}

	@Test
	public void testGetTotalCredits() {
		assertEquals("The expected total credits does not match!", 0, undergrad.getTotalCredits());

		undergrad.assignGrade((short) 4, LetterGrade.BPLUS);
		assertEquals("The expected total credits does not match!", 4, undergrad.getTotalCredits());
		
		undergrad.assignGrade((short) 3, LetterGrade.CMINUS);
		assertEquals("The expected total credits does not match!", 7, undergrad.getTotalCredits());
	
		undergrad.assignGrade((short) 1, LetterGrade.CMINUS);
		assertEquals("The expected total credits does not match!", 8, undergrad.getTotalCredits());
	}
	
	@Test
	public void testGetGPA() {
		undergrad.assignGrade((short) 4, LetterGrade.BPLUS);
		assertEquals("GPA is incorrect!", 3.3f, undergrad.getGPA(), 0.001);
		
		undergrad.assignGrade((short) 3, LetterGrade.CMINUS);
		assertEquals("GPA is incorrect!", ((3.3f * 4) + (1.7f * 3)) / 7, undergrad.getGPA(), 0.001);

		undergrad.assignGrade((short) 1, LetterGrade.APURE);
		assertEquals("GPA is incorrect!", ((3.3f * 4) + (1.7f * 3) + (4.0f * 1)) / 8, undergrad.getGPA(), 0.001);
	}
	
    public static void main(String args[]) {
        String[] testCaseName = 
            { UndergradTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }
}
