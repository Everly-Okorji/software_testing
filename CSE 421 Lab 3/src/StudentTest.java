import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {

	Student grad;
	Student undergrad;
	
	@Before
	public void setUp() throws Exception {
		grad = new Student("John", "Moss", Student.Rank.GRAD);
		undergrad = new Student("Janet", "Trust", Student.Rank.UNDERGRAD);
	}


	@Test
	public void testFullName() {
		assertTrue("Grad student's name is incorrect!", "John Moss".equals(grad.getName()));
		assertTrue("Undergrad student's name is incorrect!", "Janet Trust".equals(undergrad.getName()));
	}
	
	@Test
	public void testEmailAddress() {
		grad = new Student("John", "Moss", Student.Rank.GRAD);
		undergrad = new Student("Janet", "Trust", Student.Rank.UNDERGRAD);
		assertTrue("Grad student's email adress is incorrect!", "moss.1@utexas.edu".equals(grad.getEmailAddress()));
		assertTrue("Grad student's email adress is incorrect!", "trust.2@utexas.edu".equals(undergrad.getEmailAddress()));
	}
	
	
	@Test
	public void testAssignGrade() {
		grad = new Student("John", "Moss", Student.Rank.GRAD);
		grad.assignGrade((short) 3, LetterGrade.APURE);
		assertTrue("Grad student's assigned grade is incorrect", grad.getGPA() == 4);
	}
	
	@Test
	public void testTotalCredits() {
		grad = new Student("John", "Moss", Student.Rank.GRAD);
		grad.assignGrade((short) 3, LetterGrade.APURE);
		assertTrue("Grad student's total credits is incorrect", grad.getTotalCredits() == 3);
	}
	
    public static void main(String args[]) {
        String[] testCaseName = 
            { StudentTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }

	

}
