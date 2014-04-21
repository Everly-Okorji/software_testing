import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ProfTest {

	Prof prof;
	Prof profWithSetCredits;
	Student[] students;
	
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
		
	}

}
