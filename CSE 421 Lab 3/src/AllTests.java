import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ GradTest.class, ProfTest.class, StudentTest.class,
		UndergradTest.class })
public class AllTests {

}
