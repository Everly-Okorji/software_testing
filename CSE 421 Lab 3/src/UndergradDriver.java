import ut.LetterGrade;
import ut.Undergrad;

public class UndergradDriver extends Undergrad {

	float GPA;
	int credits;
	LetterGrade lastgrade;
	
	public UndergradDriver() {
		GPA = 0.0f;
		credits = 0;
		lastgrade = LetterGrade.EPURE;
	}
	
	public void assignGrade(short credit, LetterGrade grade) {
		lastgrade = grade;
		GPA = ((GPA * credits) + (Driver.gradeLookup.get(grade) * credit)) / (credits + credit);
		credits += credit;
	}

	public float getGPA() {
		return GPA;
	}

	public LetterGrade getLastGrade() {
		return lastgrade;
	}

	public int getTotalCredits() {
		return credits;
	}
}
