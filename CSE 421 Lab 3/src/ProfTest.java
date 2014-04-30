import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ut.LetterGrade;
import ut.Prof;
import ut.Student;


public class ProfTest {

	Prof prof;
	Prof profWithSetCredits;
	StudentDriver[] students;
	
	@Before
	public void setUp() throws Exception {
		students = new StudentDriver[10];
		for (int i = 0; i < 10; i++) {
			students[i] = new StudentDriver();
		}
		
		prof = new Prof(students);
		profWithSetCredits = new Prof(students, (short) 4);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOneParamConstructor() {
		assertEquals("Number of students assigned to professor must match!", prof.students.length, students.length);
		for (int k = 0; k < students.length; k++) {
			String name = "Jesse-" + k + " Smith";
			assertTrue("Student names must match!", name.equals(prof.students[k].getName()));
		}
		assertTrue("Credits for a professor's course must be between 1 and 5!", prof.getCredits() >= 1 && prof.getCredits() <= 5);
	}
	
	@Test
	public void testTwoParamConstructor() {
		assertEquals("Number of students assigned to professor must match!", profWithSetCredits.students.length, students.length);
		for (int k = 0; k < students.length; k++) {
			String name = "Jesse-" + k + " Smith";
			assertTrue("Student names must match!", name.equals(profWithSetCredits.students[k].getName()));
		}
		assertTrue("Credits for this professor's course must be 4!", profWithSetCredits.getCredits() == 4);
		
		assertEquals("Credits for this professor's course is incorrect!", 1, (new Prof(students, (short)1)).getCredits());
		assertEquals("Credits for this professor's course is incorrect!", 5, (new Prof(students, (short)5)).getCredits());
	}
	
	@Test (expected=AssertionError.class)
	public void testInvalidCredits() {
		new Prof(students, (short) 0);
	}
	
	@Test (expected=AssertionError.class)
	public void testInvalidCredits2() {
		new Prof(students, (short) 6);
	}
	
	@Test
	public void testEvaluate() {
		
		short credits = prof.getCredits();
		
		Student[] result = prof.evaluate();
		for (int k = 0; k < result.length; k++) {
			assertTrue("GPAs must be between 0.0 and 4.0!", result[k].getGPA() >= 0.0 && result[k].getGPA() <= 4.0);
			assertTrue("Student GPAs must be equal to one of the pre-defined letter grades (only one class taken)!", Driver.gradeLookup.containsValue(result[k].getGPA()));
			assertEquals("All students must be assigned the same credits!", credits, result[k].getTotalCredits());
		}
		
		profWithSetCredits = new Prof(result, (short)1);
		credits +=1;
		result = profWithSetCredits.evaluate();
		for (int k = 0; k < result.length; k++) {
			assertTrue("GPAs must be between 0.0 and 4.0!", result[k].getGPA() >= 0.0 || result[k].getGPA() <= 4.0);
			assertEquals("All students must be assigned the same credits!", credits, result[k].getTotalCredits());
		}
		
		LetterGrade[] grades = {LetterGrade.APURE, LetterGrade.CPURE, LetterGrade.APURE, LetterGrade.APURE, LetterGrade.BPLUS, LetterGrade.BPLUS, LetterGrade.BPURE, LetterGrade.APURE, LetterGrade.DPURE, LetterGrade.APURE};
		for (int i = 0; i < 10; i++) {
			students[i] = new StudentDriver();
		};
		prof = new Prof(students, (short)5);
		credits = 5;
		result = prof.evaluate(grades);
		for (int k = 0; k < result.length; k++) {
			assertTrue("GPAs must be between 0.0 and 4.0!", result[k].getGPA() >= 0.0 || result[k].getGPA() <= 4.0);
			assertEquals("All students must be assigned the appropriate credits!", credits, result[k].getTotalCredits());
			assertTrue("All student GPAs must equal assigned letter grade (note: one class)!", Driver.gradeLookup.get(grades[k]) == result[k].getGPA());
		}
	}
	
    public static void main(String args[]) {
        String[] testCaseName = 
            { ProfTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }

}
