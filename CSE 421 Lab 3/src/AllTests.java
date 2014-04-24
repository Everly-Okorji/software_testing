import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ GradTest.class, ProfTest.class, StudentTest.class,
		UndergradTest.class })
public class AllTests {
	
    public static void main(String args[]) {
        String[] testCaseName = 
            { AllTests.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }

}
