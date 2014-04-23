import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ProfTest {

	Prof prof;
	Prof profWithSetCredits;
	Student[] students;
	Map<LetterGrade, Float> creditValue = new HashMap<LetterGrade, Float>();
	
	@Before
	public void setUp() throws Exception {
		students = new Student[10];
		for (int i = 0; i < 10; i++) {
			Student.Rank rank;
			if (i % 3 == 0) {
				rank = Student.Rank.GRAD;
			} else {
				rank = Student.Rank.UNDERGRAD;
			}
			students[i] = new Student("Jesse-" + i, "Smith", rank);
		}
		
		prof = new Prof(students);
		profWithSetCredits = new Prof(students, (short) 4);
		
		creditValue.put(LetterGrade.APURE, 4.0f);
		creditValue.put(LetterGrade.AMINUS, 3.7f);
		creditValue.put(LetterGrade.BPLUS, 3.3f);
		creditValue.put(LetterGrade.BPURE, 3.0f);
		creditValue.put(LetterGrade.BMINUS, 2.7f);
		creditValue.put(LetterGrade.CPLUS, 2.3f);
		creditValue.put(LetterGrade.CPURE, 2.0f);
		creditValue.put(LetterGrade.CMINUS, 1.7f);
		creditValue.put(LetterGrade.DPLUS, 1.3f);
		creditValue.put(LetterGrade.DPURE, 1.0f);
		creditValue.put(LetterGrade.EPURE, 0.0f);
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
		assertTrue("Credits for a professor's course must be between 1 and 5!", prof.credits >= 1 && prof.credits <= 5);
	}
	
	@Test
	public void testTwoParamConstructor() {
		assertEquals("Number of students assigned to professor must match!", profWithSetCredits.students.length, students.length);
		for (int k = 0; k < students.length; k++) {
			String name = "Jesse-" + k + " Smith";
			assertTrue("Student names must match!", name.equals(profWithSetCredits.students[k].getName()));
		}
		assertTrue("Credits for this professor's course must be 4!", profWithSetCredits.credits == 4);
		
		assertTrue("Credits for this professor's course must be 1!", (new Prof(students, (short)1)).credits == 1);
		assertTrue("Credits for this professor's course must be 5!", (new Prof(students, (short)5)).credits == 5);
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
		
		short credits = prof.credits;
		
		Student[] result = prof.evaluate();
		for (int k = 0; k < result.length; k++) {
			assertTrue("GPAs must be between 0.0 and 4.0!", result[k].getGPA() >= 0.0 && result[k].getGPA() <= 4.0);
			assertTrue("Student GPAs must be equal to one of the pre-defined letter grades (only one class taken)!", creditValue.containsValue(result[k].getGPA()));
			assertTrue("All students must be assigned the same credits!", result[k].getTotalCredits() == credits);
		}
		
		profWithSetCredits = new Prof(result, (short)1);
		credits +=1;
		result = profWithSetCredits.evaluate();
		for (int k = 0; k < result.length; k++) {
			assertTrue("GPAs must be between 0.0 and 4.0!", result[k].getGPA() >= 0.0 || result[k].getGPA() <= 4.0);
			assertTrue("All students must be assigned the same credits!", result[k].getTotalCredits() == credits);
		}
		
		LetterGrade[] grades = {LetterGrade.APURE, LetterGrade.CPURE, LetterGrade.APURE, LetterGrade.APURE, LetterGrade.BPLUS, LetterGrade.BPLUS, LetterGrade.BPURE, LetterGrade.APURE, LetterGrade.DPURE, LetterGrade.APURE};
		for (int i = 0; i < 10; i++) {
			students[i] = new Student("Johnson-" + i, "Riley", Student.Rank.UNDERGRAD);
		};
		prof = new Prof(students, (short)5);
		credits = 5;
		result = prof.evaluate(grades);
		for (int k = 0; k < result.length; k++) {
			assertTrue("GPAs must be between 0.0 and 4.0!", result[k].getGPA() >= 0.0 || result[k].getGPA() <= 4.0);
			assertTrue("All students must be assigned the appropriate credits!", result[k].getTotalCredits() == credits);
			assertTrue("All student GPAs must equal assigned letter grade (note: one class)!", creditValue.get(grades[k]) == result[k].getGPA());
		}
	}

}
