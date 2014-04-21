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

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFullName() {
		assertTrue("Grad student's name is incorrect!", "John Moss".equals(grad.getName()));
		assertTrue("Undergrad student's name is incorrect!", "Janet Trust".equals(undergrad.getName()));
	}
	
	@Test
	public void testEmailAddress() {
		
	}
	
	@Test
	public void testGPA() {
		
	}
	
	

}
